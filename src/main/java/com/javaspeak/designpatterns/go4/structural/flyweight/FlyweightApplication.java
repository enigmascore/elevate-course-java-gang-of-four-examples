package com.javaspeak.designpatterns.go4.structural.flyweight;

/**
 * Text book description:
 * <p>
 * "Flyweight: A fine-grained instance used for efficient sharing. Use sharing to support
 * large numbers of fine-grained objects efficiently. A flyweight is a shared object that can
 * be used in multiple contexts simultaneously. The flyweight acts as an independent object
 * in each context - it is indistinguishable from an instance of the object that is not shared."
 * <p>
 * When there are many instances of a class which use the same data across many instances then
 * that data should be factored out into a read only instance of a class that allows the data to
 * be shared between many instances.
 * <p>
 * In this example we are drawing shapes on a canvas at different locations. Some of the data for
 * a given shape is the same.  For example a square looks the same. Other data could be
 * different; for example the location of a square on the canvas.
 * <p>
 * We have factored out the information about rendering a square or triangle into a flyweight
 * object which can be referenced by many CanvasElements wishing to draw a square or triangle.
 * The CanvasElements reference the shape they are interested in and provide unique data such as
 * the location to draw the shape at.
 * <p>
 * When there are many references to the same flyweight objects this can substantially reduce
 * memory requirements.
 *
 * @author John Dickerson - 22 February 2020
 */
public class FlyweightApplication {

    /**
     * Creates the example application.
     */
    public FlyweightApplication() {
    }


    /**
     * Runs the example: adds CanvasElements referencing the shared flyweight shapes to the
     * canvas and renders them.  The rendering involves copying individual pixels from the
     * flyweight Shapes onto the canvas.
     *
     * @return the canvas rendered as ASCII art
     */
    public String runExample() {

        Canvas canvas = new CanvasImpl();
        canvas.addCanvasElement( new CanvasElement( ShapeCache.getSquare(), 0, 0 ) );
        canvas.addCanvasElement( new CanvasElement( ShapeCache.getSquare(), 0, 6 ) );
        canvas.addCanvasElement( new CanvasElement( ShapeCache.getTriangle(), 6, 0 ) );
        canvas.addCanvasElement( new CanvasElement( ShapeCache.getTriangle(), 6, 6 ) );

        return canvas.render();
    }


    /**
     * Runs the example and prints its output.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        FlyweightApplication application = new FlyweightApplication();
        System.out.println( application.runExample() );
    }
}
