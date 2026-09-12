/**
 * Gang of Four Memento pattern.
 * <p>
 * Text book description:
 * <p>
 * "Memento: Capture and restore an object's internal state. Without violating encapsulation,
 * capture and externalize an object's internal state so that the object can be restored to this
 * state later."
 * <p>
 * In this example the Caretaker
 * ({@link com.javaspeak.designpatterns.go4.behavioural.memento.MementoApplication}) asks the
 * Originator ({@link com.javaspeak.designpatterns.go4.behavioural.memento.DocumentImpl}) for a
 * {@link com.javaspeak.designpatterns.go4.behavioural.memento.Memento} snapshot of its state
 * every time the document is saved, and pushes it onto a Deque (LIFO queue).  An undo operation
 * pops the Deque and hands the previous Memento back to the Originator to roll its state back.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.memento;
