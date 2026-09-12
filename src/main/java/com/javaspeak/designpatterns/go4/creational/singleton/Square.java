package com.javaspeak.designpatterns.go4.creational.singleton;

/**
 * An immutable Square built by the ShapeManager singleton.
 *
 * @author John Dickerson - 24 February 2020
 */
public class Square extends Shape {

    /**
     * Constructor pretending to do some expensive initialisation.
     */
    public Square() {

        super( """
                xxxx
                x  x
                x  x
                xxxx
                """ );
    }
}
