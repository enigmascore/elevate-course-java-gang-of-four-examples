package com.javaspeak.designpatterns.go4.structural.adapter;

/**
 * This class is the adapter.  We are providing a wrapper around the third party Shape (the
 * adaptee) so that when we call draw() on the application's Square interface (the target) we
 * are internally calling shape.drawSquare().
 *
 * @author John Dickerson - 24 February 2020
 */
public class ShapeAdapter implements Square {

    private final Shape shape;

    /**
     * Constructor that wraps the third party Shape being adapted.
     *
     * @param shape the third party Shape (the adaptee) to wrap
     */
    public ShapeAdapter( Shape shape ) {

        this.shape = shape;
    }


    @Override
    public String draw() {

        // The draw() method belongs to the Application API. It wraps the third party
        // drawSquare() method.
        return shape.drawSquare();
    }
}
