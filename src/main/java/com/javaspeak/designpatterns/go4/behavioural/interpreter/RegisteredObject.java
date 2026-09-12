package com.javaspeak.designpatterns.go4.behavioural.interpreter;

/**
 * An object which can be registered with the InterpreterContext so that "Object Query Language
 * (oql)" statements can insert data into it and select data from it.
 * <p>
 * Implementations expose their fields by name through {@link #setFieldValue(String, String)}
 * and {@link #getFieldValue(String)}.  This keeps the InterpreterContext free of reflection: no
 * setAccessible(..) hacks are needed because each registered object knows how to read and write
 * its own fields.
 *
 * @author John Dickerson - 11 September 2026
 */
public interface RegisteredObject {

    /**
     * Sets the named field to the given value, converting the String to the field's type where
     * necessary.
     *
     * @param fieldName
     *      The name of the field to update
     *
     * @param fieldValue
     *      The value to update the field with, as parsed from the oql statement
     *
     * @throws InterpreterException if the object has no field of that name or the value cannot
     *      be converted to the field's type
     */
    void setFieldValue( String fieldName, String fieldValue );


    /**
     * Retrieves the current value of the named field.
     *
     * @param fieldName
     *      The name of the field to retrieve the value for
     *
     * @return the current value of the named field
     *
     * @throws InterpreterException if the object has no field of that name
     */
    Object getFieldValue( String fieldName );
}
