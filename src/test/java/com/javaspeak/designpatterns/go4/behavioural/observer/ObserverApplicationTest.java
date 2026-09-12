package com.javaspeak.designpatterns.go4.behavioural.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the Observer pattern example: subscribed listeners receive published events, and
 * removed listeners do not.
 *
 * @author John Dickerson - 11 September 2026
 */
public class ObserverApplicationTest {

    @Test
    public void subscribedListenersReceiveEvents() {

        ObserverApplication subject = new ObserverApplication();
        MessageListener listenerOne = new MessageListener( "ListenerOne" );
        MessageListener listenerTwo = new MessageListener( "ListenerTwo" );

        subject.addListener( listenerOne );
        subject.addListener( listenerTwo );

        subject.notifyListeners( new MessageEvent( "Hello Everyone!" ) );

        assertEquals( List.of( "Hello Everyone!" ), listenerOne.getReceivedMessages() );
        assertEquals( List.of( "Hello Everyone!" ), listenerTwo.getReceivedMessages() );
    }


    @Test
    public void removedListenerStopsReceivingEvents() {

        ObserverApplication subject = new ObserverApplication();
        MessageListener listener = new MessageListener( "Listener" );

        subject.addListener( listener );
        subject.notifyListeners( new MessageEvent( "first" ) );

        subject.removeListener( listener );
        subject.notifyListeners( new MessageEvent( "second" ) );

        assertEquals( List.of( "first" ), listener.getReceivedMessages() );
    }


    @Test
    public void runExampleReportsAllListeners() {

        String report = new ObserverApplication().runExample();

        assertTrue( report.contains( "ListenerOne received [Hello Everyone!]" ) );
        assertTrue( report.contains( "ListenerTwo received [Hello Everyone!]" ) );
        assertTrue( report.contains( "ListenerThree received [Hello Everyone!]" ) );
    }
}
