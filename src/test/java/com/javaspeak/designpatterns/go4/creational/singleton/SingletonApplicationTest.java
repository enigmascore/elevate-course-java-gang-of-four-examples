package com.javaspeak.designpatterns.go4.creational.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/**
 * Tests the Singleton pattern example: every call to getInstance() returns the same
 * ShapeManager instance, and the example draws the expected Square.
 *
 * @author John Dickerson - 11 September 2026
 */
public class SingletonApplicationTest {

    private static final String SQUARE_ART =
            "xxxx\n"
                    + "x  x\n"
                    + "x  x\n"
                    + "xxxx\n";


    @Test
    public void getInstanceAlwaysReturnsTheSameInstance() {

        assertSame( ShapeManager.getInstance(), ShapeManager.getInstance() );
    }


    @Test
    public void builtSquareDrawsTheExpectedArt() {

        assertEquals( SQUARE_ART, ShapeManager.getInstance().buildSquare().draw() );
    }


    @Test
    public void runExampleDrawsSquare() {

        assertEquals( SQUARE_ART, new SingletonApplication().runExample() );
    }
}
