package com.javaspeak.designpatterns.go4.structural.bridge;

/**
 * This abstract bridge class wraps the ShapeBuilder.  Calling buildShape() on
 * AbstractShapeBridge internally calls the buildShape() method of the underlying ShapeBuilder.
 * <p>
 * The idea with the bridge is that the implementation for the underlying ShapeBuilder can be
 * switched.  This example is using a TriangleBuilder but the TriangleBuilder could be swapped
 * for another shape builder.
 *
 * @author John Dickerson - 24 February 2020
 */
public abstract class AbstractShapeBridge {

    /** The pluggable ShapeBuilder implementation this bridge delegates to. */
    protected final ShapeBuilder shapeBuilder;

    /**
     * Constructor.
     * <p>
     * The constructor allows the implementation for the ShapeBuilder to be passed in.
     *
     * @param shapeBuilder the ShapeBuilder implementation this bridge delegates to
     */
    public AbstractShapeBridge( ShapeBuilder shapeBuilder ) {

        this.shapeBuilder = shapeBuilder;
    }


    /**
     * Defines the API for building a shape.  Internally calls the shapeBuilder to build the
     * shape.  Notice that the implementation for the shapeBuilder is pluggable.
     *
     * @return the Shape built by the underlying ShapeBuilder
     */
    public Shape buildShape() {

        return shapeBuilder.buildShape();
    }
}
