package com.javaspeak.designpatterns.go4.behavioural.command;

import java.util.Map;

/**
 * The Response is passed to the RequestProcessor's processRequest(..) method along with the
 * Request. The RequestProcessor in turn retrieves the RequestType from the Request and from the
 * RequestType retrieves the Command. The Request and Response is then passed to the Command's
 * execute method.
 * <p>
 * As a functional interface a Response is usually supplied as a lambda by the calling code.
 *
 * @author John Dickerson - 21 February 2020
 */
@FunctionalInterface
public interface Response {

    /**
     * The handleResponse(..) method is a callback method that is called by the Command when it
     * has completed processing the map of attributes retrieved from the Request.  The
     * handleResponse(..) method has a new map of response attributes passed to it.
     *
     * @param responseAttributes A map of response attributes
     */
    void handleResponse( Map<String, String> responseAttributes );
}
