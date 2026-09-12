package com.javaspeak.designpatterns.go4.behavioural.command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This Command class reads in a map of request attributes from the request and for each of the
 * request attributes capitalises the value and returns them in a new map which is passed to the
 * handleResponse(..) method.
 *
 * @author John Dickerson - 21 February 2020
 */
public class CapitaliseCommand implements Command {

    /**
     * Default constructor.
     */
    public CapitaliseCommand() {

    }


    @Override
    public void execute( Request request, Response response ) {

        Map<String, String> responseAttributes = new LinkedHashMap<>();

        request.requestAttributes().forEach(
                ( key, value ) -> responseAttributes.put( key, value.toUpperCase() ) );

        response.handleResponse( responseAttributes );
    }
}
