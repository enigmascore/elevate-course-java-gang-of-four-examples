package com.javaspeak.designpatterns.go4.structural.bridge;

/**
 * The TriangleBuilder is the pluggable ShapeBuilder in this Bridge pattern.  It can be readily
 * switched for another ShapeBuilder.
 * <p>
 * ShapeBridgeImpl passes the TriangleBuilder to its super class, AbstractShapeBridge, via its
 * constructor.
 *
 * @author John Dickerson - 24 February 2020
 */
public class TriangleBuilder implements ShapeBuilder {

    /**
     * Creates a TriangleBuilder.
     */
    public TriangleBuilder() {
    }


    @Override
    public Shape buildShape() {

        return new Triangle();
    }
}
