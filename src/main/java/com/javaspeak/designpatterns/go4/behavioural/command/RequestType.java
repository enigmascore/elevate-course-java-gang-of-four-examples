package com.javaspeak.designpatterns.go4.behavioural.command;

/**
 * Specifies the RequestType. The RequestType can be retrieved from the Request by the
 * RequestProcessor.  Inside the RequestType is a reference to the command to execute.
 *
 * @author John Dickerson - 21 February 2020
 */
public enum RequestType {

    /**
     * Capitalises all the attribute values.
     */
    CAPITALISE( new CapitaliseCommand() ),

    /**
     * Sorts the entries in the map by the attribute values.
     */
    SORT( new SortCommand() );

    // holds a reference to the command associated with this enum.  As the
    // reference is in an Enum the reference is a singleton.
    private final Command command;

    /**
     * Private constructor of enum.
     *
     * @param command the command to execute
     */
    RequestType( Command command ) {

        this.command = command;
    }


    /**
     * Retrieves the Command associated with this RequestType.
     *
     * @return the command to execute
     */
    public Command getCommand() {

        return this.command;
    }
}
