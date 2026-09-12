package com.javaspeak.designpatterns.go4.behavioural.command;

import java.util.Map;

/**
 * The RequestProcessor calls the execute(..) method of Command passing in the request and
 * response to it.
 * <p>
 * The Command to call the execute() method on is retrieved from the RequestType stored in the
 * request.
 *
 * @author John Dickerson - 21 February 2020
 */
public interface Request {

    /**
     * The RequestType holds the mapping to a Command.
     * <p>
     * The RequestProcessor uses the RequestType from a Request to retrieve the Command to call
     * execute(..) on.
     *
     * @return the RequestType which holds the mapping to a Command
     */
    RequestType requestType();


    /**
     * Retrieves a map of request attributes which are String key value pairs.
     * <p>
     * The idea is that the execute(..) method of Command will read the request attributes,
     * process them and then return them in a new map via the callback method handleResponse(..)
     * in the response argument of the execute(..) method.
     *
     * @return map of request attributes
     */
    Map<String, String> requestAttributes();
}
