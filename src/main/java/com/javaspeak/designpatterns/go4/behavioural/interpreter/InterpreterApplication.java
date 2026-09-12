package com.javaspeak.designpatterns.go4.behavioural.interpreter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Text book description:
 * <p>
 * "Interpreter: A way to include language elements in a program. Given a language, define a
 * representation for its grammar along with an interpreter that uses the representation to
 * interpret sentences in the language."
 * <p>
 * This example uses the Interpreter pattern.
 * <p>
 * The interpreter pattern has an InterpreterContext which the interpreted language can interact
 * with.  This example uses "Object Query Language (oql)" as its interpreted language and the
 * InterpreterContext is used to register objects with it.  The idea is that once an object is
 * registered in the InterpreterContext, you can use oql to update the object with data or to
 * select data from it.
 * <p>
 * In the example we are using oql to insert some data into a Person and retrieve some data.
 * The data retrieved is returned as the report of runExample().
 * <p>
 * The mechanics of the Interpreter pattern in this example work as follows:
 * <p>
 * The interpreter first of all separates the oql into statements. It does this by looking for
 * a ";" separator between the statements. It then executes the statements one by one.
 * <p>
 * Note that the Interpreter uses Expression objects to help it parse data and call the right
 * methods.  In our example we have an InsertExpression and a SelectExpression which both
 * implement the sealed interface Expression.
 * <p>
 * If an oql statement starts with "insert" the interpreter knows to use the InsertExpression
 * to perform the parsing and call the setFieldValue(..) method on the InterpreterContext.
 * <p>
 * If however the oql statement starts with "select" the interpreter knows to use the
 * SelectExpression to perform the parsing and call the getFieldValue(..) method on the
 * InterpreterContext and add the selected values to the valuesToReturn list.
 *
 * @author John Dickerson - 21 February 2020
 */
public class InterpreterApplication {

    /**
     * Default constructor.
     */
    public InterpreterApplication() {

    }


    /**
     * Runs the example: registers a Person with the InterpreterContext, inserts data into it
     * with an oql insert statement, selects the data back out with an oql select statement and
     * reports the selected values.
     *
     * @return the selected values separated by spaces
     */
    public String runExample() {

        InterpreterContext interpreterContext = new InterpreterContext();

        // register an object with the interpreter which we wish to insert data into or select
        // data from
        interpreterContext.registerObject( new Person() );

        Interpreter interpreter = new Interpreter( interpreterContext );

        // Build up an insert statement and a select statement. Separate the statements by ";"
        String oql =
                "insert into " + Person.class.getName() +
                        " firstName=John lastName=Dickerson height=180;" +
                        "select height, firstName, lastName from " +
                        Person.class.getName() + ";";

        // Call the interpreter to execute the oql and return values
        List<Value> values = interpreter.interpret( oql );

        // Report the values retrieved
        return values.stream().map( Value::toString ).collect( Collectors.joining( " " ) );
    }


    /**
     * Runs the example and prints its report.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        InterpreterApplication application = new InterpreterApplication();
        System.out.println( application.runExample() );
    }
}
