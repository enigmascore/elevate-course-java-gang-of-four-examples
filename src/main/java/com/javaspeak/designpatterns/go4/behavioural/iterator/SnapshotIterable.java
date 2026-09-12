package com.javaspeak.designpatterns.go4.behavioural.iterator;

/**
 * A collection which can hand out {@link SnapshotIterator}s.
 * <p>
 * Extends {@link java.lang.Iterable} so implementations can be used directly in the enhanced
 * for loop; each iteration then works on its own snapshot of the collection.
 *
 * @param <E> the element type of the collection
 *
 * @author John Dickerson - 21 February 2020
 */
public interface SnapshotIterable<E> extends Iterable<E> {

    /**
     * Takes a snapshot of the collection as it is now and returns an iterator over it.  Later
     * additions and removals do not affect the returned iterator.
     *
     * @return an iterator over the snapshot, in insertion order
     */
    SnapshotIterator<E> getSnapshotIterator();


    @Override
    default SnapshotIterator<E> iterator() {

        return getSnapshotIterator();
    }
}
