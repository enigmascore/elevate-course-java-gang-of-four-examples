package com.javaspeak.designpatterns.go4.structural.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Adapter pattern example: calling draw() on the application's Square interface
 * returns the ASCII art drawn by the adapted third party ShapeImpl.
 *
 * @author John Dickerson - 11 September 2026
 */
public class AdapterApplicationTest {

    private static final String EXPECTED_SQUARE = """
            xxxx
            x  x
            x  x
            xxxx
            """;

    @Test
    public void adapterTranslatesDrawIntoDrawSquare() {

        Square square = new ShapeAdapter( new ShapeImpl() );

        assertEquals( EXPECTED_SQUARE, square.draw() );
    }


    @Test
    public void runExampleReturnsAsciiArtSquare() {

        assertEquals( EXPECTED_SQUARE, new AdapterApplication().runExample() );
    }
}
