package com.javaspeak.designpatterns.go4.structural.proxy;

/**
 * Implementation of Shape.  This is the class we are proxying.
 *
 * @author John Dickerson - 23 February 2020
 */
public class Triangle implements Shape {

    /**
     * Creates a Triangle.
     */
    public Triangle() {
    }


    @Override
    public String drawShape() {

        return """
                   x
                  x x
                 x   x
                xxxxxxx
                """;
    }
}
