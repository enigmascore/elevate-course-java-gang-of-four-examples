package com.javaspeak.designpatterns.go4.creational.prototype;

/**
 * Text book description:
 * <p>
 * "Prototype: A fully initialized instance to be copied or cloned. Specify the kinds of
 * objects to create using a prototypical instance, and create new objects by copying this
 * prototype."
 * <p>
 * The prototype factory pattern uses a map of pre-initialized instances where there is one of
 * each type in the map.
 * <p>
 * When an instance of a certain type is requested the relevant instance is retrieved and copied.
 * <p>
 * This pattern is typically used where expensive initialisation in the constructor is present
 * so it is cheaper to copy the class than do an expensive initialisation every time a new
 * instance is required.
 * <p>
 * In this example there is a map of different shapes in the PrototypeFactory.
 * <p>
 * When a Shape such as a Square is requested using the getShape() method, the appropriate
 * instance is retrieved from the map and copied using its copy constructor.
 *
 * @author John Dickerson - 22 February 2020
 */
public class PrototypeApplication {

    /**
     * Creates the application.
     */
    public PrototypeApplication() {

    }


    /**
     * Runs the example: retrieves a copied Square and a copied Triangle from the
     * PrototypeFactory and draws both of them.
     *
     * @return the drawn Square followed by the drawn Triangle
     */
    public String runExample() {

        return PrototypeFactory.getShape( ShapeType.SQUARE ).draw()
                + "\n"
                + PrototypeFactory.getShape( ShapeType.TRIANGLE ).draw();
    }


    /**
     * Runs the example and prints the drawn Shapes.
     *
     * @param args
     *      not used
     */
    public static void main( String[] args ) {

        PrototypeApplication application = new PrototypeApplication();
        System.out.println( application.runExample() );
    }
}
