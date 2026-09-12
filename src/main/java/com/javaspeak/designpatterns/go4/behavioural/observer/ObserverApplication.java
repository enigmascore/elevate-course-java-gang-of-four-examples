package com.javaspeak.designpatterns.go4.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Text book description:
 * <p>
 * "Observer: A way of notifying change to a number of classes. Define a one-to-many dependency
 * between objects so that when one object changes state, all its dependents are notified and
 * updated automatically."
 * <p>
 * This example uses the Observer pattern.
 * <p>
 * In the Observer pattern there is one Subject (called "Observable" in some texts) and there are
 * many Listeners (called "Observers").  The names Subject and Listener are used here to avoid
 * clashing with the deprecated {@code java.util.Observable} / {@code java.util.Observer} JDK
 * types; the modern JDK equivalent of this pattern is {@link java.util.concurrent.Flow}.
 * <p>
 * The idea is that when something specific happens with the Subject all Listeners are notified
 * of this happening.
 * <p>
 * In this example the application itself is the Subject.  It adds three different Listeners to
 * its list of listeners using its addListener(..) method.
 * <p>
 * The Subject then calls notifyListeners( SubjectEvent subjectEvent ) to inform all subscribed
 * listeners of a new happening.  The happening is encapsulated in a MessageEvent record which
 * holds a message. The message is "Hello Everyone!".
 * <p>
 * Internally the notifyListeners(..) method loops through all its subscribed Listeners and
 * calls receiveSubjectEvent( SubjectEvent subjectEvent ) on each of them.
 * <p>
 * Each Listener then retrieves the message from the SubjectEvent and remembers it.
 *
 * @author John Dickerson - 22 February 2020
 */
public class ObserverApplication implements Subject {

    private final List<Listener> listeners = new ArrayList<>();

    /**
     * Creates the example application, initially with no subscribed listeners.
     */
    public ObserverApplication() {
    }


    @Override
    public void addListener( Listener listener ) {

        listeners.add( listener );
    }


    @Override
    public void removeListener( Listener listener ) {

        listeners.remove( listener );
    }


    @Override
    public void notifyListeners( SubjectEvent subjectEvent ) {

        for ( Listener listener : listeners ) {

            listener.receiveSubjectEvent( subjectEvent );
        }
    }


    /**
     * Runs the example: subscribes three listeners, publishes an event and reports which
     * listener received which message.
     *
     * @return a report of the messages each listener received
     */
    public String runExample() {

        // This class is the Subject

        // First we create some Listeners
        MessageListener listenerOne = new MessageListener( "ListenerOne" );
        MessageListener listenerTwo = new MessageListener( "ListenerTwo" );
        MessageListener listenerThree = new MessageListener( "ListenerThree" );

        // Next we subscribe those listeners so that they are observing the Subject. "Observing"
        // in this example means they are waiting for SubjectEvents. In other words the Listeners
        // are waiting for their receiveSubjectEvent(..) method to be called.
        addListener( listenerOne );
        addListener( listenerTwo );
        addListener( listenerThree );

        // The Subject (this class) loops through its subscribed listeners and calls
        // receiveSubjectEvent(..) on each of them. A SubjectEvent is passed to each of the
        // Listeners which has the message, "Hello Everyone!" in it.
        notifyListeners( new MessageEvent( "Hello Everyone!" ) );

        var report = new StringBuilder();

        for ( MessageListener listener :
                List.of( listenerOne, listenerTwo, listenerThree ) ) {

            report.append( listener.getListenerName() )
                    .append( " received " )
                    .append( listener.getReceivedMessages() )
                    .append( '\n' );
        }

        return report.toString();
    }


    /**
     * Runs the example and prints its output.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        ObserverApplication application = new ObserverApplication();
        System.out.println( application.runExample() );
    }
}
