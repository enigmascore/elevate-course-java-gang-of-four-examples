package com.javaspeak.designpatterns.go4.creational.abstractfactory;

/**
 * Implementation of Shape. When the drawShape() method is called it returns a drawing of a
 * Square.
 *
 * @author John Dickerson - 22 February 2020
 */
public class Square implements Shape {

    /**
     * Creates a Square.
     */
    public Square() {

    }


    @Override
    public String drawShape() {

        return """
                X X X X
                X     X
                X     X
                X X X X
                """;
    }
}
