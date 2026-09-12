package com.javaspeak.designpatterns.go4.behavioural.mediator;

/**
 * This mediator is central to the Mediator pattern.
 * <p>
 * The ChatMediator mediates between the ChatUsers.  Only the ChatMediator has references to
 * all consumers and producers.  In this example the ChatUsers are both consumers and producers:
 * they can both send and receive messages.  A ChatUser sends and receives a message via the
 * ChatMediator.
 *
 * @author John Dickerson - 21 February 2020
 */
public interface ChatMediator {

    /**
     * Returns all groups
     *
     * @return
     *      all groups
     */
    String[] getGroups();


    /**
     * Creates a new group
     *
     * @param groupName
     *      Group Name to create the group with
     *
     * @return
     *      true if the group was created. false indicates the group was not created because it
     *      exists already.
     */
    boolean createNewGroup( String groupName );


    /**
     * Subscribe to the group.  If the group does not exist create it and subscribe
     *
     * @param groupName
     *      GroupName to subscribe to
     *
     * @param chatUser
     *      ChatUser which is subscribing
     */
    void subscribeToGroup( String groupName, ChatUser chatUser );


    /**
     * Send message to group
     *
     * @param group
     *      Group to send message to
     *
     * @param chatUser
     *      ChatUser which is sending the message
     *
     * @param message
     *      the message to send to the group
     */
    void sendMessage( String group, ChatUser chatUser, String message );
}
