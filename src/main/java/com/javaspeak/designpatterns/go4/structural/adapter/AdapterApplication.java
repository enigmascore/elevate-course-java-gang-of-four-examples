package com.javaspeak.designpatterns.go4.structural.adapter;

/**
 * Text book description:
 * <p>
 * "Adapter: Match interfaces of different classes. Convert the interface of a class into another
 * interface clients expect. Adapter lets classes work together that couldn't otherwise because
 * of incompatible interfaces."
 * <p>
 * An Adapter is used to provide a wrapper to a third party class so that it can be called by the
 * Application.
 * <p>
 * The Application may expect to call a certain method of an interface which is part of its own
 * API.  However we wish to call a method of some other third party API.
 * <p>
 * What we do is we wrap the third party class in an adapter that implements our application API.
 * When we call the application API method it internally calls the third party implementation.
 * <p>
 * In this example the roles are:
 * <p>
 * Square is the target: the interface the application expects to call.  Shape and its
 * implementation ShapeImpl are the adaptee: the third party (legacy) side we cannot change.
 * ShapeAdapter is the adapter: it implements the target interface Square and wraps a Shape.
 * (Note the naming is deliberately from the third party's point of view: the third party owns
 * the generic Shape / ShapeImpl names, while the application owns the specific Square name.)
 * <p>
 * For example imagine the Application client code wishes to call the draw() method on a Square.
 * Square is part of the application API.
 * <p>
 * We create a ShapeAdapter to wrap ShapeImpl which is part of a third party implementation.
 * <p>
 * When we call draw() on the ShapeAdapter it internally calls drawSquare() on the ShapeImpl
 * class.
 * <p>
 * We have adapted the shape.drawSquare() method so that it is executed when we call our
 * application API square.draw() method.
 *
 * @author John Dickerson - 22 February 2020
 */
public class AdapterApplication {

    /**
     * Creates the example application.
     */
    public AdapterApplication() {
    }


    /**
     * Runs the example: creates an adapter for ShapeImpl and calls square.draw() on it, which
     * internally calls shape.drawSquare().
     *
     * @return the ASCII art square drawn by the adapted third party ShapeImpl
     */
    public String runExample() {

        Square square = new ShapeAdapter( new ShapeImpl() );
        return square.draw();
    }


    /**
     * Runs the Adapter example and prints the drawn square.
     *
     * @param args command line arguments; not used
     */
    public static void main( String[] args ) {

        AdapterApplication application = new AdapterApplication();
        System.out.println( application.runExample() );
    }
}
