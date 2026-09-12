package com.javaspeak.designpatterns.go4.behavioural.visitor;

/**
 * Both BigShapeVisitor and SmallShapeVisitor implement this interface.
 * <p>
 * BigShapeVisitor encapsulates the code for drawing big triangles and big squares while
 * SmallShapeVisitor encapsulates the code for drawing small triangles and small squares.
 * <p>
 * The visitors, BigShapeVisitor and SmallShapeVisitor, make it easier to maintain code relating
 * to big and small shapes.
 * <p>
 * Calling accept on a TriangleVisitable or SquareVisitable delegates the call to the visitor
 * passed in as a method argument to the accept method. The visit method is called on the
 * ShapeVisitor, BigShapeVisitor or SmallShapeVisitor.
 * <p>
 * Notice that the visit methods are overloaded: one takes in a TriangleVisitable and the other
 * a SquareVisitable.
 *
 * @author John Dickerson - 22 February 2020
 */
public interface ShapeVisitor {

    /**
     * Gets the name of the ShapeVisitor, e.g. SmallShapeVisitor or BigShapeVisitor
     *
     * @return
     *      name of the ShapeVisitor
     */
    String getName();


    /**
     * Calling accept on a TriangleVisitable delegates the call to the visitor passed in as a
     * method argument to the accept method. The visit method is called on the ShapeVisitor:
     * BigShapeVisitor or SmallShapeVisitor.  BigShapeVisitor and SmallShapeVisitor implement this
     * ShapeVisitor interface and do the actual rendering of the triangle.
     *
     * @param triangleVisitable
     *      The TriangleVisitable to retrieve triangle properties from such as the character to
     *      use to draw the triangle with.
     *
     * @return the rendered triangle
     */
    String visit( TriangleVisitable triangleVisitable );


    /**
     * Calling accept on a SquareVisitable delegates the call to the visitor passed in as a method
     * argument to the accept method. The visit method is called on the ShapeVisitor,
     * BigShapeVisitor or SmallShapeVisitor.  BigShapeVisitor and SmallShapeVisitor implement this
     * ShapeVisitor interface and do the actual rendering of the square.
     *
     * @param squareVisitable
     *      The SquareVisitable to retrieve square properties from such as the title to render
     *      under the square.
     *
     * @return the rendered square
     */
    String visit( SquareVisitable squareVisitable );
}
