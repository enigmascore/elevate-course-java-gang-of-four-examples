package com.javaspeak.designpatterns.go4.behavioural.memento;

/**
 * A Memento saves a snapshot state of the Originator (DocumentImpl). The Caretaker
 * (MementoApplication) places the Memento on the end of a Deque (LIFO queue) so that it can at a
 * later stage be used in an undo operation.
 * <p>
 * The interface is sealed: the only permitted implementation is the DocumentMemento record nested
 * inside DocumentImpl, so the Originator remains the only class able to create snapshots of its
 * state.
 *
 * @author John Dickerson - 22 February 2020
 */
public sealed interface Memento permits DocumentImpl.DocumentMemento {

}
