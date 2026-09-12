package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Defines an API to the outside world.  Internally the implementation of the getSquare() and
 * getTriangle() methods will make method calls to internal interfaces.
 *
 * @author John Dickerson - 23 February 2020
 */
public interface ShapeFacade {

    /**
     * Retrieves a Square.
     *
     * @return a square
     */
    Square getSquare();


    /**
     * Retrieves a Triangle.
     *
     * @return a triangle
     */
    Triangle getTriangle();
}
