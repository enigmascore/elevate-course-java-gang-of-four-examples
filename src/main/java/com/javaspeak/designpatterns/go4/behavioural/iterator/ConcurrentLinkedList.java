package com.javaspeak.designpatterns.go4.behavioural.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ConcurrentLinkedList uses Compare and Swap (CAS) operations to add and remove elements to the
 * list.  This is less expensive than traditional blocking synchronisation.
 * <p>
 * In ConcurrentLinkedList there is an AtomicReference field called lastAddedLinkedElement which
 * references the most recently added {@link LinkedElement}.  Each LinkedElement references the
 * LinkedElement added before it.
 * <p>
 * When the first LinkedElement, A, is added lastAddedLinkedElement references A.
 * <p>
 * When the next LinkedElement, B, is added lastAddedLinkedElement references B.
 * B itself references A.  In other words: B ==&gt; A
 * <p>
 * When the next LinkedElement, C, is added lastAddedLinkedElement references C.
 * C itself references B which in turn references A. In other words: C ==&gt; B ==&gt; A
 * <p>
 * Removal happens in two steps.  First the matching LinkedElement is logically deleted by
 * atomically setting its deleted flag; this CAS is the linearization point of the remove, so two
 * threads can never both remove the same node, and traversals simply skip deleted nodes.  Second,
 * the node is physically unlinked from the chain as a best-effort memory optimisation; if a
 * concurrent modification makes the unlink CAS fail the node stays in the chain but is invisible
 * to all readers because its deleted flag is set.  This deleted-flag technique is a simplified
 * form of the marking used by Harris' lock-free linked list algorithm and avoids the classic
 * bug where concurrently unlinking two adjacent nodes resurrects one of them.
 * <p>
 * The atomic field operations use {@link java.lang.invoke.VarHandle}s (see
 * {@link LinkedElement}), the modern replacement for {@code AtomicReferenceFieldUpdater}.
 *
 * @param <E> the element type of the list
 *
 * @author John Dickerson - 21 February 2020
 */
public class ConcurrentLinkedList<E> implements SnapshotIterable<E> {

    // references the last added LinkedElement.  New LinkedElements are created and updated in
    // this AtomicReference using a CAS operation.
    private final AtomicReference<LinkedElement<E>> lastAddedLinkedElement =
            new AtomicReference<>();

    /**
     * Creates an empty ConcurrentLinkedList.
     */
    public ConcurrentLinkedList() {
    }


    /**
     * A non expensive CAS operation is used to update the reference in lastAddedLinkedElement to
     * the newly added LinkedElement.
     * <p>
     * The newly added LinkedElement references the LinkedElements already in the chain.
     * <p>
     * LinkedElements are used to wrap the element being added and to provide a reference to the
     * next LinkedElement in the chain.
     * <p>
     * The LinkedElement placed at the beginning of the chain is always the last LinkedElement
     * added.
     *
     * @param object
     *      Element to add
     */
    public void add( E object ) {

        LinkedElement<E> linkedElement = new LinkedElement<>( object );

        // Keep trying to update the reference to the new LinkedElement being added using a CAS
        // operation until it succeeds.  CAS operations avoid the expense of traditional
        // synchronization.  Instead of blocking all other threads until the current thread
        // releases the lock, a CAS operation reads the current value of a field, does some
        // processing and, before replacing the current value with a new value, checks that the
        // old value has not changed in the meantime.  If the old value has changed it means it
        // was changed by another thread, and the operation is repeated until successful.  CAS
        // methods make use of CAS functionality in the hardware.
        while ( true ) {

            LinkedElement<E> currentHead = lastAddedLinkedElement.get();
            linkedElement.nextLinkedElement = currentHead;

            // if the old value read from lastAddedLinkedElement has not changed, replace it with
            // the new reference in a CAS operation.
            if ( lastAddedLinkedElement.compareAndSet( currentHead, linkedElement ) ) {

                return;
            }
        }
    }


