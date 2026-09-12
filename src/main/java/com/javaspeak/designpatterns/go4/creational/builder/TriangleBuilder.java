package com.javaspeak.designpatterns.go4.creational.builder;

/**
 * This builder configures the Shape class in the parent ShapeBuilder class
 * to model a Triangle.
 *
 * @author John Dickerson - 22 February 2020
 */
public class TriangleBuilder extends ShapeBuilder {

    /**
     * Creates a TriangleBuilder.
     */
    public TriangleBuilder() {

    }


    @Override
    public void buildPoints() {

        //         1
        //       1   1
        //     1       1
        //   1 1 1 1 1 1 1
        shape.setPoints( new int[][] {
                { 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 0, 1, 0 },
                { 1, 1, 1, 1, 1, 1, 1 } } );
    }


    @Override
    public void setPaintingLevel() {

        shape.setPaintLevel( 2 );
    }


    @Override
    public void translateCoordinates() {

        // translate (2,1): move each point right by 2 and down by 1
        int xTranslate = 2;
        int yTranslate = 1;

        int[][] points = shape.getPoints();

        int[][] translatedPoints =
                new int[points.length + yTranslate][points[0].length + xTranslate];

        for ( int y = 0; y < points.length; y++ ) {

            for ( int x = 0; x < points[y].length; x++ ) {

                translatedPoints[y + yTranslate][x + xTranslate] = points[y][x];
            }
        }

        shape.setPoints( translatedPoints );
    }
}
