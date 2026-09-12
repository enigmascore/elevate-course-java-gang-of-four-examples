package com.javaspeak.designpatterns.go4.behavioural.mediator;

import java.util.List;

/**
 * Text book description:
 * <p>
 * "Mediator: Defines simplified communication between classes. Define an object that encapsulates
 * how a set of objects interact. Mediator promotes loose coupling by keeping objects from
 * referring to each other explicitly, and it lets you vary their interaction independently."
 * <p>
 * In this example the ChatMediator mediates between the ChatUsers.  Only the ChatMediator has
 * references to all consumers and producers.  In this example the ChatUsers are both consumers
 * and producers: they can both send and receive messages.  A ChatUser sends and receives a
 * message via the ChatMediator.
 * <p>
 * Before a ChatUser can send a message a group must exist.  The ChatUser can check whether a
 * group exists and if it does not create one by calling the createNewGroup(..) method on the
 * ChatMediator. Once a group exists ChatUsers can subscribe to the group.  ChatUsers can then
 * send messages to the group by calling the sendMessage(..) method on the ChatMediator.  The
 * ChatMediator in return calls receiveMessage(..) on each of the other ChatUsers that is
 * subscribed to the group.
 *
 * @author John Dickerson - 21 February 2020
 */
public class MediatorApplication {

    /**
     * Creates the application.
     */
    public MediatorApplication() {

    }


    /**
     * Runs the example: three ChatUsers subscribe to a "kite surfing" group via the ChatMediator
     * and each sends a message to the group.  The report shows which messages each ChatUser
     * received; note that a ChatUser does not receive its own messages.
     *
     * @return a report of the messages each ChatUser received
     */
    public String runExample() {

        // The chatMediator to pass through the constructor of each ChatUser
        ChatMediator chatMediator = new ChatMediatorImpl();

        ChatUserImpl chatUserOne = new ChatUserImpl( chatMediator, "chatUserOne" );
        ChatUserImpl chatUserTwo = new ChatUserImpl( chatMediator, "chatUserTwo" );
        ChatUserImpl chatUserThree = new ChatUserImpl( chatMediator, "chatUserThree" );

        String[] groups = chatUserOne.getGroups();

        String groupName = "kite surfing";

        // ChatUserOne checks if the "kite surfing" group already exists
        // and creates one if it does not exist
        if ( !chatUserOne.groupExists( groups, groupName ) ) {

            chatUserOne.createNewGroup( groupName );
        }

        // three ChatUsers subscribe to the "kite surfing" group
        chatUserOne.subscribeToGroup( groupName );
        chatUserTwo.subscribeToGroup( groupName );
        chatUserThree.subscribeToGroup( groupName );

        // ChatUserOne sends a message to the group
        chatUserOne.sendMessage( groupName,
                "Kite meet at Shoreham, Saturday. Any one want to go?" );

        // ChatUserTwo sends a message to the group
        chatUserTwo.sendMessage( groupName, "Yes I would like to go" );

        // ChatUserThree sends a message to the group
        chatUserThree.sendMessage( groupName, "Count me in as well" );

        var report = new StringBuilder();

        for ( ChatUserImpl chatUser : List.of( chatUserOne, chatUserTwo, chatUserThree ) ) {

            report.append( chatUser.getUserId() ).append( " received:\n" );

            for ( String message : chatUser.getReceivedMessages() ) {

                report.append( "    " ).append( message ).append( '\n' );
            }
        }

        return report.toString();
    }


    /**
     * Runs the example and prints the report of received messages.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        MediatorApplication application = new MediatorApplication();
        System.out.println( application.runExample() );
    }
}
