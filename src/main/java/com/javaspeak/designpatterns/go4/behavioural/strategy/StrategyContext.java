package com.javaspeak.designpatterns.go4.behavioural.strategy;

/**
 * This interface is optional in the Strategy pattern. It provides a contract for the application
 * using the strategy pattern to swap in Strategies and execute them.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface StrategyContext {

    /**
     * Set the strategy
     *
     * @param strategy
     *      The implementation of the strategy provides an implementation of the
     *      drawShape() method
     */
    void setStrategy( Strategy strategy );


    /**
     * Executes the currently set strategy.
     *
     * @return the shape drawn by the current strategy
     */
    String executeStrategy();
}
