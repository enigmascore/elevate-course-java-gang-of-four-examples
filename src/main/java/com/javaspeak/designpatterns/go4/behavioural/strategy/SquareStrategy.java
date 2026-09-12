package com.javaspeak.designpatterns.go4.behavioural.strategy;

/**
 * A Strategy that draws a square in ASCII art.  It can be swapped in to the StrategyContext
 * (StrategyApplication) interchangeably with any other Strategy, such as TriangleStrategy.
 *
 * @author John Dickerson - 22 February 2020
 */
public class SquareStrategy implements Strategy {

    /**
     * Creates a SquareStrategy.
     */
    public SquareStrategy() {

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
