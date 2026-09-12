package com.javaspeak.designpatterns.go4.behavioural.visitor;

/**
 * The calling code, VisitorApplication, calls the accept method passing in a SmallShapeVisitor
 * or BigShapeVisitor. The accept method in turn calls the overloaded visit method of
 * SmallShapeVisitor or BigShapeVisitor.
 *
 * @author John Dickerson - 22 February 2020
 */
public final class SquareVisitable implements ShapeVisitable {

    /**
     * Creates a SquareVisitable.
     */
    public SquareVisitable() {

    }


    /**
     * This method is specific to SquareVisitable and is not in TriangleVisitable.  Note that a
     * reference to this class is passed into the accept(..) method as "this". Internally the
     * Visitors, SmallShapeVisitor and BigShapeVisitor, call getTitle() to add a title to the
     * Square.
     *
     * @return Title of Square
     */
    String getTitle() {

        return "Square";
    }


    @Override
    public String accept( ShapeVisitor shapeVisitor ) {

        return shapeVisitor.visit( this );
    }
}
