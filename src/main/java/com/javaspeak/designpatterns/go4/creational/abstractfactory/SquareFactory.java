package com.javaspeak.designpatterns.go4.creational.abstractfactory;

/**
 * Implementation of ShapeFactory which returns a Square when the getShape()
 * method is called.
 *
 * @author John Dickerson - 22 February 2020
 */
public class SquareFactory implements ShapeFactory {

    /**
     * Creates a SquareFactory.
     */
    public SquareFactory() {

    }


    @Override
    public Shape getShape() {

        return new Square();
    }
}
