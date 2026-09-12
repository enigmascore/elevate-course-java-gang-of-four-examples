package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Provides implementation for a Square.  Drawing the square returns it rendered as ASCII art.
 *
 * @author John Dickerson - 23 February 2020
 */
public class SquareImpl implements Square {

    /**
     * Creates a SquareImpl.
     */
    public SquareImpl() {
    }


    @Override
    public String draw() {

        return """
                xxxx
                x  x
                x  x
                xxxx
                """;
    }


    @Override
    public String toString() {

        return draw();
    }
}
