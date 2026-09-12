package com.javaspeak.designpatterns.go4.behavioural.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator over an immutable snapshot of a {@link ConcurrentLinkedList}.  The snapshot is taken
 * when {@link ConcurrentLinkedList#getSnapshotIterator()} is called; this iterator simply walks
 * the captured elements in insertion order.
 *
 * @author John Dickerson - 21 February 2020
 */
class SnapshotIteratorImpl<E> implements SnapshotIterator<E> {

    private final Iterator<E> snapshot;

    /**
     * Constructor.
     *
     * @param snapshot
     *      iterator over the captured elements, already in insertion order
     */
    SnapshotIteratorImpl( Iterator<E> snapshot ) {

        this.snapshot = snapshot;
    }


    @Override
    public boolean hasNext() {

        return snapshot.hasNext();
    }


    @Override
    public E next() {

        if ( !snapshot.hasNext() ) {

            throw new NoSuchElementException( "Snapshot iterator has no more elements" );
        }

        return snapshot.next();
    }
}
