package com.javaspeak.designpatterns.go4.creational.builder;

/**
 * This builder configures the Shape class in the parent ShapeBuilder class
 * to model a Square.
 *
 * @author John Dickerson - 22 February 2020
 */
public class SquareBuilder extends ShapeBuilder {

    /**
     * Creates a SquareBuilder.
     */
    public SquareBuilder() {

    }


    @Override
    public void buildPoints() {

        //   1 1 1 1
        //   1     1
        //   1     1
        //   1 1 1 1
        shape.setPoints( new int[][] {
                { 1, 1, 1, 1 },
                { 1, 0, 0, 1 },
                { 1, 0, 0, 1 },
                { 1, 1, 1, 1 } } );
    }


    @Override
    public void setPaintingLevel() {

        // paint first
        shape.setPaintLevel( 0 );
    }


    @Override
    public void translateCoordinates() {

        // no translation necessary
    }
}
