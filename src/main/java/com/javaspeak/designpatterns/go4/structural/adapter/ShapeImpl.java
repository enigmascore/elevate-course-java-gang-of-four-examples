package com.javaspeak.designpatterns.go4.structural.adapter;

/**
 * This class is the third party implementation of the adaptee interface, Shape.  It is the class
 * we are adapting so that the application can call it through its own Square interface.
 *
 * @author John Dickerson - 24 February 2020
 */
public class ShapeImpl implements Shape {

    /**
     * Creates the third party shape implementation.
     */
    public ShapeImpl() {
    }


    @Override
    public String drawSquare() {

        return """
                xxxx
                x  x
                x  x
                xxxx
                """;
    }
}
