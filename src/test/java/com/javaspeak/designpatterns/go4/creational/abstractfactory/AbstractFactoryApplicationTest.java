package com.javaspeak.designpatterns.go4.creational.abstractfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import com.javaspeak.designpatterns.go4.creational.abstractfactory.ShapeSelector.ShapeType;

/**
 * Tests the Abstract Factory pattern example: the ShapeSelector returns the factory for the
 * requested shape type and each factory's Shape draws the expected ASCII art.
 *
 * @author John Dickerson - 11 September 2026
 */
public class AbstractFactoryApplicationTest {

    private static final String SQUARE_ART =
            "X X X X\n"
                    + "X     X\n"
                    + "X     X\n"
                    + "X X X X\n";

    private static final String TRIANGLE_ART =
            "    x\n"
                    + "   x x\n"
                    + "  x   x\n"
                    + " x     x\n"
                    + "x x x x x\n";


    @Test
    public void shapeSelectorReturnsFactoryForRequestedShapeType() {

        assertInstanceOf(
                SquareFactory.class, ShapeSelector.getShapeFactory( ShapeType.SQUARE ) );

        assertInstanceOf(
                TriangleFactory.class, ShapeSelector.getShapeFactory( ShapeType.TRIANGLE ) );
    }


    @Test
    public void factoriesCreateShapesWhichDrawTheExpectedArt() {

        assertEquals(
                SQUARE_ART,
                ShapeSelector.getShapeFactory( ShapeType.SQUARE ).getShape().drawShape() );

        assertEquals(
                TRIANGLE_ART,
                ShapeSelector.getShapeFactory( ShapeType.TRIANGLE ).getShape().drawShape() );
    }


    @Test
    public void runExampleDrawsSquareThenTriangle() {

        assertEquals(
                SQUARE_ART + "\n" + TRIANGLE_ART,
                new AbstractFactoryApplication().runExample() );
    }
}
