package com.javaspeak.designpatterns.go4.behavioural.mediator;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This mediator is central to the Mediator pattern.
 * <p>
 * The ChatMediator mediates between the ChatUsers.  Only the ChatMediator has references to all
 * consumers and producers.  In this example the ChatUsers are both consumers and producers: they
 * can both send and receive messages.  A ChatUser sends and receives a message via the
 * ChatMediator.
 *
 * @author John Dickerson - 22 February 2020
 */
public class ChatMediatorImpl implements ChatMediator {

    private static final Logger logger = LoggerFactory.getLogger( ChatMediatorImpl.class );

    private final Set<String> groups = new ConcurrentSkipListSet<>();

    private final Map<String, Set<ChatUser>> chatUsersByGroup = new ConcurrentHashMap<>();

    /**
     * Creates a ChatMediatorImpl with no groups and no subscribed ChatUsers.
     */
    public ChatMediatorImpl() {

    }


    @Override
    public String[] getGroups() {

        return groups.toArray( new String[0] );
    }


    @Override
    public boolean createNewGroup( String groupName ) {

        return groups.add( groupName );
    }


    @Override
    public void subscribeToGroup( String group, ChatUser chatUser ) {

        // computeIfAbsent is atomic on ConcurrentHashMap: two ChatUsers racing to subscribe to
        // the same new group both end up in the same subscriber set, whereas a check-then-put
        // could drop one of the freshly created sets.
        chatUsersByGroup
                .computeIfAbsent( group, groupName -> new ConcurrentSkipListSet<>() )
                .add( chatUser );
    }


    @Override
    public void sendMessage( String groupName, ChatUser chatUser, String message ) {

        Set<ChatUser> groupChatUsers = chatUsersByGroup.get( groupName );

        if ( groupChatUsers == null || !groupChatUsers.contains( chatUser ) ) {

            logger.info(
                    "Dropping message from {} : group '{}' has no such subscriber",
                    chatUser.getUserId(), groupName );
            return;
        }

        for ( ChatUser groupChatUser : groupChatUsers ) {

            if ( !groupChatUser.equals( chatUser ) ) {

                groupChatUser.receiveMessage( groupName, chatUser.getUserId(), message );
            }
        }
    }
}
