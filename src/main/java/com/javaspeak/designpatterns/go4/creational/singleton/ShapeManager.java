package com.javaspeak.designpatterns.go4.creational.singleton;

/**
 * This class is a singleton. It has a private constructor so it cannot be instantiated directly
 * by other classes.  Instead the single instance is created eagerly in a final field
 * initializer, which is class-initialization-safe: the JVM guarantees the field is assigned
 * exactly once, when the class is initialized, before any thread can call getInstance().
 * <p>
 * Once the instance has been retrieved using the static getInstance() method, the buildSquare()
 * method can be called on the instance.
 * <p>
 * The main alternative to this idiom is a single-element enum
 * ({@code public enum ShapeManager { INSTANCE }}), which additionally protects the singleton
 * against reflection and serialization attacks.
 *
 * @author John Dickerson - 24 February 2020
 */
public class ShapeManager {

    private static final ShapeManager instance = new ShapeManager();

    /**
     * Private constructor so that the only instance is the one created by the final field
     * initializer.
     */
    private ShapeManager() {

    }


    /**
     * This method provides the only access to an instance of ShapeManager.
     *
     * @return the single instance of ShapeManager
     */
    public static ShapeManager getInstance() {

        return instance;
    }


    /**
     * Builds a Square.
     *
     * @return a newly built Square
     */
    public Square buildSquare() {

        return new Square();
    }
}
