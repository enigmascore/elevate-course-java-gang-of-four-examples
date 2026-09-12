package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Provides implementation for a Triangle.  Drawing the triangle returns it rendered as ASCII art.
 *
 * @author John Dickerson - 23 February 2020
 */
public class TriangleImpl implements Triangle {

    /**
     * Creates a TriangleImpl.
     */
    public TriangleImpl() {
    }


    @Override
    public String draw() {

        return """
                   x
                  x x
                 x   x
                xxxxxxx
                """;
    }


    @Override
    public String toString() {

        return draw();
    }
}
