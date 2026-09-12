package com.javaspeak.designpatterns.go4.behavioural.visitor;

/**
 * SquareVisitable and TriangleVisitable implement this interface.  The calling code in
 * VisitorApplication calls the accept method on SquareVisitable and TriangleVisitable two times
 * each. On one occasion it calls passing the SmallShapeVisitor through the accept method and the
 * second time it passes the BigShapeVisitor.  Both SquareVisitable and TriangleVisitable in turn
 * call the visit method on the Visitor classes, SmallShapeVisitor and BigShapeVisitor.
 * <p>
 * The result is a small triangle is rendered when passing the SmallShapeVisitor into the accept
 * method of TriangleVisitable and similarly a small square is rendered when passing the
 * SmallShapeVisitor into the accept method of SquareVisitable.
 * <p>
 * Both the small triangle and small square drawing code is encapsulated in the SmallShapeVisitor
 * instead of being in both TriangleVisitable and SquareVisitable.  What this means is that the
 * TriangleVisitable and SquareVisitable code does not need to change if a new style of drawing a
 * triangle and square (such as in 3D) is invented.  The 3D implementation of both a triangle and
 * square could then be encapsulated in a new Visitor called ThreeDShapeVisitor.
 * <p>
 * This interface is sealed: the visitor pattern operates over a closed set of Visitables, and
 * sealing the hierarchy makes that closed set explicit to the compiler.  See the package javadoc
 * for how sealed interfaces plus pattern-matching switch offer a modern alternative to the
 * visitor pattern for closed hierarchies.
 *
 * @author John Dickerson - 22 February 2020
 */
public sealed interface ShapeVisitable permits TriangleVisitable, SquareVisitable {

    /**
     * The calling code, VisitorApplication, calls the accept method on TriangleVisitable or
     * SquareVisitable passing in SmallShapeVisitor or BigShapeVisitor. The accept implementation
     * will in turn call one of the overloaded visit methods of SmallShapeVisitor or
     * BigShapeVisitor.
     *
     * @param shapeVisitor
     *      The ShapeVisitor, either SmallShapeVisitor or BigShapeVisitor, to call the overloaded
     *      visit method on, and in so doing render a shape.
     *
     * @return the shape rendered by the visitor for this visitable
     */
    String accept( ShapeVisitor shapeVisitor );
}
