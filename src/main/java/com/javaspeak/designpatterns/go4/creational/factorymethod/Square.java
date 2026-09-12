package com.javaspeak.designpatterns.go4.creational.factorymethod;

/**
 * Implementation of Shape which draws a Square.
 *
 * @author John Dickerson - 24 February 2020
 */
public class Square implements Shape {

    /**
     * Creates a Square.
     */
    public Square() {

    }


    @Override
    public String draw() {

        return """
                xxxx
                x  x
                x  x
                xxxx
                """;
    }
}
