package com.javaspeak.designpatterns.go4.behavioural.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * In the interpreter pattern, Expressions interact with the InterpreterContext to retrieve
 * information from it or update it.
 * <p>
 * Expressions are used to parse code snippets of interpreted language.  In this example we have
 * InsertExpression to parse "Object Query Language" insert statements and we have a
 * SelectExpression to parse select statements.
 * <p>
 * The InsertExpression updates the fields of registered objects in the InterpreterContext using
 * methods provided by the InterpreterContext.
 * <p>
 * Similarly the SelectExpression selects fields from the registered objects in the
 * InterpreterContext using methods provided by the InterpreterContext.
 * <p>
 * Note that the InterpreterContext holds RegisteredObjects in a map keyed by class name.  Each
 * RegisteredObject knows how to read and write its own fields by name, so no reflection is
 * needed to update or retrieve the fields.
 *
 * @author John Dickerson - 21 February 2020
 */
public class InterpreterContext {

    // Map to hold registered objects, keyed by class name
    private final Map<String, RegisteredObject> objects = new HashMap<>();

    /**
     * Default constructor.
     */
    public InterpreterContext() {

    }


    /**
     * Adds or updates an object in the registry, keyed by its class name.
     *
     * @param object
     *      The object to add or update
     */
    public void registerObject( RegisteredObject object ) {

        objects.put( object.getClass().getName(), object );
    }


    /**
     * Retrieves an object by className.
     *
     * @param className
     *      The className of the object to retrieve
     *
     * @return the registered object, or null if no object is registered under that class name
     */
    public RegisteredObject getObject( String className ) {

        return objects.get( className );
    }


    /**
     * Retrieves the value of a field from a registered object.
     *
     * @param className
     *      The class name of the object in the registry
     *
     * @param fieldName
     *      The fieldName we wish to retrieve the value for
     *
     * @return the field value
     *
     * @throws ObjectNotRegisteredException if no object is registered under that class name
     * @throws InterpreterException if the registered object has no field of that name
     */
    public Object getFieldValue( String className, String fieldName ) {

        return requireRegistered( className ).getFieldValue( fieldName );
    }


    /**
     * Sets a field value of a registered object.  The registered object converts the String
     * value to the field's type where necessary.
     *
     * @param className
     *      The class name of the object which has the field we wish to update
     *
     * @param fieldName
     *      The name of the field we wish to update
     *
     * @param fieldValue
     *      The value we will update the field with
     *
     * @throws ObjectNotRegisteredException if no object is registered under that class name
     * @throws InterpreterException if the registered object has no field of that name or the
     *      value cannot be converted to the field's type
     */
    public void setFieldValue( String className, String fieldName, String fieldValue ) {

        requireRegistered( className ).setFieldValue( fieldName, fieldValue );
    }


    private RegisteredObject requireRegistered( String className ) {

        RegisteredObject registeredObject = objects.get( className );

        // throw an error if there is no registered object of that className in the registry
        if ( registeredObject == null ) {

            throw new ObjectNotRegisteredException( className );
        }

        return registeredObject;
    }
}
