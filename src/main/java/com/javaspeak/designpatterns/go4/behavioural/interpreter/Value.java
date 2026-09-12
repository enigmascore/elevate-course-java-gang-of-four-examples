package com.javaspeak.designpatterns.go4.behavioural.interpreter;

/**
 * Value is a wrapper that wraps values of different types and provides helper methods to
 * retrieve the types in different formats.  Note that attempting to retrieve a value in the
 * wrong format results in an InterpreterException.
 *
 * @param object the wrapped value, which may be null
 *
 * @author John Dickerson - 21 February 2020
 */
public record Value( Object object ) {

    /**
     * If the object is an instance of String it is returned as is, else its toString() method
     * is called.  This method does not cause a ClassCastException if used incorrectly.
     *
     * @return the String value, or null if the wrapped value is null
     */
    public String getString() {

        return switch ( object ) {

            case String string -> string;
            case null -> null;
            default -> object.toString();
        };
    }


    /**
     * If the object is an instance of Integer it is returned as is, else an attempt is made to
     * convert the value to an Integer.
     *
     * @return the Integer value
     *
     * @throws InterpreterException if the wrapped value is null or cannot be represented as an
     *      Integer
     */
    public Integer getInteger() {

        return switch ( object ) {

            case Integer integer -> integer;
            case null -> throw new InterpreterException( "Cannot convert null to an Integer" );
            default -> {

                try {

                    yield Integer.valueOf( object.toString() );
                }
                catch ( NumberFormatException e ) {

                    throw new InterpreterException(
                            "Cannot convert \"" + object + "\" to an Integer", e );
                }
            }
        };
    }


    @Override
    public String toString() {

        return switch ( object ) {

            case null -> "null";
            case String string -> string;
            default -> object.toString();
        };
    }
}
