package com.javaspeak.designpatterns.go4.behavioural.visitor;

/**
 * The calling code, VisitorApplication, calls the accept method passing in a SmallShapeVisitor
 * or BigShapeVisitor. The accept method in turn calls the overloaded visit method of
 * SmallShapeVisitor or BigShapeVisitor.
 *
 * @author John Dickerson - 22 February 2020
 */
public final class TriangleVisitable implements ShapeVisitable {

    /**
     * Creates a TriangleVisitable.
     */
    public TriangleVisitable() {

    }


    /**
     * This method is specific to TriangleVisitable and is not in SquareVisitable.  Note that a
     * reference to this class is passed into the accept(..) method as "this". Internally the
     * Visitors, SmallShapeVisitor and BigShapeVisitor, call getCharacter() to get the letter
     * used to render the Triangle.
     *
     * @return the character used to draw the Triangle with
     */
    String getCharacter() {

        return "T";
    }


    @Override
    public String accept( ShapeVisitor shapeVisitor ) {

        return shapeVisitor.visit( this );
    }
}
