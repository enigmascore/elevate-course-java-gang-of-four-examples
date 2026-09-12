package com.javaspeak.designpatterns.go4.creational.abstractfactory;

/**
 * Implementation of Shape. When the drawShape() method is called it returns a drawing of a
 * Triangle.
 *
 * @author John Dickerson - 22 February 2020
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
                 x     x
                x x x x x
                """;
    }
}
