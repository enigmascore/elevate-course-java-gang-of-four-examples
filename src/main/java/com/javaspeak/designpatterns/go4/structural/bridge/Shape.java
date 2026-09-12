package com.javaspeak.designpatterns.go4.structural.bridge;

/**
 * Interface used for Shape.
 * <p>
 * The interface is used both by ShapeBuilder implementations and the AbstractShapeBridge.
 *
 * @author John Dickerson - 24 February 2020
 */
public interface Shape {

    /**
     * Draws the shape.
     *
     * @return the drawn shape as ASCII art
     */
    String draw();
}
