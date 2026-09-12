package com.javaspeak.designpatterns.go4.behavioural.memento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the Memento pattern example: saved document states can be undone one at a time, undoing
 * past the first save leaves the document unchanged, and the memento also captures font state.
 *
 * @author John Dickerson - 11 September 2026
 */
public class MementoApplicationTest {

    @Test
    public void undoRollsBackToPreviouslySavedStates() {

        Document document = new DocumentImpl();

        document.saveDocumentText( "first" );
        Memento firstSave = document.getMemento();

        document.saveDocumentText( "second" );
        Memento secondSave = document.getMemento();

        document.saveDocumentText( "third" );

        document.restoreFromMemento( secondSave );
        assertEquals( "second", document.getDocumentText() );

        document.restoreFromMemento( firstSave );
        assertEquals( "first", document.getDocumentText() );
    }


    @Test
    public void mementoRoundTripRestoresTextAndFontState() {

        Document document = new DocumentImpl();

        document.saveFontFamily( "arial" );
        document.saveFontSize( 12 );
        document.saveDocumentText( "Hello World!" );

        Memento memento = document.getMemento();

        document.saveFontFamily( "helvetica" );
        document.saveFontSize( 14 );
        document.saveDocumentText( "Changed" );

        document.restoreFromMemento( memento );

        assertEquals( "Hello World!", document.getDocumentText() );
        assertEquals( "arial", document.getFontFamily() );
        assertEquals( 12, document.getFontSize() );
    }


    @Test
    public void runExampleShowsSavesUndosAndUndoPastFirstSave() {

        String report = new MementoApplication().runExample();

        assertTrue( report.contains(
                "Saved: Hello World! Saving a Second Time. Saving a Third Time. [arial 14]" ) );

        // first undo rolls back the third save
        assertTrue( report.contains(
                "Undo: Hello World! Saving a Second Time. [arial 14]" ) );

        // second undo rolls back the second save, including the font size change
        assertTrue( report.contains( "Undo: Hello World! [arial 12]" ) );

        // third undo has nothing left to undo and keeps the first saved state
        assertTrue( report.contains(
                "Undo: nothing to undo, document remains: Hello World! [arial 12]" ) );
    }


    @Test
    public void restoreFromMementoReportsWhetherAnythingWasUndone() {

        MementoApplication application = new MementoApplication();

        // nothing saved yet: nothing to undo
        assertFalse( application.restoreFromMemento() );

        Document document = new DocumentImpl();
        document.saveDocumentText( "only save" );
        application.saveMemento( document.getMemento() );

        // only the first save remains: still nothing to undo
        assertFalse( application.restoreFromMemento() );

        application.saveMemento( document.getMemento() );

        // two saved states: one undo is possible
        assertTrue( application.restoreFromMemento() );
        assertFalse( application.restoreFromMemento() );
    }
}
