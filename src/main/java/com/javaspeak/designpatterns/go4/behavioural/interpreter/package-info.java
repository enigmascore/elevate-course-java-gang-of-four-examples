/**
 * Gang of Four Interpreter pattern.
 * <p>
 * Text book description:
 * <p>
 * "Interpreter: A way to include language elements in a program. Given a language, define a
 * representation for its grammar along with an interpreter that uses the representation to
 * interpret sentences in the language."
 * <p>
 * In this example an
 * {@link com.javaspeak.designpatterns.go4.behavioural.interpreter.Interpreter} parses "Object
 * Query Language (oql)" into statements and delegates each statement to an
 * {@link com.javaspeak.designpatterns.go4.behavioural.interpreter.Expression}
 * (an InsertExpression or a SelectExpression).  The Expressions interact with the
 * {@link com.javaspeak.designpatterns.go4.behavioural.interpreter.InterpreterContext} which
 * holds a registry of
 * {@link com.javaspeak.designpatterns.go4.behavioural.interpreter.RegisteredObject}s to insert
 * data into the registered objects or to select data from them.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.interpreter;
