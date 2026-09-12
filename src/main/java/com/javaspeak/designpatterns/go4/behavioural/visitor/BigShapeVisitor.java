package com.javaspeak.designpatterns.go4.behavioural.visitor;

/**
 * BigShapeVisitor is responsible for drawing big Shapes.
 * <p>
 * It makes sense to centralise the code for drawing big Shapes.
 * <p>
 * As time goes on we may want to create new Visitors like "ShinyShapeVisitor" or
 * "ThreeDShapeVisitor".
 * <p>
 * Instead of having to go and edit many different shape classes to produce a different version of
 * their shape a new Visitor can be created and the code placed in one centralised place.
 * <p>
 * When we call the "accept( ShapeVisitor shapeVisitor )" method on a TriangleVisitable or
 * SquareVisitable we pass it an implementation of ShapeVisitor such as this BigShapeVisitor.
 * <p>
 * Internal to the accept method of TriangleVisitable or SquareVisitable you will see the visit
 * method is called on the Visitor (e.g. this class)
 *
 * @author John Dickerson - 22 February 2020
 */
public class BigShapeVisitor implements ShapeVisitor {

    /**
     * Creates a BigShapeVisitor.
     */
    public BigShapeVisitor() {

    }


    @Override
    public String getName() {

        return "BigShapeVisitor";
    }


    @Override
    public String visit( TriangleVisitable triangleVisitable ) {

        String shape = """
                    x
                   xxx
                  xxxxx
                 xxxxxxx
                xxxxxxxxx

                Triangle
                """;

        return shape.replace( "x", triangleVisitable.getCharacter() );
    }


    @Override
    public String visit( SquareVisitable squareVisitable ) {

        return """
                xxxxxxxx
                xxxxxxxx
                xxxxxxxx
                xxxxxxxx
                xxxxxxxx

                %s
                """.formatted( squareVisitable.getTitle() );
    }
}
