package com.javaspeak.designpatterns.go4.creational.builder;

/**
 * Record used to capture the largest dimensions necessary to accommodate all Shapes.
 *
 * @param width
 *      the max width of all the shapes
 * @param height
 *      the max height of all the shapes
 *
 * @author John Dickerson - 22 February 2020
 */
public record Dimension( int width, int height ) {
}
