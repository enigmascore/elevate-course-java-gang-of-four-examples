package com.javaspeak.designpatterns.go4.creational.builder;

/**
 * The Shape class models different shapes as a grid of points, together with the level the shape
 * is painted at.
 *
 * @author John Dickerson - 22 February 2020
 */
public class Shape {

    // Uses array of arrays.  For example the following is a square
    //
    //     1111
    //     1001
    //     1001
    //     1111
    //
    // If the above coordinates are translated (4,1) they become:
    //
    //     00000000
    //     00001111
    //     00001001
    //     00001001
    //     00001111
    private int[][] points;

    // 0 means paint first, larger numbers will be painted next
    private int paintLevel;

    /**
     * Creates an empty Shape for a ShapeBuilder to populate.
     */
    public Shape() {

    }


    /**
     * Returns the grid of points modelling the Shape. A value of 1 means the point is part of
     * the Shape.
     *
     * @return the grid of points
     */
    public int[][] getPoints() {

        return points;
    }


    /**
     * Sets the grid of points modelling the Shape.
     *
     * @param points
     *      the grid of points. A value of 1 means the point is part of the Shape
     */
    public void setPoints( int[][] points ) {

        this.points = points;
    }


    /**
     * Returns the level the Shape is painted at. Shapes on level 0 are painted before Shapes on
     * a higher level.
     *
     * @return the paint level
     */
    public int getPaintLevel() {

        return paintLevel;
    }


    /**
     * Sets the level the Shape is painted at.
     *
     * @param paintLevel
     *      the paint level. 0 means paint first, larger numbers will be painted next
     */
    public void setPaintLevel( int paintLevel ) {

        this.paintLevel = paintLevel;
    }
}
