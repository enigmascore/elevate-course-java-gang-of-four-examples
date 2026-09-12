package com.javaspeak.designpatterns.go4.structural.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Bridge pattern example: the bridge delegates to its pluggable ShapeBuilder and
 * returns the ASCII art of the built shape.
 *
 * @author John Dickerson - 11 September 2026
 */
public class BridgeApplicationTest {

    private static final String EXPECTED_TRIANGLE = """
                x
               x x
              x   x
             x     x
            x x x x x
            """;

    @Test
    public void bridgeDrawsShapeBuiltByPluggedInBuilder() {

        ShapeBridgeImpl shapeBridge = new ShapeBridgeImpl();

        assertEquals( EXPECTED_TRIANGLE, shapeBridge.drawShape() );
    }


    @Test
    public void bridgeSupportsSwappingInAnotherBuilder() {

        var square = """
                xxx
                x x
                xxx
                """;

        AbstractShapeBridge shapeBridge = new AbstractShapeBridge( () -> () -> square ) {
        };

        assertEquals( square, shapeBridge.buildShape().draw() );
    }


    @Test
    public void runExampleReturnsAsciiArtTriangle() {

        assertEquals( EXPECTED_TRIANGLE, new BridgeApplication().runExample() );
    }
}
