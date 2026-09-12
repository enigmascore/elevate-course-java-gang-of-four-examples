package com.javaspeak.designpatterns.go4.creational.abstractfactory;

/**
 * Implementation of ShapeFactory which returns a Triangle when the getShape()
 * method is called.
 *
 * @author John Dickerson - 22 February 2020
 */
public class TriangleFactory implements ShapeFactory {

    /**
     * Creates a TriangleFactory.
     */
    public TriangleFactory() {

    }


    @Override
    public Shape getShape() {

        return new Triangle();
    }
}