    /**
     * Removes the first element in the chain (starting from the most recently added) which
     * equals the given object.
     * <p>
     * The remove is linearized by the CAS on the node's deleted flag: whichever thread wins that
     * CAS owns the removal.  Afterwards the node is physically unlinked on a best-effort basis;
     * even when that unlink loses a race the node stays invisible to traversals because its
     * deleted flag is set.
     *
     * @param objectToRemove
     *      Object to remove
     *
     * @throws NoSuchElementException
     *      if no matching element is present
     */
    public void remove( E objectToRemove ) {

        while ( true ) {

            LinkedElement<E> match = findFirstMatch( objectToRemove );

            if ( match == null ) {

                throw new NoSuchElementException( "Cannot find element to remove" );
            }

            // Logical delete: the CAS ensures only one thread removes this node.  If another
            // thread got there first, loop round and look for a different matching node.
            if ( match.markDeleted() ) {

                unlink( match );
                return;
            }
        }
    }


    /**
     * Walks the chain from the most recently added LinkedElement looking for the first
     * non-deleted node whose object equals the one given.
     *
     * @param objectToFind
     *      the object to look for
     *
     * @return the matching LinkedElement, or null if there is none
     */
    private LinkedElement<E> findFirstMatch( E objectToFind ) {

        for ( LinkedElement<E> linkedElement = lastAddedLinkedElement.get();
                linkedElement != null;
                linkedElement = linkedElement.nextLinkedElement ) {

            if ( !linkedElement.deleted && linkedElement.object.equals( objectToFind ) ) {

                return linkedElement;
            }
        }

        return null;
    }


    /**
     * Best-effort physical unlink of a logically deleted node.  Given the chain A ==&gt; B ==&gt; C,
     * unlinking B entails joining A with C as follows: A ==&gt; C.  Each CAS expects the reference
     * value that was actually read beforehand, so a concurrent modification makes the CAS fail
     * rather than silently losing another thread's update; in that case the node is simply left
     * in the chain, where the deleted flag keeps it invisible to traversals.
     *
     * @param target
     *      the logically deleted LinkedElement to unlink
     */
    private void unlink( LinkedElement<E> target ) {

        LinkedElement<E> successor = skipDeleted( target.nextLinkedElement );
        LinkedElement<E> currentHead = lastAddedLinkedElement.get();

        if ( currentHead == target ) {

            // The target is the head of the chain: swing the head reference past it.  The CAS
            // expects the head value read above, so a concurrent add is never lost.
            lastAddedLinkedElement.compareAndSet( target, successor );
            return;
        }

        // Otherwise walk the chain iteratively looking for the target's predecessor.
        LinkedElement<E> predecessor = currentHead;

        while ( predecessor != null ) {

            LinkedElement<E> next = predecessor.nextLinkedElement;

            if ( next == target ) {

                // The CAS expects the previously read reference (next), not a re-read of the
                // field, so it genuinely fails when a concurrent modification intervened.
                predecessor.casNext( next, successor );
                return;
            }

            predecessor = next;
        }
    }


    /**
     * Skips over any logically deleted nodes and returns the first live node (or null).  Used
     * when choosing the successor to link to during a physical unlink.
     *
     * @param linkedElement
     *      the node to start from
     *
     * @return the first non-deleted node at or after the given node, or null
     */
    private LinkedElement<E> skipDeleted( LinkedElement<E> linkedElement ) {

        LinkedElement<E> current = linkedElement;

        while ( current != null && current.deleted ) {

            current = current.nextLinkedElement;
        }

        return current;
    }


    /**
     * Takes a snapshot of the list as it is now and returns an iterator over it, in insertion
     * order (oldest element first).  The snapshot is an independent copy: elements added or
     * removed after this call do not affect the returned iterator.  An empty list yields an
     * empty iterator.
     *
     * @return an iterator over the snapshot, in insertion order
     */
    @Override
    public SnapshotIterator<E> getSnapshotIterator() {

        List<E> snapshot = new ArrayList<>();

        // Walk the chain from the most recently added element, skipping logically deleted
        // nodes.  The walk is iterative, so long lists cannot overflow the stack.
        for ( LinkedElement<E> linkedElement = lastAddedLinkedElement.get();
                linkedElement != null;
                linkedElement = linkedElement.nextLinkedElement ) {

            if ( !linkedElement.deleted ) {

                snapshot.add( linkedElement.object );
            }
        }

        // The walk collected newest-first; reversed() views the copy oldest-first, which is
        // insertion order.
        return new SnapshotIteratorImpl<>( snapshot.reversed().iterator() );
    }
}
