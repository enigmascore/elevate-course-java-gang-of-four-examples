package com.javaspeak.designpatterns.go4.creational.factorymethod;

/**
 * Implementation of Shape which draws a Triangle.
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
                xxxxxxx
                """;
    }
}
