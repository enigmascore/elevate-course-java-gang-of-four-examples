package com.javaspeak.designpatterns.go4.behavioural.mediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A ChatUser communicates with other ChatUsers using the mediator, ChatMediator. Only the
 * ChatMediator has reference to all ChatUsers.
 * <p>
 * The mediator design pattern is about the Mediator only having reference to all consumers and
 * producers.  In this example the ChatUsers are both consumers and producers: they can both
 * send and receive messages.
 * <p>
 * This implementation remembers every message it receives, so that delivery of messages can be
 * inspected (and unit tested) after the fact.  Receipt of each message is also logged.
 * <p>
 * equals(..), hashCode() and compareTo(..) are all consistent with each other: they are based
 * on the userId only.
 *
 * @author John Dickerson - 22 February 2020
 */
public class ChatUserImpl implements ChatUser {

    private static final Logger logger = LoggerFactory.getLogger( ChatUserImpl.class );

    private final String userId;
    private final ChatMediator chatMediator;
    private final List<String> receivedMessages = new ArrayList<>();

    /**
     * The ChatMediator is passed to each ChatUser implementation. The ChatUser makes calls to the
     * ChatMediator to send messages to other ChatUsers
     *
     * @param chatMediator
     *      The ChatMediator holds references to other ChatUsers
     *
     * @param userId
     *      The userId of this ChatUser
     */
    public ChatUserImpl( ChatMediator chatMediator, String userId ) {

        this.chatMediator = chatMediator;
        this.userId = userId;
    }


    @Override
    public String getUserId() {

        return userId;
    }


    @Override
    public void sendMessage( String groupName, String message ) {

        chatMediator.sendMessage( groupName, this, message );
    }


    @Override
    public void receiveMessage( String groupName, String userId, String message ) {

        receivedMessages.add( userId + " [" + groupName + "]: " + message );
        logger.info(
                "{}> Received message from {} [group: {}]: {}",
                this.userId, userId, groupName, message );
    }


    @Override
    public boolean createNewGroup( String group ) {

        return chatMediator.createNewGroup( group );
    }


    @Override
    public void subscribeToGroup( String groupName ) {

        chatMediator.subscribeToGroup( groupName, this );
    }


    @Override
    public String[] getGroups() {

        return chatMediator.getGroups();
    }


    @Override
    public boolean groupExists( String[] groupNames, String groupName ) {

        return Arrays.asList( groupNames ).contains( groupName );
    }


    /**
     * Returns the messages received so far, in the order they were received.  Each entry has the
     * form "senderUserId [groupName]: message".
     *
     * @return an unmodifiable view of the received messages
     */
    public List<String> getReceivedMessages() {

        return List.copyOf( receivedMessages );
    }


    @Override
    public int hashCode() {

        return Objects.hashCode( userId );
    }


    @Override
    public boolean equals( Object obj ) {

        return obj instanceof ChatUser other && Objects.equals( userId, other.getUserId() );
    }


    @Override
    public int compareTo( ChatUser other ) {

        return userId.compareTo( other.getUserId() );
    }
}
