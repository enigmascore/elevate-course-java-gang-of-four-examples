package com.javaspeak.designpatterns.go4.creational.factorymethod;

/**
 * Shape interface which Square and Triangle implement.
 *
 * @author John Dickerson - 24 February 2020
 */
public interface Shape {

    /**
     * Draws the Shape.
     *
     * @return the ASCII art representation of the Shape
     */
    String draw();
}
