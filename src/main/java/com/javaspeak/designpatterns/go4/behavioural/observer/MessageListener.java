package com.javaspeak.designpatterns.go4.behavioural.observer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A listener which remembers every message it receives, so that the delivery of events can be
 * inspected (and unit tested) after the fact.  Receipt of each event is also logged.
 *
 * @author John Dickerson - 22 February 2020
 */
public class MessageListener implements Listener {

    private static final Logger logger = LoggerFactory.getLogger( MessageListener.class );

    private final String listenerName;
    private final List<String> receivedMessages = new ArrayList<>();

    /**
     * Creates a listener with the given name.
     *
     * @param listenerName
     *      the name identifying this listener
     */
    public MessageListener( String listenerName ) {

        this.listenerName = listenerName;
    }


    @Override
    public void receiveSubjectEvent( SubjectEvent subjectEvent ) {

        receivedMessages.add( subjectEvent.message() );
        logger.info( "{} received event {}", listenerName, subjectEvent.message() );
    }


    /**
     * Returns the name identifying this listener.
     *
     * @return the listener name
     */
    public String getListenerName() {

        return listenerName;
    }


    /**
     * Returns the messages received so far, in the order they were received.
     *
     * @return an unmodifiable view of the received messages
     */
    public List<String> getReceivedMessages() {

        return List.copyOf( receivedMessages );
    }
}
