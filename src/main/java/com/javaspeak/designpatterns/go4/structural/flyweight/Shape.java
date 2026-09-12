package com.javaspeak.designpatterns.go4.structural.flyweight;

/**
 * Abstract class that shapes extend.  A Shape is an immutable flyweight: it holds the pixels
 * which make up the shape and never exposes its internal array, so a single instance can be
 * safely shared (read only) between many CanvasElements.
 * <p>
 * The pixels are held as an array of arrays.  For example the following is a square:
 * <pre>
 *     1111
 *     1001
 *     1001
 *     1111
 * </pre>
 *
 * @author John Dickerson - 23 February 2020
 */
public abstract sealed class Shape permits SquareImpl, TriangleImpl {

    private final int[][] points;

    /**
     * Copies the given pixels into the shape so that no caller retains a reference to the
     * shape's internal state.
     *
     * @param points
     *      The pixels making up the shape; a value of 1 means the pixel is set
     */
    protected Shape( int[][] points ) {

        this.points = new int[points.length][];

        for ( int y = 0; y < points.length; y++ ) {

            this.points[y] = points[y].clone();
        }
    }


    /**
     * Returns the height of the shape in pixels.
     *
     * @return the number of pixel rows in the shape
     */
    public int getHeight() {

        return points.length;
    }


    /**
     * Returns the width of the shape in pixels.
     *
     * @return the number of pixel columns in the shape
     */
    public int getWidth() {

        return points[0].length;
    }


    /**
     * Reports whether the pixel at the given coordinates is set.
     *
     * @param y
     *      The row of the pixel, 0 being the top row
     *
     * @param x
     *      The column of the pixel, 0 being the leftmost column
     *
     * @return true if the pixel is set
     */
    public boolean isPixelSet( int y, int x ) {

        return points[y][x] == 1;
    }
}
