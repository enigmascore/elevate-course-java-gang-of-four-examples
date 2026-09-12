package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Provides an implementation for a buildSquare method.
 *
 * @author John Dickerson - 23 February 2020
 */
public class SquareBuilderImpl implements SquareBuilder {

    /**
     * Creates a SquareBuilderImpl.
     */
    public SquareBuilderImpl() {
    }


    @Override
    public Square buildSquare() {

        return new SquareImpl();
    }
}
