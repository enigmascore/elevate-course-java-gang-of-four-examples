package com.javaspeak.designpatterns.go4.creational.builder;

/**
 * Text book description:
 * <p>
 * "Builder: Separates object construction from its representation. Separate the construction
 * of a complex object from its representation so that the same construction processes can
 * create different representations."
 * <p>
 * This application uses a buildManager which calls constructShape(..) on different builders to
 * construct different shapes. The class Shape is used to encapsulate shapes.   SquareBuilder and
 * TriangleBuilder are used to populate Shape with the correct values for a Square and Triangle
 * respectively.
 * <p>
 * The constructShape(..) method calls several methods on the builders to build a shape.  The
 * constructShape(..) method of the buildManager calls the builder methods in the correct order:
 * <pre>
 *     shapeBuilder.setPaintingLevel();
 *     shapeBuilder.buildPoints();
 *     shapeBuilder.translateCoordinates();
 * </pre>
 * The paintingLevel is the order to paint the shape. For example any shape with level 0 will be
 * painted before any shape with level 1.
 * <p>
 * shapeBuilder.buildPoints() builds the points for the shape.
 * <p>
 * translateCoordinates() translates the points. e.g. it can move a shape both vertically and
 * horizontally.
 * <p>
 * Once the Shape has been built it can be retrieved from the builder using getShape()
 *
 * @author John Dickerson - 22 February 2020
 */
public class BuilderApplication {

    /**
     * Creates the application.
     */
    public BuilderApplication() {

    }


    /**
     * Runs the example: constructs the Shapes using the SquareBuilder and TriangleBuilder, then
     * merges their coordinates together into a points array in BuildManager and finally paints
     * the merged points.
     *
     * @return the painted merged Shapes
     */
    public String runExample() {

        BuildManager buildManager = new BuildManager();
        buildManager.constructShape( new SquareBuilder() );
        buildManager.constructShape( new TriangleBuilder() );
        buildManager.mergeCoordinates();

        return buildManager.paint();
    }


    /**
     * Runs the example and prints the painted Shapes.
     *
     * @param args
     *      not used
     */
    public static void main( String[] args ) {

        BuilderApplication application = new BuilderApplication();
        System.out.println( application.runExample() );
    }
}
