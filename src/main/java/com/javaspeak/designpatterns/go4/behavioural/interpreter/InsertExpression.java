package com.javaspeak.designpatterns.go4.behavioural.interpreter;

import java.util.List;

/**
 * Used for parsing insert "Object Query Language (oql)" statements of the form:
 * <p>
 * {@code insert into <className> <fieldName>=<fieldValue> ...}
 *
 * @author John Dickerson - 21 February 2020
 */
public final class InsertExpression implements Expression {

    private final String insertStatement;

    /**
     * The insert statement is passed through the constructor.
     *
     * @param insertStatement
     *      Insert statement to be parsed and executed
     */
    public InsertExpression( String insertStatement ) {

        this.insertStatement = insertStatement;
    }


    @Override
    public List<Value> interpret( InterpreterContext context ) {

        // split the statement into words using spaces and commas as separators
        String[] words = insertStatement.trim().split( "[,\\s]+" );

        // the first word is "insert", the second word is "into" and the third word is the
        // className of the object we wish to insert into
        if ( words.length < 3 || !words[0].equalsIgnoreCase( "insert" ) ||
                !words[1].equalsIgnoreCase( "into" ) ) {

            throw new InterpreterException(
                    "Insert statements must have the form \"insert into <className> " +
                            "<fieldName>=<fieldValue> ...\" but was: " + insertStatement );
        }

        String className = words[2];

        // all remaining words have the form fieldName=fieldValue.  Field values may not
        // contain spaces
        for ( int i = 3; i < words.length; i++ ) {

            String[] pair = words[i].split( "=", 2 );

            if ( pair.length != 2 || pair[0].isEmpty() ) {

                throw new InterpreterException(
                        "Malformed field assignment \"" + words[i] +
                                "\" in insert statement: " + insertStatement );
            }

            context.setFieldValue( className, pair[0], pair[1] );
        }

        // insert statements do not select values so an empty list is returned
        return List.of();
    }
}
