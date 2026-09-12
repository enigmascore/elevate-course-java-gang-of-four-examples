package com.javaspeak.designpatterns.go4.behavioural.interpreter;

/**
 * Thrown if we attempt to retrieve an object from the registry in the InterpreterContext when
 * the object is not there.
 *
 * @author John Dickerson - 21 February 2020
 */
public class ObjectNotRegisteredException extends InterpreterException {

    private static final long serialVersionUID = -2331430364438294926L;

    /**
     * Constructor.
     *
     * @param className the class name under which no object is registered
     */
    public ObjectNotRegisteredException( String className ) {

        super( "No object is registered with the InterpreterContext under the class name \"" +
                className + "\"" );
    }
}
