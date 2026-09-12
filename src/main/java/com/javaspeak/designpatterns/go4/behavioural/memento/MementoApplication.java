package com.javaspeak.designpatterns.go4.behavioural.memento;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Text book description:
 * <p>
 * "Memento: Capture and restore an object's internal state. Without violating encapsulation,
 * capture and externalize an object's internal state so that the object can be restored to this
 * state later."
 * <p>
 * This example uses the Memento pattern for undo operations. In the Memento pattern there is a
 * Caretaker, Originator and Memento.
 * <p>
 * The Originator is the class that has state that needs to be rolled back to the previous
 * version.  The Caretaker manages the backup and restoration of state to the Originator.  The
 * Caretaker asks the Originator for a Memento object that holds a snapshot of its current state.
 * The Caretaker then adds this to a Deque (LIFO queue) it is managing.  Later on an undo
 * operation involves taking the last Memento put on the LIFO queue and passing it to the
 * Originator so that the Originator has the information to roll back its state to the last saved
 * version.
 * <p>
 * In this example we are modelling the saving operation of a text editor.  Every time the
 * document is saved the Caretaker (MementoApplication) asks the Originator (DocumentImpl) for a
 * DocumentMemento which it adds to a mementoDeque (LIFO queue).  After saving 3 times an undo
 * operation (restoreFromMemento()) is called three times.
 * <p>
 * After each undo operation the document state is added to the report so you can see that the
 * multiple undo has worked.  The third undo has nothing left to undo: the first saved state is
 * kept and the report says so, rather than rolling back past the first save.
 *
 * @author John Dickerson - 22 February 2020
 */
public class MementoApplication implements Caretaker {

    private final Document document = new DocumentImpl();
    private final Deque<Memento> mementoDeque = new ArrayDeque<>();

    /**
     * Creates the application with an empty document and an empty memento Deque.
     */
    public MementoApplication() {

    }


    @Override
    public void saveMemento( Memento memento ) {

        mementoDeque.offerLast( memento );
    }


    @Override
    public boolean restoreFromMemento() {

        // The last Memento on the Deque is the current state; the one before it is the state to
        // roll back to.  If fewer than two Mementos remain there is nothing to undo: undoing
        // past the first save would otherwise pass null to the Document.
        if ( mementoDeque.size() < 2 ) {

            return false;
        }

        mementoDeque.removeLast();
        document.restoreFromMemento( mementoDeque.peekLast() );
        return true;
    }


    private String describeDocument() {

        return document.getDocumentText() +
                " [" + document.getFontFamily() + " " + document.getFontSize() + "]";
    }


    /**
     * Runs the example application.  This application is modelling the saving operation of a
     * text editor. The document has font family, font size and document text properties and the
     * DocumentMemento captures all three, so undoing a save also rolls back font changes.
     * <p>
     * Every time we call saveDocumentText(..) on the Document we also call the saveMemento(..)
     * method to save the document state in a DocumentMemento object and place it at the end of a
     * Deque (LIFO queue).
     * <p>
     * Every time we want to undo a save we remove the last DocumentMemento off the end of the
     * Deque and read the last DocumentMemento still remaining on the end of the Deque.  We then
     * use this DocumentMemento to roll back the state of the Document.  Undoing when only the
     * first saved state remains reports that there is nothing to undo.
     *
     * @return a report of the document state after each save and each undo
     */
    public String runExample() {

        var report = new StringBuilder();

        document.saveFontFamily( "arial" );
        document.saveFontSize( 12 );
        document.saveDocumentText( "Hello World!" );
        saveMemento( document.getMemento() );
        report.append( "Saved: " ).append( describeDocument() ).append( '\n' );

        document.saveFontSize( 14 );
        document.saveDocumentText( document.getDocumentText() + " Saving a Second Time." );
        saveMemento( document.getMemento() );
        report.append( "Saved: " ).append( describeDocument() ).append( '\n' );

        document.saveDocumentText( document.getDocumentText() + " Saving a Third Time." );
        saveMemento( document.getMemento() );
        report.append( "Saved: " ).append( describeDocument() ).append( '\n' );

        for ( int i = 0; i < 3; i++ ) {

            if ( restoreFromMemento() ) {

                report.append( "Undo: " ).append( describeDocument() ).append( '\n' );
            }
            else {

                report.append( "Undo: nothing to undo, document remains: " )
                        .append( describeDocument() ).append( '\n' );
            }
        }

        return report.toString();
    }


    /**
     * Runs the example and prints the report of saves and undos.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        MementoApplication mementoApplication = new MementoApplication();
        System.out.println( mementoApplication.runExample() );
    }
}
