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
public class RequestProcessorImpl implements RequestProcessor {

    /**
     * Default constructor.
     */
    public RequestProcessorImpl() {

    }


    @Override
    public void processRequest( Request request, Response response ) {

        request.requestType().getCommand().execute( request, response );
    }
}
