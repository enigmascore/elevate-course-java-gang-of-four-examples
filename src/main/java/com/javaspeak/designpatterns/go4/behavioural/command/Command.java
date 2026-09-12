package com.javaspeak.designpatterns.go4.behavioural.command;

/**
 * All Commands need to implement this interface.
 *
 * @author John Dickerson - 21 February 2020
 */
public interface Command {

    /**
     * The implementation of this method should retrieve a Map of request attributes from the
     * request.  It should then modify them and add them to a new map and call the
     * {@code handleResponse( Map<String, String> responseAttributes )} method to return them to
     * the calling code.
     *
     * @param request
     *      Request which has the map of attributes to process
     *
     * @param response
     *      Response which has the call back method handleResponse(..) to call
     */
    void execute( Request request, Response response );
}
