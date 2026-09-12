package com.javaspeak.designpatterns.go4.structural.flyweight;

/**
 * Defines pixels for a Triangle.
 *
 * @author John Dickerson - 23 February 2020
 */
public final class TriangleImpl extends Shape {

    /**
     * Creates the triangle flyweight from its pixels.
     */
    public TriangleImpl() {

        super( new int[][] {
                { 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 0, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1 } } );
    }
}
