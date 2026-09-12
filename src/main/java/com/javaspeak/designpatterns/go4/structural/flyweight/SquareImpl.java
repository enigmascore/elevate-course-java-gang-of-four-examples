package com.javaspeak.designpatterns.go4.structural.flyweight;

/**
 * Defines pixels for a Square.
 *
 * @author John Dickerson - 23 February 2020
 */
public final class SquareImpl extends Shape {

    /**
     * Creates the square flyweight from its pixels.
     */
    public SquareImpl() {

        super( new int[][] {
                { 1, 1, 1, 1 },
                { 1, 0, 0, 1 },
                { 1, 0, 0, 1 },
                { 1, 1, 1, 1 } } );
    }
}
