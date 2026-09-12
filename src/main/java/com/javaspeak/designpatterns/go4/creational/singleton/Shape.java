package com.javaspeak.designpatterns.go4.creational.singleton;

/**
 * Immutable base class for Shapes built by the ShapeManager singleton. The pixel representation
 * of a Shape is fixed at construction time.
 *
 * @author John Dickerson - 24 February 2020
 */
public abstract class Shape {

    private final String pixels;

    /**
     * Creates a Shape drawn with the given pixels.
     *
     * @param pixels
     *      the pixel representation of the Shape
     */
    protected Shape( String pixels ) {

        this.pixels = pixels;
    }


    /**
     * Draws the pixel representation of the Shape.
     *
     * @return the pixel representation of the Shape
     */
    public String draw() {

        return pixels;
    }
}
