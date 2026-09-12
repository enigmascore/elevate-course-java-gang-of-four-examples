package com.javaspeak.designpatterns.go4.structural.flyweight;

/**
 * ShapeCache is a flyweight factory that returns flyweight shapes.  There is only one instance
 * of each Shape and each Shape may be referenced by many CanvasElements.  This saves on memory.
 * The cached shapes are immutable so sharing them is safe.
 *
 * @author John Dickerson - 23 February 2020
 */
public class ShapeCache {

    private static final Shape SQUARE = new SquareImpl();
    private static final Shape TRIANGLE = new TriangleImpl();

    private ShapeCache() {

        // static factory only
    }


    /**
     * Returns the shared square flyweight.
     *
     * @return
     *      the single SquareImpl instance
     */
    public static Shape getSquare() {

        return SQUARE;
    }


    /**
     * Returns the shared triangle flyweight.
     *
     * @return
     *      the single TriangleImpl instance
     */
    public static Shape getTriangle() {

        return TRIANGLE;
    }
}
