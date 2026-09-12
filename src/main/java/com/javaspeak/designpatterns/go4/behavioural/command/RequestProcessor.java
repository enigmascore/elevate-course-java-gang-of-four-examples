package com.javaspeak.designpatterns.go4.behavioural.command;

/**
 * The application calling code calls the processRequest(..) method of RequestProcessor. The
 * implementation of processRequest retrieves the RequestType from the request and from the
 * RequestType gets the Command that is mapped to it.  It then calls the execute method of the
 * Command.
 * <p>
 * The execute method of Command in turn reads a map of attributes from the request, processes
 * them and creates a new map which is passed as an argument to the callback method
 * handleResponse(..) of the response.
 *
 * @author John Dickerson - 21 February 2020
 */
public interface RequestProcessor {

    /**
     * Delegates the request and response to a Command.  The request has a RequestType which
     * holds a reference to the Command.
     *
     * @param request
     *      The request holding a map of attributes and a RequestType which references the
     *      Command to execute.
     *
     * @param response
     *      The response which has a call back method called handleResponse(..) which a new map
     *      of processed attributes can be passed to.
     */
    void processRequest( Request request, Response response );
}
