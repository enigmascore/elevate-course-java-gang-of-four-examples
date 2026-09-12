package com.javaspeak.designpatterns.go4.behavioural.iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * Multi-threaded tests for {@link ConcurrentLinkedList}, covering the races the original
 * implementation lost: an add racing a head removal, and concurrent removals of adjacent
 * elements resurrecting a removed node.
 *
 * @author John Dickerson - 11 September 2026
 */
public class ConcurrentLinkedListConcurrencyTest {

    private static final int THREADS = 8;

    private List<String> drain( SnapshotIterator<String> iterator ) {

        List<String> values = new ArrayList<>();

        while ( iterator.hasNext() ) {

            values.add( iterator.next() );
        }

        return values;
    }


    private void runConcurrently( List<Runnable> tasks ) throws Exception {

        CountDownLatch startGate = new CountDownLatch( 1 );
        List<Future<?>> futures = new ArrayList<>();

        try ( ExecutorService executor = Executors.newFixedThreadPool( tasks.size() ) ) {

            for ( Runnable task : tasks ) {

                futures.add( executor.submit( () -> {

                    startGate.await();
                    task.run();
                    return null;
                } ) );
            }

            startGate.countDown();

            for ( Future<?> future : futures ) {

                future.get( 30, TimeUnit.SECONDS );
            }
        }
    }


    @Test
    public void concurrentAddsLoseNothing() throws Exception {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();
        int addsPerThread = 1_000;
        List<Runnable> tasks = new ArrayList<>();

        for ( int t = 0; t < THREADS; t++ ) {

            int thread = t;

            tasks.add( () -> {

                for ( int i = 0; i < addsPerThread; i++ ) {

                    list.add( thread + "-" + i );
                }
            } );
        }

        runConcurrently( tasks );

        List<String> values = drain( list.getSnapshotIterator() );
        assertEquals( THREADS * addsPerThread, values.size() );
        assertEquals( THREADS * addsPerThread, new HashSet<>( values ).size() );
    }


    @Test
    public void addRacingHeadRemovalLosesNothing() throws Exception {

        // The original implementation unlinked the head with a plain set() instead of a CAS,
        // so an add racing a head removal could be silently lost.
        for ( int round = 0; round < 200; round++ ) {

            ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();
            list.add( "victim" );

            runConcurrently( List.of(
                    () -> list.remove( "victim" ),
                    () -> list.add( "newcomer" ) ) );

            assertEquals( List.of( "newcomer" ), drain( list.getSnapshotIterator() ) );
        }
    }


    @Test
    public void concurrentRemovalOfAdjacentElementsResurrectsNothing() throws Exception {

        // The classic unmarked linked-list bug: unlinking B (A ==> B ==> C) and A concurrently
        // can resurrect one of them.  The deleted flag prevents that.
        for ( int round = 0; round < 200; round++ ) {

            ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();
            list.add( "c" );
            list.add( "b" );
            list.add( "a" );

            runConcurrently( List.of(
                    () -> list.remove( "a" ),
                    () -> list.remove( "b" ) ) );

            assertEquals( List.of( "c" ), drain( list.getSnapshotIterator() ) );
        }
    }


    @Test
    public void concurrentAddAndRemoveChurnEndsEmpty() throws Exception {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();
        int elementsPerThread = 500;
        List<Runnable> tasks = new ArrayList<>();

        for ( int t = 0; t < THREADS; t++ ) {

            int thread = t;

            tasks.add( () -> {

                for ( int i = 0; i < elementsPerThread; i++ ) {

                    String value = thread + "-" + i;
                    list.add( value );
                    list.remove( value );
                }
            } );
        }

        runConcurrently( tasks );

        assertEquals( List.of(), drain( list.getSnapshotIterator() ) );
    }


    @Test
    public void snapshotIsStableWhileListIsMutatedConcurrently() throws Exception {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();
        Set<String> stableValues = new HashSet<>();

        for ( int i = 0; i < 100; i++ ) {

            String value = "stable-" + i;
            stableValues.add( value );
            list.add( value );
        }

        List<Runnable> tasks = new ArrayList<>();
        List<List<String>> snapshots = new ArrayList<>();

        for ( int i = 0; i < THREADS / 2; i++ ) {

            List<String> snapshot = new ArrayList<>();
            snapshots.add( snapshot );
            tasks.add( () -> snapshot.addAll( drain( list.getSnapshotIterator() ) ) );

            int thread = i;

            tasks.add( () -> {

                for ( int j = 0; j < 500; j++ ) {

                    String value = "churn-" + thread + "-" + j;
                    list.add( value );
                    list.remove( value );
                }
            } );
        }

        runConcurrently( tasks );

        // Every snapshot must contain all the stable values exactly once, in insertion order;
        // churn values may or may not appear depending on timing, but nothing else may.
        for ( List<String> snapshot : snapshots ) {

            List<String> stableOnly = snapshot.stream()
                    .filter( stableValues::contains )
                    .toList();

            assertEquals( 100, stableOnly.size() );

            for ( String value : snapshot ) {

                assertTrue( value.startsWith( "stable-" ) || value.startsWith( "churn-" ) );
            }
        }
    }
}
