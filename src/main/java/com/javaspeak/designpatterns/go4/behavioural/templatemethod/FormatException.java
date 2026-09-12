package com.javaspeak.designpatterns.go4.behavioural.templatemethod;

/**
 * This unchecked exception is thrown if html is not well formed.
 *
 * @author John Dickerson - 22 February 2020
 */
public class FormatException extends RuntimeException {

    private static final long serialVersionUID = -5550364727379819980L;

    /**
     * Creates a FormatException with a diagnostic message.
     *
     * @param message
     *      description of what is malformed in the html
     */
    public FormatException( String message ) {

        super( message );
    }


    /**
     * Creates a FormatException with a diagnostic message and the underlying cause.
     *
     * @param message
     *      description of what is malformed in the html
     *
     * @param cause
     *      the underlying cause of the failure
     */
    public FormatException( String message, Throwable cause ) {

        super( message, cause );
    }
}
