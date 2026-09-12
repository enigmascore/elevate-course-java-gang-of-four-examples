package com.javaspeak.designpatterns.go4.creational.factorymethod;

/**
 * Text book description:
 * <p>
 * "Factory Method: Creates an instance of several derived classes. Define an interface for
 * creating an object, but let subclasses decide which class to instantiate. Factory Method
 * lets a class defer instantiation to subclasses."
 * <p>
 * Factory Methods in this example are createSquare() and createTriangle():
 * <pre>
 *      Drawing drawing = new Drawing();
 *      drawing.createSquare().draw();
 *      drawing.createTriangle().draw();
 * </pre>
 * createSquare() creates a Square instance and createTriangle() creates a Triangle instance.
 * Both Square and Triangle implement Shape which has a draw() method.
 *
 * @author John Dickerson - 22 February 2020
 */
public class FactoryMethodApplication {

    /**
     * Creates the application.
     */
    public FactoryMethodApplication() {

    }


    /**
     * Runs the example: uses the factory methods of Drawing to create a Square and a Triangle
     * and draws both of them.
     *
     * @return the drawn Square followed by the drawn Triangle
     */
    public String runExample() {

        Drawing drawing = new Drawing();

        return drawing.createSquare().draw() + "\n" + drawing.createTriangle().draw();
    }


    /**
     * Runs the example and prints the drawn Shapes.
     *
     * @param args
     *      not used
     */
    public static void main( String[] args ) {

        FactoryMethodApplication application = new FactoryMethodApplication();
        System.out.println( application.runExample() );
    }
}
