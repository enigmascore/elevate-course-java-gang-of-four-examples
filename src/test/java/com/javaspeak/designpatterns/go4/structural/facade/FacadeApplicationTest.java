package com.javaspeak.designpatterns.go4.structural.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Facade pattern example: the facade hides the builder subsystem and hands back shapes
 * which draw themselves as ASCII art.
 *
 * @author John Dickerson - 11 September 2026
 */
public class FacadeApplicationTest {

    private static final String SQUARE = """
            xxxx
            x  x
            x  x
            xxxx
            """;

    private static final String TRIANGLE = """
               x
              x x
             x   x
            xxxxxxx
            """;

    @Test
    public void facadeReturnsSquareWhichDrawsItself() {

        ShapeFacade shapeFacade = new ShapeFacadeImpl();

        assertEquals( SQUARE, shapeFacade.getSquare().draw() );
    }


    @Test
    public void facadeReturnsTriangleWhichDrawsItself() {

        ShapeFacade shapeFacade = new ShapeFacadeImpl();

        assertEquals( TRIANGLE, shapeFacade.getTriangle().draw() );
    }


    @Test
    public void runExampleDrawsBothShapes() {

        assertEquals( SQUARE + "\n" + TRIANGLE, new FacadeApplication().runExample() );
    }
}
