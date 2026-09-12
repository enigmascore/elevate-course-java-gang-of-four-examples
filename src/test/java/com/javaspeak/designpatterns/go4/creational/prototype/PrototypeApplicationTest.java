package com.javaspeak.designpatterns.go4.creational.prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

/**
 * Tests the Prototype pattern example: the PrototypeFactory returns copies which are independent
 * instances with the same content as each other and as the cached prototype.
 *
 * @author John Dickerson - 11 September 2026
 */
public class PrototypeApplicationTest {

    @Test
    public void copiesAreIndependentInstancesWithEqualContent() {

        Shape firstSquare = PrototypeFactory.getShape( ShapeType.SQUARE );
        Shape secondSquare = PrototypeFactory.getShape( ShapeType.SQUARE );

        assertNotSame( firstSquare, secondSquare );
        assertEquals( firstSquare.draw(), secondSquare.draw() );
    }


    @Test
    public void copiesHaveTheSameRuntimeTypeAsTheirPrototype() {

        assertInstanceOf( Square.class, PrototypeFactory.getShape( ShapeType.SQUARE ) );
        assertInstanceOf( Triangle.class, PrototypeFactory.getShape( ShapeType.TRIANGLE ) );
    }


    @Test
    public void copiesDrawTheSameArtAsFreshlyConstructedShapes() {

        assertEquals(
                new Square().draw(), PrototypeFactory.getShape( ShapeType.SQUARE ).draw() );

        assertEquals(
                new Triangle().draw(), PrototypeFactory.getShape( ShapeType.TRIANGLE ).draw() );
    }


    @Test
    public void runExampleDrawsSquareThenTriangle() {

        assertEquals(
                new Square().draw() + "\n" + new Triangle().draw(),
                new PrototypeApplication().runExample() );
    }
}
