package com.javaspeak.designpatterns.go4.behavioural.mediator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the Mediator pattern example: ChatUsers subscribed to a group receive messages sent to
 * that group (but not their own messages), while ChatUsers who are not members receive nothing.
 *
 * @author John Dickerson - 11 September 2026
 */
public class MediatorApplicationTest {

    @Test
    public void groupMembersReceiveGroupMessages() {

        ChatMediator chatMediator = new ChatMediatorImpl();

        ChatUserImpl alice = new ChatUserImpl( chatMediator, "alice" );
        ChatUserImpl bob = new ChatUserImpl( chatMediator, "bob" );

        alice.createNewGroup( "surfing" );
        alice.subscribeToGroup( "surfing" );
        bob.subscribeToGroup( "surfing" );

        alice.sendMessage( "surfing", "Anyone up for the beach?" );

        assertEquals(
                List.of( "alice [surfing]: Anyone up for the beach?" ),
                bob.getReceivedMessages() );

        // the sender does not receive its own message
        assertEquals( List.of(), alice.getReceivedMessages() );
    }


    @Test
    public void nonMembersDoNotReceiveGroupMessages() {

        ChatMediator chatMediator = new ChatMediatorImpl();

        ChatUserImpl alice = new ChatUserImpl( chatMediator, "alice" );
        ChatUserImpl bob = new ChatUserImpl( chatMediator, "bob" );
        ChatUserImpl outsider = new ChatUserImpl( chatMediator, "outsider" );

        alice.createNewGroup( "surfing" );
        alice.subscribeToGroup( "surfing" );
        bob.subscribeToGroup( "surfing" );

        alice.sendMessage( "surfing", "Members only" );

        assertEquals( List.of( "alice [surfing]: Members only" ), bob.getReceivedMessages() );
        assertEquals( List.of(), outsider.getReceivedMessages() );
    }


    @Test
    public void sendingToGroupWithNoSubscribersDeliversNothing() {

        ChatMediator chatMediator = new ChatMediatorImpl();

        ChatUserImpl alice = new ChatUserImpl( chatMediator, "alice" );
        ChatUserImpl bob = new ChatUserImpl( chatMediator, "bob" );

        alice.createNewGroup( "empty group" );

        // alice never subscribed, so the message is dropped rather than throwing
        alice.sendMessage( "empty group", "Hello?" );

        assertEquals( List.of(), alice.getReceivedMessages() );
        assertEquals( List.of(), bob.getReceivedMessages() );
    }


    @Test
    public void groupExistsChecksTheGroupNames() {

        ChatMediator chatMediator = new ChatMediatorImpl();
        ChatUserImpl alice = new ChatUserImpl( chatMediator, "alice" );

        alice.createNewGroup( "surfing" );

        assertTrue( alice.groupExists( alice.getGroups(), "surfing" ) );
        assertTrue( !alice.groupExists( alice.getGroups(), "chess" ) );
    }


    @Test
    public void runExampleReportsDeliveredMessages() {

        String report = new MediatorApplication().runExample();

        assertTrue( report.contains( "chatUserOne received:" ) );
        assertTrue( report.contains(
                "chatUserOne [kite surfing]: "
                        + "Kite meet at Shoreham, Saturday. Any one want to go?" ) );
        assertTrue( report.contains( "chatUserTwo [kite surfing]: Yes I would like to go" ) );
        assertTrue( report.contains( "chatUserThree [kite surfing]: Count me in as well" ) );
    }
}
