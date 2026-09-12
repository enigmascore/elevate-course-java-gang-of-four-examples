package com.javaspeak.designpatterns.go4.behavioural.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * The interpreter class is responsible for holding a reference to the InterpreterContext and
 * for determining which Expressions to use for parsing the interpreted language.  The
 * interpreter parses "Object Query Language (oql)" into statements and then executes the
 * statements. Different Expression objects are used for parsing different kinds of statements.
 * <p>
 * For example an InsertExpression is used for parsing oql insert statements and a
 * SelectExpression is used for parsing oql select statements.
 * <p>
 * In our example the InterpreterContext registers objects and allows us to update their values
 * and select values from them by making method calls.  The Expression objects,
 * InsertExpression and SelectExpression, make calls to the InterpreterContext to update the
 * registered objects with new values or to retrieve values.
 *
 * @author John Dickerson - 21 February 2020
 */
public class Interpreter {

    // Context holding registered objects
    private final InterpreterContext interpreterContext;

    /**
     * Constructor to pass in the context.
     *
     * @param interpreterContext the context holding the registered objects
     */
    public Interpreter( InterpreterContext interpreterContext ) {

        this.interpreterContext = interpreterContext;
    }


    /**
     * Performs the following steps:
     * <ul>
     *      <li>
     *          Parses "Object Query Language (oql)" into statements by looking
     *          for ";" separators.
     *      </li>
     *      <li>
     *          Checks the first word of each statement to determine which Expression to use to
     *          parse the statement. If a "select" is present uses a SelectExpression. If an
     *          "insert" is present uses an InsertExpression.
     *      </li>
     *      <li>
     *          Interprets the Expression. The Expression in turn parses the oql and makes calls
     *          to the InterpreterContext to either insert data into the registered objects or
     *          retrieve data from them.
     *      </li>
     * </ul>
     *
     * @param oql
     *      The language to interpret
     *
     * @return the list of Values selected by all select statements in the oql.  Note that
     *      multiple select statements can be present but only one combined list of values is
     *      returned.
     *
     * @throws InterpreterException if a statement does not start with "select" or "insert", is
     *      not well formed, or references an object or field which does not exist
     */
    public List<Value> interpret( String oql ) {

        // separate statements by ";"
        String[] statements = oql.split( ";" );

        List<Value> valuesToReturn = new ArrayList<>();

        // For each statement choose an Expression to do the work
        for ( String statement : statements ) {

            statement = statement.trim();

            // if the statement starts with "select" use a SelectExpression to parse the
            // statement.  If it starts with "insert" use an InsertExpression to parse the
            // statement
            if ( statement.toLowerCase().startsWith( "select" ) ) {

                Expression expression = new SelectExpression( statement );
                valuesToReturn.addAll( expression.interpret( interpreterContext ) );
            }
            else if ( statement.toLowerCase().startsWith( "insert" ) ) {

                Expression expression = new InsertExpression( statement );
                expression.interpret( interpreterContext );
            }
            else {

                // the statement starts with an unexpected word so throw an error
                throw new InterpreterException(
                        "Statements must start with \"select\" or \"insert\" but was: " +
                                statement );
            }
        }

        return valuesToReturn;
    }
}
