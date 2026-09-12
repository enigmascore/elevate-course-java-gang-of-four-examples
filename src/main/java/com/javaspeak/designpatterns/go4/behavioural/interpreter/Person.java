package com.javaspeak.designpatterns.go4.behavioural.interpreter;

/**
 * This is the test class we are using to try our interpreted "Object Query Language" on.  It
 * implements RegisteredObject so that the InterpreterContext can read and write its fields by
 * name without any reflection: the field name is simply matched in a switch expression.
 *
 * @author John Dickerson - 21 February 2020
 */
public class Person implements RegisteredObject {

    private String firstName;
    private String lastName;
    private Integer height;

    /**
     * Default constructor.  The fields are populated later by oql insert statements.
     */
    public Person() {

    }


    @Override
    public void setFieldValue( String fieldName, String fieldValue ) {

        switch ( fieldName ) {

            case "firstName" -> firstName = fieldValue;
            case "lastName" -> lastName = fieldValue;
            case "height" -> height = Integer.valueOf( fieldValue );
            default -> throw new InterpreterException(
                    "Person has no field named \"" + fieldName + "\"" );
        }
    }


    @Override
    public Object getFieldValue( String fieldName ) {

        return switch ( fieldName ) {

            case "firstName" -> firstName;
            case "lastName" -> lastName;
            case "height" -> height;
            default -> throw new InterpreterException(
                    "Person has no field named \"" + fieldName + "\"" );
        };
    }
}
