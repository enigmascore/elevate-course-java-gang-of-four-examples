package com.javaspeak.designpatterns.go4.behavioural.memento;

/**
 * The class taking care of managing the Mementos is called the Caretaker and should implement
 * this interface.  In this example the Caretaker is the MementoApplication class.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface Caretaker {

    /**
     * Adds the Memento to the end of a Deque (LIFO queue)
     *
     * @param memento
     *      The Memento to add to the end of the Deque (LIFO queue)
     */
    void saveMemento( Memento memento );


    /**
     * Removes the Memento on the end of the Deque (LIFO queue) and then reads the one still
     * remaining on the end of the Deque. Uses this DocumentMemento remaining at the end of the
     * queue to roll back the state of the Originator object (DocumentImpl).
     * <p>
     * If there is nothing left to undo (only the first saved state, or no saved state at all,
     * remains on the Deque) the Originator is left unchanged.
     *
     * @return
     *      true if a state was undone, or false if there was nothing to undo
     */
    boolean restoreFromMemento();
}
