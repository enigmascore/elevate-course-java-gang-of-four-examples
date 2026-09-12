package com.javaspeak.designpatterns.go4.behavioural.mediator;

/**
 * A ChatUser communicates with other ChatUsers using the mediator, ChatMediator. Only the
 * ChatMediator has reference to all ChatUsers.
 * <p>
 * The mediator design pattern is about the Mediator only having reference to all consumers and
 * producers.  In this example the ChatUsers are both consumers and producers: they can both send
 * and receive messages.
 * <p>
 * ChatUsers are kept by the ChatMediator in sorted sets, so every ChatUser is Comparable; the
 * ordering (and equality) of ChatUsers is by userId.
 *
 * @author John Dickerson - 21 February 2020
 */
public interface ChatUser extends Comparable<ChatUser> {

    /**
     * The userId of the ChatUser is the user name of the ChatUser
     *
     * @return userId
     */
    String getUserId();


    /**
     * The ChatUser calls this method to send messages to other ChatUsers
     *
     * @param group
     *      Group to send the message to
     *
     * @param message
     *      The message to send to the group
     */
    void sendMessage( String group, String message );


    /**
     * The ChatMediator calls this method to send a message to the ChatUser
     * which it has received from another user.
     *
     * @param group
     *      Group that the message belongs to
     *
     * @param userId
     *      The userId of the ChatUser sending the message
     *
     * @param message
     *      The message that is being sent to the ChatUser who has subscribed to the Group.
     */
    void receiveMessage( String group, String userId, String message );


    /**
     * A ChatUser calls this method to create a new Group.
     *
     * @param groupName
     *      The name of the group to create
     *
     * @return
     *      true if the group was created or false if the group already exists
     */
    boolean createNewGroup( String groupName );


    /**
     * Subscribe to the group
     *
     * @param groupName
     *      the group to subscribe to
     */
    void subscribeToGroup( String groupName );


    /**
     * Gets a list of the group Names that exist
     *
     * @return
     *      a list of the group names that exist
     */
    String[] getGroups();


    /**
     * Checks whether the groupName is in the array of group names
     *
     * @param groupNames
     *      array of group names
     *
     * @param groupName
     *      the group name to check for membership of the array of group names
     *
     * @return
     *      true if the groupName is in the array of group names or false if it is not
     */
    boolean groupExists( String[] groupNames, String groupName );
}
