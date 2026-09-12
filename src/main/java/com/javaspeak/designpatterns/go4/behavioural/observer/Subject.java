package com.javaspeak.designpatterns.go4.behavioural.observer;

/**
 * The class which is having its state observed implements this interface.
 * <p>
 * When a certain event happens the Subject will notify all its subscribed listeners that the
 * event has occurred.
 * <p>
 * This role is called "Observable" in some texts; it is named Subject here to avoid clashing
 * with the deprecated {@code java.util.Observable}. The modern JDK equivalent of this pattern
 * is {@link java.util.concurrent.Flow}.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface Subject {

    /**
     * Adds a subscribed listener to the subject's list of listeners.  The subscribed listeners
     * can be said from that point in time to be listening to SubjectEvents.
     *
     * @param listener
     *      The listener which has subscribed to the SubjectEvents
     */
    void addListener( Listener listener );


    /**
     * Removes a previously subscribed listener so that it no longer receives SubjectEvents.
     *
     * @param listener
     *      The listener to unsubscribe
     */
    void removeListener( Listener listener );


    /**
     * The Subject calls this method to inform all its subscribed listeners that a SubjectEvent
     * has occurred.
     *
     * @param subjectEvent
     *      The SubjectEvent to pass to all subscribed listeners. Internally the
     *      notifyListeners(..) method loops through all its subscribed listeners and calls the
     *      receiveSubjectEvent(..) method on each of them.
     */
    void notifyListeners( SubjectEvent subjectEvent );
}
