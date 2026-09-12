package com.javaspeak.designpatterns.go4.creational.prototype;

/**
 * A Square Shape. Its constructor pretends to be expensive, which is why the PrototypeFactory
 * copies a cached prototype instead of constructing a new Square each time.
 *
 * @author John Dickerson - 24 February 2020
 */
public class Square extends Shape {

    /**
     * Constructor pretending to do some expensive initialisation.
     */
    public Square() {

        pixels = """
                xxxxxxx
                x     x
                x     x
                xxxxxxx
                """;
    }


    // Copy constructor: copies the already initialised pixels cheaply
    private Square( Square source ) {

        super( source );
    }


    @Override
    public Square copy() {

        return new Square( this );
    }
}
