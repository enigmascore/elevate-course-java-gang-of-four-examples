package com.javaspeak.designpatterns.go4.creational.factorymethod;

/**
 * Has factory methods for creating Squares and Triangles.
 *
 * @author John Dickerson - 24 February 2020
 */
public class Drawing {

    /**
     * Creates a Drawing.
     */
    public Drawing() {

    }


    /**
     * Factory method which creates a Square.
     *
     * @return a new Square
     */
    public Shape createSquare() {

        return new Square();
    }


    /**
     * Factory method which creates a Triangle.
     *
     * @return a new Triangle
     */
    public Shape createTriangle() {

        return new Triangle();
    }
}
