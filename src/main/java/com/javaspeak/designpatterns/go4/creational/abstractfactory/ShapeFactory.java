package com.javaspeak.designpatterns.go4.creational.abstractfactory;

/**
 * SquareFactory and TriangleFactory implement ShapeFactory.  ShapeFactory is the cornerstone of
 * the Gang of Four creational AbstractFactory pattern.
 * <p>
 * Depending on whether SquareFactory or TriangleFactory is plugged in, a Square or Triangle will
 * be used when the drawShape() method is called on the Shape.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface ShapeFactory {

    /**
     * Returns a Shape that the implementation of the ShapeFactory deals with.
     * <p>
     * For example a SquareFactory that implements ShapeFactory will return a Square.
     *
     * @return
     *      a Shape
     */
    Shape getShape();
}
