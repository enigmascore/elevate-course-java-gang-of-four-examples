package com.javaspeak.designpatterns.go4.creational.factorymethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

/**
 * Tests the Factory Method pattern example: the factory methods of Drawing create the expected
 * Shapes and the Shapes draw the expected ASCII art.
 *
 * @author John Dickerson - 11 September 2026
 */
public class FactoryMethodApplicationTest {

    private static final String SQUARE_ART =
            "xxxx\n"
                    + "x  x\n"
                    + "x  x\n"
                    + "xxxx\n";

    private static final String TRIANGLE_ART =
            "   x\n"
                    + "  x x\n"
                    + " x   x\n"
                    + "xxxxxxx\n";


    @Test
    public void factoryMethodsCreateTheExpectedShapes() {

        Drawing drawing = new Drawing();

        assertInstanceOf( Square.class, drawing.createSquare() );
        assertInstanceOf( Triangle.class, drawing.createTriangle() );
    }


    @Test
    public void createdShapesDrawTheExpectedArt() {

        Drawing drawing = new Drawing();

        assertEquals( SQUARE_ART, drawing.createSquare().draw() );
        assertEquals( TRIANGLE_ART, drawing.createTriangle().draw() );
    }


    @Test
    public void runExampleDrawsSquareThenTriangle() {

        assertEquals(
                SQUARE_ART + "\n" + TRIANGLE_ART,
                new FactoryMethodApplication().runExample() );
    }
}
