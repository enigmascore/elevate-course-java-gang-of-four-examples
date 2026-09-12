package com.javaspeak.designpatterns.go4.creational.abstractfactory;

/**
 * The ShapeSelector is responsible for returning the correct ShapeFactory. The ShapeFactory can
 * be either a SquareFactory or a TriangleFactory.
 *
 * @author John Dickerson - 22 February 2020
 */
public class ShapeSelector {

    /**
     * The types of Shape which the ShapeSelector can provide a ShapeFactory for.
     */
    public enum ShapeType {

        /**
         * A four sided shape whose sides all have the same length.
         */
        SQUARE,

        /**
         * A three sided shape.
         */
        TRIANGLE
    }


    /**
     * Utility class which is not instantiated.
     */
    private ShapeSelector() {

    }


    /**
     * Returns the appropriate ShapeFactory for the ShapeType.
     *
     * @param shapeType
     *      Enum specifying Shape type
     *
     * @return
     *      Factory that implements ShapeFactory
     */
    public static ShapeFactory getShapeFactory( ShapeType shapeType ) {

        return switch ( shapeType ) {

            case SQUARE -> new SquareFactory();
            case TRIANGLE -> new TriangleFactory();
        };
    }
}
