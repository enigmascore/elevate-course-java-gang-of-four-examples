package com.javaspeak.designpatterns.go4.behavioural.strategy;

/**
 * A Strategy that draws a triangle in ASCII art.  It can be swapped in to the StrategyContext
 * (StrategyApplication) interchangeably with any other Strategy, such as SquareStrategy.
 *
 * @author John Dickerson - 22 February 2020
 */
public class TriangleStrategy implements Strategy {

    /**
     * Creates a TriangleStrategy.
     */
    public TriangleStrategy() {

    }


    @Override
    public String drawShape() {

        return """
                    x
                   x x
                  x   x
                 x     x
                x x x x x
                """;
    }
}
