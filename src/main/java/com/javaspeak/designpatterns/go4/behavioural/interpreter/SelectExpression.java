package com.javaspeak.designpatterns.go4.behavioural.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * Used for parsing select "Object Query Language (oql)" statements of the form:
 * <p>
 * {@code select <fieldName>, <fieldName>, ... from <className>}
 *
 * @author John Dickerson - 21 February 2020
 */
public final class SelectExpression implements Expression {

    private final String selectStatement;

    /**
     * The select statement is passed through the constructor.
     *
     * @param selectStatement
     *      Select statement to be parsed and executed
     */
    public SelectExpression( String selectStatement ) {

        this.selectStatement = selectStatement;
    }


    @Override
    public List<Value> interpret( InterpreterContext context ) {

        // split the statement into words using spaces and commas as separators
        String[] words = selectStatement.trim().split( "[,\\s]+" );

        // the first word is "select"; the words up to "from" are the fieldNames to select and
        // the word after "from" is the className
        List<String> fieldNames = new ArrayList<>();
        int index = 1;

        while ( index < words.length && !words[index].equalsIgnoreCase( "from" ) ) {

            fieldNames.add( words[index] );
            index++;
        }

        if ( words.length < 4 || !words[0].equalsIgnoreCase( "select" ) ||
                fieldNames.isEmpty() || index + 1 >= words.length ) {

            throw new InterpreterException(
                    "Select statements must have the form \"select <fieldName>, ... from " +
                            "<className>\" but was: " + selectStatement );
        }

        String className = words[index + 1];

        // make a call to the InterpreterContext for each fieldName to retrieve the field value
        // from the object registered under the className
        List<Value> fieldValues = new ArrayList<>( fieldNames.size() );

        for ( String fieldName : fieldNames ) {

            fieldValues.add( new Value( context.getFieldValue( className, fieldName ) ) );
        }

        return fieldValues;
    }
}
