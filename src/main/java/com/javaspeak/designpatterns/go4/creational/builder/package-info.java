/**
 * Gang of Four Builder pattern.
 * <p>
 * Text book description:
 * <p>
 * "Builder: Separates object construction from its representation. Separate the construction
 * of a complex object from its representation so that the same construction processes can
 * create different representations."
 * <p>
 * In this example the {@link com.javaspeak.designpatterns.go4.creational.builder.BuildManager}
 * directs {@link com.javaspeak.designpatterns.go4.creational.builder.ShapeBuilder}s (a
 * SquareBuilder and a TriangleBuilder) through the construction steps of a
 * {@link com.javaspeak.designpatterns.go4.creational.builder.Shape} in the correct order, and
 * then merges and paints the constructed Shapes.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.creational.builder;
