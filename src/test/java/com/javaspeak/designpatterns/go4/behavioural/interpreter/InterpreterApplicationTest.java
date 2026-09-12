package com.javaspeak.designpatterns.go4.behavioural.interpreter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests the Interpreter pattern example: oql insert statements update the registered object,
 * oql select statements read the values back out, and malformed oql is rejected with an
 * InterpreterException.
 *
 * @author John Dickerson - 11 September 2026
 */
public class InterpreterApplicationTest {

    private Interpreter buildInterpreterWithRegisteredPerson() {

        InterpreterContext interpreterContext = new InterpreterContext();
        interpreterContext.registerObject( new Person() );
        return new Interpreter( interpreterContext );
    }


    @Test
    public void insertAndSelectRoundTrip() {

        Interpreter interpreter = buildInterpreterWithRegisteredPerson();

        List<Value> values = interpreter.interpret(
                "insert into " + Person.class.getName() +
                        " firstName=John lastName=Dickerson height=180;" +
                        "select height, firstName, lastName from " +
                        Person.class.getName() + ";" );

        assertEquals( 3, values.size() );
        assertEquals( Integer.valueOf( 180 ), values.get( 0 ).getInteger() );
        assertEquals( "John", values.get( 1 ).getString() );
        assertEquals( "Dickerson", values.get( 2 ).getString() );
    }


    @Test
    public void statementWithUnknownKeywordIsRejected() {

        Interpreter interpreter = buildInterpreterWithRegisteredPerson();

        assertThrows(
                InterpreterException.class,
                () -> interpreter.interpret( "delete from " + Person.class.getName() + ";" ) );
    }


    @Test
    public void selectFromUnregisteredObjectIsRejected() {

        Interpreter interpreter = new Interpreter( new InterpreterContext() );

        assertThrows(
                ObjectNotRegisteredException.class,
                () -> interpreter.interpret(
                        "select firstName from " + Person.class.getName() + ";" ) );
    }


    @Test
    public void insertIntoUnknownFieldIsRejected() {

        Interpreter interpreter = buildInterpreterWithRegisteredPerson();

        assertThrows(
                InterpreterException.class,
                () -> interpreter.interpret(
                        "insert into " + Person.class.getName() + " shoeSize=44;" ) );
    }


    @Test
    public void valueConvertsBetweenFormats() {

        assertEquals( Integer.valueOf( 42 ), new Value( "42" ).getInteger() );
        assertEquals( "42", new Value( 42 ).getString() );
        assertEquals( "42", new Value( 42 ).toString() );
        assertThrows( InterpreterException.class, () -> new Value( "tall" ).getInteger() );
    }


    @Test
    public void runExampleReportsSelectedValues() {

        assertEquals( "180 John Dickerson", new InterpreterApplication().runExample() );
    }
}
