package com.javaspeak.designpatterns.go4.behavioural.strategy;

/**
 * Different Strategies, such as SquareStrategy and TriangleStrategy, can be swapped in to the
 * StrategyContext (StrategyApplication).
 * <p>
 * As this interface has a single abstract method it is a {@code @FunctionalInterface}: a lambda
 * or method reference can serve as a Strategy just as well as a named class.
 *
 * @author John Dickerson - 22 February 2020
 */
@FunctionalInterface
public interface Strategy {

    /**
     * Draws the shape this strategy is responsible for.
     *
     * @return the drawn shape as ASCII art
     */
    String drawShape();
}
