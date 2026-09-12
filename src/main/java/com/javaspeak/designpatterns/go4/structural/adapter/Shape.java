package com.javaspeak.designpatterns.go4.structural.adapter;

/**
 * The adaptee interface: the third party interface which we are adapting.  The application
 * cannot change this interface, so the ShapeAdapter wraps it behind the application's own
 * Square interface.
 *
 * @author John Dickerson - 24 February 2020
 */
public interface Shape {

    /**
     * Draws a square in the third party's own way.
     *
     * @return the drawn square as ASCII art
     */
    String drawSquare();
}
