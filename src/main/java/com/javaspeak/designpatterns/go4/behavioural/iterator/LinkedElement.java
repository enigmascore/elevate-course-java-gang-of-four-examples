package com.javaspeak.designpatterns.go4.behavioural.iterator;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * A node in the {@link ConcurrentLinkedList} chain.  Wraps the element being stored and holds a
 * reference to the next LinkedElement in the chain.
 * <p>
 * Removal is done in two steps: the node is first logically deleted by atomically setting its
 * {@code deleted} flag (this is the linearization point of a remove), and then unlinked from the
 * chain physically as a best-effort memory optimisation.  Traversals ignore nodes whose
 * {@code deleted} flag is set, so correctness never depends on the physical unlink.
 * <p>
 * The atomic operations on the volatile fields use {@link VarHandle}s, the modern replacement
 * for {@code AtomicReferenceFieldUpdater}.  A VarHandle is cheaper than wrapping every node
 * field in an {@code AtomicReference} because it adds no per-instance object overhead.
 *
 * @author John Dickerson - 21 February 2020
 */
class LinkedElement<E> {

    private static final VarHandle NEXT;
    private static final VarHandle DELETED;

    static {

        try {

            MethodHandles.Lookup lookup = MethodHandles.lookup();

            NEXT = lookup.findVarHandle(
                    LinkedElement.class, "nextLinkedElement", LinkedElement.class );

            DELETED = lookup.findVarHandle( LinkedElement.class, "deleted", boolean.class );
        }
        catch ( ReflectiveOperationException e ) {

            throw new ExceptionInInitializerError( e );
        }
    }

    // The element being stored
    final E object;

    // The next LinkedElement in the chain; updated atomically via the NEXT VarHandle
    volatile LinkedElement<E> nextLinkedElement;

    // true once the node has been logically removed; set atomically via the DELETED VarHandle
    volatile boolean deleted;

    LinkedElement( E object ) {

        this.object = object;
    }


    /**
     * Atomically marks this node as logically deleted.  Only one thread can win this CAS, which
     * makes it the linearization point of a remove: whichever thread succeeds owns the removal.
     *
     * @return true if this call marked the node; false if another thread already had
     */
    boolean markDeleted() {

        return DELETED.compareAndSet( this, false, true );
    }


    /**
     * Atomically replaces this node's next reference, expecting it to still hold the previously
     * read value.  Used for the best-effort physical unlink of a logically deleted successor.
     *
     * @param expectedNext
     *      the next reference read earlier by the caller
     *
     * @param newNext
     *      the replacement next reference
     *
     * @return true if the CAS succeeded
     */
    boolean casNext( LinkedElement<E> expectedNext, LinkedElement<E> newNext ) {

        return NEXT.compareAndSet( this, expectedNext, newNext );
    }
}
