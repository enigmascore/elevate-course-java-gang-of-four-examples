package com.javaspeak.designpatterns.go4.behavioural.iterator;

import java.util.Iterator;

/**
 * An iterator over a snapshot of a collection: the elements it returns reflect the collection at
 * the moment the snapshot was taken, and are unaffected by later additions or removals.
 * <p>
 * Extends {@link java.util.Iterator} so a SnapshotIterator can be used anywhere a standard
 * iterator is expected, including the enhanced for loop (via {@link SnapshotIterable}).
 *
 * @param <E> the element type returned by this iterator
 *
 * @author John Dickerson - 21 February 2020
 */
public interface SnapshotIterator<E> extends Iterator<E> {
}
