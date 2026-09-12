package com.javaspeak.designpatterns.go4.creational.singleton;

/**
 * Text book description:
 * <p>
 * "Singleton: A class of which only a single instance can exist. Ensure a class only has one
 * instance, and provide a global point of access to it."
 * <p>
 * The Singleton ensures that only one copy of an instance exists in the same classloader.
 *
 * @author John Dickerson - 22 February 2020
 */
public class SingletonApplication {

    /**
     * Creates the application.
     */
    public SingletonApplication() {

    }


    /**
     * Runs the example: retrieves the instance from the singleton ShapeManager, builds a Square
     * and draws it.
     *
     * @return the drawn Square
     */
    public String runExample() {

        return ShapeManager.getInstance().buildSquare().draw();
    }


    /**
     * Runs the example and prints the drawn Square.
     *
     * @param args
     *      not used
     */
    public static void main( String[] args ) {

        SingletonApplication application = new SingletonApplication();
        System.out.println( application.runExample() );
    }
}
