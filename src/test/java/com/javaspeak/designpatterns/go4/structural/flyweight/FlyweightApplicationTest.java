package com.javaspeak.designpatterns.go4.structural.flyweight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/**
 * Tests the Flyweight pattern example: the ShapeCache hands out shared immutable flyweights and
 * the canvas renders many CanvasElements referencing them at their own locations.
 *
 * @author John Dickerson - 11 September 2026
 */
public class FlyweightApplicationTest {

    @Test
    public void shapeCacheSharesFlyweightInstances() {

        assertSame( ShapeCache.getSquare(), ShapeCache.getSquare() );
        assertSame( ShapeCache.getTriangle(), ShapeCache.getTriangle() );
    }


    @Test
    public void canvasRendersSingleShapeAtItsLocation() {

        Canvas canvas = new CanvasImpl();
        canvas.addCanvasElement( new CanvasElement( ShapeCache.getSquare(), 2, 1 ) );

        String expected = """

                  1111
                  1  1
                  1  1
                  1111
                """;

        assertEquals( expected, canvas.render() );
    }


    @Test
    public void runExampleRendersTwoSquaresAndTwoTriangles() {

        String expected = """
                1111     1
                1  1    1 1
                1  1   1   1
                1111  1111111


                1111     1
                1  1    1 1
                1  1   1   1
                1111  1111111
                """;

        assertEquals( expected, new FlyweightApplication().runExample() );
    }
}
