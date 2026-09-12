package com.javaspeak.designpatterns.go4.structural.bridge;

/**
 * This interface is central to the Bridge pattern as it allows different implementations to be
 * plugged in for the shape building.
 * <p>
 * For example the TriangleBuilder can be easily switched for a SquareBuilder.
 *
 * @author John Dickerson - 24 February 2020
 */
public interface ShapeBuilder {

    /**
     * Builds a Shape.
     *
     * @return
     *      a Shape
     */
    Shape buildShape();
}
