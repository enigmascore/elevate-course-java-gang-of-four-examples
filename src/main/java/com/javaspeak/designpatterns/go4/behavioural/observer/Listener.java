package com.javaspeak.designpatterns.go4.behavioural.observer;

/**
 * Listeners are interested in receiving SubjectEvents when a specific happening occurs in the
 * Subject.
 * <p>
 * This role is called "Observer" in some texts; it is named Listener here to avoid clashing
 * with the deprecated {@code java.util.Observer}.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface Listener {

    /**
     * This method is called on each of the subscribed listeners when the Subject executes its
     * notifyListeners( SubjectEvent subjectEvent ) method.
     *
     * @param subjectEvent
     *      The SubjectEvent to pass to the listener
     */
    void receiveSubjectEvent( SubjectEvent subjectEvent );
}
