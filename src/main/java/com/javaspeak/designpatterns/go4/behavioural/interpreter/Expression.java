package com.javaspeak.designpatterns.go4.behavioural.interpreter;

import java.util.List;

/**
 * Typically an interpreted language encapsulates the parsing into different expressions.  In
 * this example all Expressions need to implement this sealed interface, and the only
 * implementations are InsertExpression and SelectExpression.
 * <p>
 * The interpreter parses the "Object Query Language (oql)" into statements and then checks
 * which expression to use to parse each statement. For example the InsertExpression is used for
 * parsing oql which is inserting data into objects and the SelectExpression is used for parsing
 * oql which is used for retrieving data from objects.
 *
 * @author John Dickerson - 21 February 2020
 */
public sealed interface Expression permits InsertExpression, SelectExpression {

    /**
     * Classes implementing Expression should pass the oql statement to parse through their
     * constructor.
     *
     * @param context
     *      The InterpreterContext which holds a registry of objects.
     *
     * @return the list of Values selected by the statement.  The InsertExpression returns an
     *      empty list while the SelectExpression returns a list of Value.
     *
     * @throws InterpreterException if the oql statement is not well formed or references an
     *      object or field which does not exist
     */
    List<Value> interpret( InterpreterContext context );
}
