package com.javaspeak.designpatterns.go4.behavioural.command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This Command class reads in a map of request attributes from the request, sorts the entries by
 * their attribute values and returns them in a new insertion-ordered map which is passed to the
 * handleResponse(..) method.
 * <p>
 * The entries are sorted as a stream of map entries using
 * {@code Map.Entry.comparingByValue()}, so duplicate values are naturally allowed and no
 * contract-violating Comparator tricks are needed.
 *
 * @author John Dickerson - 21 February 2020
 */
public class SortCommand implements Command {

    /**
     * Default constructor.
     */
    public SortCommand() {

    }


    @Override
    public void execute( Request request, Response response ) {

        Map<String, String> responseAttributes = new LinkedHashMap<>();

        request.requestAttributes().entrySet().stream()
                .sorted( Map.Entry.comparingByValue() )
                .forEachOrdered(
                        entry -> responseAttributes.put( entry.getKey(), entry.getValue() ) );

        response.handleResponse( responseAttributes );
    }
}
