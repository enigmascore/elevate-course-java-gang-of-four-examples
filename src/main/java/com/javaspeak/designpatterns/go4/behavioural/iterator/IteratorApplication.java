package com.javaspeak.designpatterns.go4.behavioural.iterator;

/**
 * Text book description:
 * <p>
 * "Iterator: The iterator pattern is used to provide a standard interface for traversing a
 * collection of items in an aggregate object without the need to understand its underlying
 * structure."
 * <p>
 * This example uses the Iterator pattern.
 * <p>
 * The aggregate object is {@link ConcurrentLinkedList}, a lock-free multi-threaded data
 * structure built on non-blocking CAS operations.  Callers traverse it through the
 * {@link SnapshotIterator} interface without knowing anything about its internal chain of
 * {@link LinkedElement}s.
 * <p>
 * The iterator returned is a snapshot iterator: it iterates over the list as it was at the
 * moment the iterator was taken, so elements added or removed afterwards do not affect the
 * iteration.  Because {@link SnapshotIterable} extends {@link Iterable}, the list works
 * directly in the enhanced for loop.
 *
 * @author John Dickerson - 21 February 2020
 */
public class IteratorApplication {

    /**
     * Creates the example application.
     */
    public IteratorApplication() {
    }


    /**
     * Runs the example: fills a ConcurrentLinkedList, takes a snapshot iterator, mutates the
     * list afterwards and shows that the snapshot is unaffected.
     *
     * @return a report of the iteration results
     */
    public String runExample() {

        ConcurrentLinkedList<String> list = new ConcurrentLinkedList<>();

        list.add( "one" );
        list.add( "two" );
        list.add( "three" );
        list.remove( "two" );

        // The snapshot is taken now: later mutations must not affect it
        SnapshotIterator<String> snapshotIterator = list.getSnapshotIterator();

        list.add( "four" );
        list.remove( "one" );

        var report = new StringBuilder( "Snapshot taken after removing \"two\": " );

        while ( snapshotIterator.hasNext() ) {

            report.append( snapshotIterator.next() ).append( ' ' );
        }

        report.append( "\nList contents now: " );

        // SnapshotIterable extends Iterable so the enhanced for loop works too
        for ( String element : list ) {

            report.append( element ).append( ' ' );
        }

        return report.toString();
    }


    /**
     * Runs the example and prints its output.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        IteratorApplication application = new IteratorApplication();
        System.out.println( application.runExample() );
    }
}
