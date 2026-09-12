package com.javaspeak.designpatterns.go4.structural.bridge;

/**
 * A Triangle is the Shape built by the TriangleBuilder.  Its draw() method returns the triangle
 * as ASCII art.
 *
 * @author John Dickerson - 24 February 2020
 */
public class Triangle implements Shape {

    /**
     * Creates a Triangle.
     */
    public Triangle() {
    }


    @Override
    public String draw() {

        return """
                    x
                   x x
                  x   x
                 x     x
                x x x x x
                """;
    }
}
