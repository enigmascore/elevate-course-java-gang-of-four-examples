package com.javaspeak.designpatterns.go4.structural.flyweight;

/**
 * Captures the largest dimensions necessary to accommodate all Shapes on the canvas.
 *
 * @param width
 *      The max width of all the shapes
 *
 * @param height
 *      The max height of all the shapes
 *
 * @author John Dickerson - 23 February 2020
 */
public record Dimension( int width, int height ) {
}
