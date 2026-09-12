package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Provides an implementation for a buildTriangle method.
 *
 * @author John Dickerson - 23 February 2020
 */
public class TriangleBuilderImpl implements TriangleBuilder {

    /**
     * Creates a TriangleBuilderImpl.
     */
    public TriangleBuilderImpl() {
    }


    @Override
    public Triangle buildTriangle() {

        return new TriangleImpl();
    }
}
