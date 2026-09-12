package com.javaspeak.designpatterns.go4.behavioural.observer;

/**
 * A SubjectEvent is an encapsulation of an event that has occurred in the Subject which its
 * listeners have subscribed to (are listening to).
 * <p>
 * In this example the SubjectEvent has a message in it. It could also however have other
 * attributes such as an event type.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface SubjectEvent {

    /**
     * The SubjectEvent has a message in it.
     *
     * @return the message describing the event
     */
    String message();
}
