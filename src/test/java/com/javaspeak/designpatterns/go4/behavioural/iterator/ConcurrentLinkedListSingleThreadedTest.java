package com.javaspeak.designpatterns.go4.behavioural.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Single-threaded tests for {@link ConcurrentLinkedList}: insertion-order iteration, snapshot
 * isolation from later mutation, and the failure modes (removing a missing element, iterating
 * an empty list, calling next() past the end).
 *
 * @author John Dickerson - 21 February 2020
 */
public class ConcurrentLinkedListSingleThreadedTest {

    private <E> List<E> drain( SnapshotIterator<E> iterator ) {

        List<E> values = new ArrayList<>();

        while ( iterator.hasNext() ) {

            values.add( iterator.next() );
        }

        return values;
    }


    @Test
    public void snapshotIteratesInInsertionOrderAndIgnoresLaterMutation() {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();

        for ( String value : List.of( "1", "2", "3", "4", "5", "6", "7", "8" ) ) {

            list.add( value );
        }

        list.remove( "4" );
        list.remove( "6" );

        SnapshotIterator<String> snapshotIterator = list.getSnapshotIterator();

        // mutations after the snapshot was taken must not affect it
        list.add( "9" );
        list.remove( "8" );

        assertEquals( List.of( "1", "2", "3", "5", "7", "8" ), drain( snapshotIterator ) );

        // a fresh snapshot sees the later mutations
        assertEquals(
                List.of( "1", "2", "3", "5", "7", "9" ),
                drain( list.getSnapshotIterator() ) );
    }


    @Test
    public void removingMissingElementThrows() {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();
        list.add( "1" );

        assertThrows( NoSuchElementException.class, () -> list.remove( "no-such-element" ) );
    }


    @Test
    public void removingFromEmptyListThrows() {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();

        assertThrows( NoSuchElementException.class, () -> list.remove( "anything" ) );
    }


    @Test
    public void emptyListYieldsEmptySnapshot() {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();

        SnapshotIterator<String> snapshotIterator = list.getSnapshotIterator();

        assertFalse( snapshotIterator.hasNext() );
        assertThrows( NoSuchElementException.class, snapshotIterator::next );
    }


    @Test
    public void nextPastEndThrows() {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();
        list.add( "only" );

        SnapshotIterator<String> snapshotIterator = list.getSnapshotIterator();

        assertEquals( "only", snapshotIterator.next() );
        assertThrows( NoSuchElementException.class, snapshotIterator::next );
    }


    @Test
    public void removingDuplicateOnlyRemovesOneOccurrence() {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();
        list.add( "a" );
        list.add( "b" );
        list.add( "a" );

        list.remove( "a" );

        assertEquals( List.of( "a", "b" ), drain( list.getSnapshotIterator() ) );
    }


    @Test
    public void longListDoesNotOverflowTheStack() {

        ConcurrentLinkedList<Integer> list = new ConcurrentLinkedList<>();
        int size = 200_000;

        for ( int i = 0; i < size; i++ ) {

            list.add( i );
        }

        // both remove and snapshot used to recurse over the chain and blew the stack on long
        // lists; they are iterative now
        list.remove( 0 );

        assertEquals( size - 1, drain( list.getSnapshotIterator() ).size() );
    }
}
