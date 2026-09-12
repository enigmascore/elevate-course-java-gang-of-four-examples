package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Implements an API to the outside world.  Internally the implementation of the getSquare() and
 * getTriangle() methods make method calls to internal interfaces.  A squareBuilder is used
 * internally to create the square to return and a triangleBuilder is used internally to create
 * the triangle to return.
 *
 * @author John Dickerson - 23 February 2020
 */
public class ShapeFacadeImpl implements ShapeFacade {

    private final SquareBuilder squareBuilder;
    private final TriangleBuilder triangleBuilder;

    /**
     * Creates the facade, wiring up the internal builders it delegates to.
     */
    public ShapeFacadeImpl() {

        squareBuilder = new SquareBuilderImpl();
        triangleBuilder = new TriangleBuilderImpl();
    }


    @Override
    public Square getSquare() {

        return squareBuilder.buildSquare();
    }


    @Override
    public Triangle getTriangle() {

        return triangleBuilder.buildTriangle();
    }
}
