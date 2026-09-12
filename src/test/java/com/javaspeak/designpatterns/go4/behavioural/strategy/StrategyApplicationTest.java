package com.javaspeak.designpatterns.go4.behavioural.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the Strategy pattern example: swapping the strategy in the StrategyContext changes the
 * shape that is drawn.
 *
 * @author John Dickerson - 11 September 2026
 */
public class StrategyApplicationTest {

    @Test
    public void switchingStrategiesChangesTheDrawnOutput() {

        StrategyApplication context = new StrategyApplication();

        context.setStrategy( new SquareStrategy() );
        String square = context.executeStrategy();

        context.setStrategy( new TriangleStrategy() );
        String triangle = context.executeStrategy();

        assertTrue( square.contains( "X X X X" ) );
        assertTrue( triangle.contains( "x x x x x" ) );
        assertEquals( new SquareStrategy().drawShape(), square );
        assertEquals( new TriangleStrategy().drawShape(), triangle );
    }


    @Test
    public void lambdaCanServeAsStrategy() {

        StrategyApplication context = new StrategyApplication();

        context.setStrategy( () -> "- - - - -\n" );

        assertEquals( "- - - - -\n", context.executeStrategy() );
    }


    @Test
    public void runExampleDrawsAllStrategies() {

        String report = new StrategyApplication().runExample();

        assertTrue( report.contains( "SquareStrategy draws:" ) );
        assertTrue( report.contains( "X X X X" ) );
        assertTrue( report.contains( "TriangleStrategy draws:" ) );
        assertTrue( report.contains( "x x x x x" ) );
        assertTrue( report.contains( "Lambda strategy draws:" ) );
        assertTrue( report.contains( "- - - - -" ) );
    }
}
