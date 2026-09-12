package com.javaspeak.designpatterns.go4.creational.prototype;

/**
 * A Triangle Shape. Its constructor pretends to be expensive, which is why the PrototypeFactory
 * copies a cached prototype instead of constructing a new Triangle each time.
 *
 * @author John Dickerson - 24 February 2020
 */
public class Triangle extends Shape {

    /**
     * Constructor pretending to do some expensive initialisation.
     */
    public Triangle() {

        pixels = """
                   x
                  x x
                 x   x
                xxxxxxx
                """;
    }


    // Copy constructor: copies the already initialised pixels cheaply
    private Triangle( Triangle source ) {

        super( source );
    }


    @Override
    public Triangle copy() {

        return new Triangle( this );
    }
}
