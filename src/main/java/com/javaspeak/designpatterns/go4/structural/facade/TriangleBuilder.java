package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Defines a buildTriangle method.  This is one of the internal interfaces which the ShapeFacade
 * hides from the outside world.
 *
 * @author John Dickerson - 23 February 2020
 */
public interface TriangleBuilder {

    /**
     * Builds a Triangle.
     *
     * @return
     *      a Triangle
     */
    Triangle buildTriangle();
}
