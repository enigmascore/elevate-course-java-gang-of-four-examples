/**
 * Gang of Four Strategy pattern.
 * <p>
 * Text book description:
 * <p>
 * "Strategy: Encapsulates an algorithm inside a class. Define a family of algorithms,
 * encapsulate each one, and make them interchangeable.  Strategy lets the algorithm vary
 * independently from clients that use it."
 * <p>
 * In this example the
 * {@link com.javaspeak.designpatterns.go4.behavioural.strategy.StrategyApplication} acts as the
 * {@link com.javaspeak.designpatterns.go4.behavioural.strategy.StrategyContext}: a
 * {@link com.javaspeak.designpatterns.go4.behavioural.strategy.Strategy} such as
 * {@link com.javaspeak.designpatterns.go4.behavioural.strategy.SquareStrategy} or
 * {@link com.javaspeak.designpatterns.go4.behavioural.strategy.TriangleStrategy} is swapped in
 * at runtime and executed to draw its shape.
 * <p>
 * Strategy is a {@code @FunctionalInterface}, so a lambda or method reference can also serve as
 * a strategy in modern Java.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.strategy;
