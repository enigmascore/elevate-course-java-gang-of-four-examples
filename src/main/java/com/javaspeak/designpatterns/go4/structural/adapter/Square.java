package com.javaspeak.designpatterns.go4.structural.adapter;

/**
 * The target interface: this is the interface that the Application (AdapterApplication) expects
 * to call.
 * <p>
 * There is no implementation for Square and we want to use the drawSquare() implementation of
 * Shape.
 * <p>
 * We create an adapter called ShapeAdapter which implements Square so that code in the
 * Application does not have to change.  Internally the ShapeAdapter makes a call to the
 * drawSquare() method of Shape.  Shape and ShapeImpl are third party legacy code (the adaptee
 * side of the pattern).
 *
 * @author John Dickerson - 24 February 2020
 */
public interface Square {

    /**
     * This draw() method is called by the Application.
     *
     * @return the drawn square as ASCII art
     */
    String draw();
}
