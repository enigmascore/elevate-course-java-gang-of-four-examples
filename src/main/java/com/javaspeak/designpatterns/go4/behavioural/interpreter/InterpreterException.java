package com.javaspeak.designpatterns.go4.behavioural.interpreter;

/**
 * Thrown when an "Object Query Language (oql)" statement cannot be interpreted, for example
 * because it is badly formed or references a field which does not exist.
 * <p>
 * The exception is unchecked so that Expression implementations do not need to declare checked
 * exceptions; the message always describes the offending statement or value.
 *
 * @author John Dickerson - 11 September 2026
 */
public class InterpreterException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param message describes what could not be interpreted and why
     */
    public InterpreterException( String message ) {

        super( message );
    }


    /**
     * Constructor.
     *
     * @param message describes what could not be interpreted and why
     * @param cause the underlying cause of the failure
     */
    public InterpreterException( String message, Throwable cause ) {

        super( message, cause );
    }
}
