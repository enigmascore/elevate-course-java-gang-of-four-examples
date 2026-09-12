package com.javaspeak.designpatterns.go4.structural.bridge;

/**
 * Text book description:
 * <p>
 * "Bridge: Separates an object's interface from its implementation. Decouple an abstraction from
 * its implementation so that the two can vary independently."
 * <p>
 * This application has an abstract class called AbstractShapeBridge which wraps ShapeBuilder so
 * that calling the buildShape() method of AbstractShapeBridge internally calls the buildShape()
 * method of the ShapeBuilder.
 * <p>
 * This wrapping allows the implementation of ShapeBuilder, currently TriangleBuilder, to be
 * readily switched for another implementation such as SquareBuilder.
 * <p>
 * The implementation of the ShapeBuilder is passed through the constructor of
 * AbstractShapeBridge.
 * <p>
 * ShapeBridgeImpl extends AbstractShapeBridge and adds its own method, drawShape(), to draw the
 * shape.  Internally the drawShape() method makes a call to the buildShape() method in
 * AbstractShapeBridge.
 * <p>
 * This pattern is pretty similar to the Adapter pattern.  Notice some differences though:
 * <ul>
 *     <li>
 *          Adapter is more about wrapping a third party API so it can be used by the application
 *          API.
 *     </li>
 *     <li>
 *          Bridge is more about having the ability to slot in different implementations.
 *     </li>
 *     <li>
 *          The Bridge has an abstract class that wraps the implementation which is itself
 *          extended to add extra functionality (e.g. ShapeBridgeImpl.drawShape())
 *     </li>
 *     <li>
 *          The Bridge pattern in this example can be considered to be a combination of the
 *          Strategy and Template patterns
 *     </li>
 * </ul>
 *
 * @author John Dickerson - 22 February 2020
 */
public class BridgeApplication {

    /**
     * Creates the example application.
     */
    public BridgeApplication() {
    }


    /**
     * Runs the example: calls drawShape() on the bridge implementation, which builds a Shape via
     * its pluggable ShapeBuilder and draws it.
     *
     * @return the ASCII art shape drawn by the bridge implementation
     */
    public String runExample() {

        ShapeBridgeImpl shapeBridge = new ShapeBridgeImpl();
        return shapeBridge.drawShape();
    }


    /**
     * Runs the Bridge example and prints the drawn shape.
     *
     * @param args command line arguments; not used
     */
    public static void main( String[] args ) {

        BridgeApplication application = new BridgeApplication();
        System.out.println( application.runExample() );
    }
}
