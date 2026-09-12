package com.javaspeak.designpatterns.go4.creational.abstractfactory;

/**
 * Square and Triangle implement Shape.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface Shape {

    /**
     * Draws a Shape.
     *
     * @return the ASCII art representation of the Shape
     */
    String drawShape();
}
