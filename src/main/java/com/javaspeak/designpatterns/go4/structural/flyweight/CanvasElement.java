package com.javaspeak.designpatterns.go4.structural.flyweight;

/**
 * References Shape. Shape is a flyweight object which can be used by many CanvasElement
 * instances.  The implementation for Shape encapsulates the data required to render the
 * underlying shape.  Shape does not specify the coordinates of the Shape on the Canvas as
 * different CanvasElements are likely to position the shapes in different locations on the
 * Canvas.  Instead CanvasElement defines unique data such as the location of the Shape on the
 * Canvas.
 * <p>
 * The Shape is integral to the flyweight pattern. Its data has been refactored out of
 * CanvasElement into the Shape implementation as it is identical data required by many
 * instances.
 *
 * @param shape
 *      The flyweight instance to reference
 *
 * @param xCoordinate
 *      The X location of the Shape on the canvas
 *
 * @param yCoordinate
 *      The Y location of the Shape on the canvas
 *
 * @author John Dickerson - 23 February 2020
 */
public record CanvasElement( Shape shape, int xCoordinate, int yCoordinate ) {
}
