package com.javaspeak.designpatterns.go4.behavioural.memento;

/**
 * The Originator is the class that needs having its state backed up or restored (DocumentImpl).
 * <p>
 * Every time the Document's text is saved the Caretaker (MementoApplication) calls getMemento() on
 * the Document to get a snapshot of its state in the form of a DocumentMemento. The
 * DocumentMemento is placed on the end of a Deque (LIFO queue).  Every time a text operation needs
 * to be undone, the Caretaker retrieves a DocumentMemento from the end of the Deque (LIFO queue)
 * and calls the restoreFromMemento(..) method on the Originator (DocumentImpl) so that the
 * Document can roll its state back to how it was last time it saved.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface Originator {

    /**
     * Asks the Originator (DocumentImpl) for a snapshot of its current state encapsulated in a
     * Memento (DocumentMemento).
     *
     * @return Memento (DocumentMemento)
     */
    Memento getMemento();


    /**
     * Asks the Originator (DocumentImpl) to roll back its state to the same state as specified in
     * the Memento (DocumentMemento)
     *
     * @param memento
     *      The Memento holding a copy of the state that needs to be set in the Originator
     *      (DocumentImpl)
     */
    void restoreFromMemento( Memento memento );
}
