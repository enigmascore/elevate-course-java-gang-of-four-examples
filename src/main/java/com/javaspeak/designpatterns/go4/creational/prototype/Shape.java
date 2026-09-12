package com.javaspeak.designpatterns.go4.creational.prototype;

/**
 * Concrete base class which provides copy functionality and drawing functionality for a Shape.
 * <p>
 * Copies are made with a copy constructor instead of the legacy Cloneable / clone() mechanism.
 * Subclasses override the copy() method to call their own copy constructor so that a copy has
 * the same runtime type as its prototype.
 *
 * @author John Dickerson - 24 February 2020
 */
public class Shape {

    /**
     * The pixel representation of the Shape. Populated by subclass constructors.
     */
    protected String pixels;

    /**
     * Creates a Shape with no pixels. Subclasses populate the pixels in their own constructors.
     */
    public Shape() {

    }


    /**
     * Copy constructor which copies the pixels of the source Shape.
     *
     * @param source
     *      the Shape to copy
     */
    protected Shape( Shape source ) {

        this.pixels = source.pixels;
    }


    /**
     * Returns a copy of this Shape. Subclasses override this method to return a copy created
     * with their own copy constructor.
     *
     * @return a new Shape with the same pixels as this Shape
     */
    public Shape copy() {

        return new Shape( this );
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
