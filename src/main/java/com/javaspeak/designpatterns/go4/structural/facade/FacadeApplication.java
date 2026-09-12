package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Text book description:
 * <p>
 * "Facade: A single class that represents an entire subsystem. Provide a unified interface to
 * a set of interfaces in a system. Facade defines a higher-level interface that makes the
 * subsystem easier to use."
 * <p>
 * This example application invokes methods on the ShapeFacade.  The ShapeFacade provides an API
 * to the outside world.  Internally the ShapeFacade calls methods on other internal interfaces:
 * SquareBuilder and TriangleBuilder.
 *
 * @author John Dickerson - 22 February 2020
 */
public class FacadeApplication {

    /**
     * Creates the example application.
     */
    public FacadeApplication() {
    }


    /**
     * Runs the example: calls methods on the facade to retrieve shapes and draws them.
     *
     * @return the square and the triangle rendered as ASCII art
     */
    public String runExample() {

        ShapeFacade shapeFacade = new ShapeFacadeImpl();

        return shapeFacade.getSquare().draw() + "\n" + shapeFacade.getTriangle().draw();
    }


    /**
     * Main method.
     *
     * @param args
     *      command line arguments; not used
     */
    public static void main( String[] args ) {

        FacadeApplication application = new FacadeApplication();
        System.out.println( application.runExample() );
    }
}
