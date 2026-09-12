package com.javaspeak.designpatterns.go4.behavioural.visitor;

/**
 * Text book description:
 * <p>
 * "Visitor: Defines a new operation to a class without change. Represent an operation to be
 * performed on the elements of an object structure. Visitor lets you define a new operation
 * without changing the classes of the elements on which it operates."
 * <p>
 * This example uses the Visitor Pattern.
 * <p>
 * The output of running the example looks like:
 * <pre>
 * ============================================
 * SmallShapeVisitor
 * ============================================
 *   T
 *  TTT
 * TTTTT
 *
 * xxxx
 * xxxx
 * xxxx
 *
 * ============================================
 * BigShapeVisitor
 * ============================================
 *     T
 *    TTT
 *   TTTTT
 *  TTTTTTT
 * TTTTTTTTT
 *
 * Triangle
 *
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 *
 * Square
 * </pre>
 * There are two Visitors, one is called BigShapeVisitor and the other SmallShapeVisitor.  As
 * the names imply BigShapeVisitor is responsible for drawing big Shapes and SmallShapeVisitor
 * is responsible for drawing small shapes.
 * <p>
 * It makes sense to centralise the code for drawing big Shapes in one place and the code for
 * drawing small shapes in another.
 * <p>
 * As time goes on we may want to create new Visitors like "ShinyShapeVisitor" or
 * "ThreeDShapeVisitor".
 * <p>
 * Instead of having to go and edit many different shape classes to produce a different version of
 * their shape a new Visitor can be created and the code placed in one centralised place.
 * <p>
 * When we call the accept( ShapeVisitor shapeVisitor ) method on a TriangleVisitable or
 * SquareVisitable we pass it an implementation of ShapeVisitor. Implementations include
 * BigShapeVisitor and SmallShapeVisitor. Internal to the accept method of TriangleVisitable or
 * SquareVisitable you will see the visit method is called on the Visitor passing a reference of
 * this:
 * <pre>
 * // inside TriangleVisitable or SquareVisitable
 * public String accept( ShapeVisitor shapeVisitor ) {
 *
 *     return shapeVisitor.visit( this );
 * }
 * </pre>
 * In the Visitor you will find overloaded visit methods, one for each Visitable.
 * <p>
 * SquareVisitable and TriangleVisitable are Visitables.  These classes may hold information
 * useful for all Visitors. This information may be different between the Visitables as it can be
 * specific to the Visitable itself.  For example the TriangleVisitable has a method
 * "getCharacter()" which allows the BigShapeVisitor and SmallShapeVisitor to know what character
 * to draw the triangle with.  Note that SquareVisitable does not have a "getCharacter()" method
 * but instead has a personalized "getTitle()" method.
 * <p>
 * ShapeVisitable is a sealed interface, so the set of Visitables is closed and known to the
 * compiler.  See the package javadoc for how sealed interfaces plus pattern-matching switch
 * offer a modern alternative to the visitor pattern for closed hierarchies.
 *
 * @author John Dickerson - 22 February 2020
 */
public class VisitorApplication {

    /**
     * Creates a VisitorApplication.
     */
    public VisitorApplication() {

    }


    /**
     * Runs the example: both the SmallShapeVisitor and the BigShapeVisitor visit the same
     * visitable structure (a TriangleVisitable and a SquareVisitable) and each renders its own
     * version of the shapes.
     *
     * @return the shapes rendered by each visitor over the same visitable structure
     */
    public String runExample() {

        ShapeVisitor[] shapeVisitors = { new SmallShapeVisitor(), new BigShapeVisitor() };
        ShapeVisitable[] shapeVisitables = { new TriangleVisitable(), new SquareVisitable() };

        var report = new StringBuilder();

        for ( ShapeVisitor shapeVisitor : shapeVisitors ) {

            report.append( "============================================\n" );
            report.append( shapeVisitor.getName() ).append( '\n' );
            report.append( "============================================\n" );

            for ( ShapeVisitable shapeVisitable : shapeVisitables ) {

                report.append( shapeVisitable.accept( shapeVisitor ) ).append( '\n' );

                // Because ShapeVisitable is sealed, a pattern-matching switch is the modern
                // alternative to the double dispatch above - the compiler checks the switch is
                // exhaustive over the closed hierarchy, so no accept/visit plumbing is needed:
                //
                // String rendering = switch ( shapeVisitable ) {
                //     case TriangleVisitable triangle -> shapeVisitor.visit( triangle );
                //     case SquareVisitable square -> shapeVisitor.visit( square );
                // };
            }
        }

        return report.toString();
    }


    /**
     * Runs the example from the command line and prints the result.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        VisitorApplication application = new VisitorApplication();
        System.out.println( application.runExample() );
    }
}
