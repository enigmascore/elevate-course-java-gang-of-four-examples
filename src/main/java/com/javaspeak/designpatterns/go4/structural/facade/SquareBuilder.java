package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Defines a buildSquare method.  This is one of the internal interfaces which the ShapeFacade
 * hides from the outside world.
 *
 * @author John Dickerson - 23 February 2020
 */
public interface SquareBuilder {

    /**
     * Builds a Square.
     *
     * @return
     *      a Square
     */
    Square buildSquare();
}
