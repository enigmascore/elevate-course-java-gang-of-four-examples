package com.javaspeak.designpatterns.go4.creational.builder;

/**
 * This abstract class instantiates a Shape class and provides an implementation for the
 * getShape() method.  It has several abstract methods that need to be implemented by builders
 * extending ShapeBuilder.
 *
 * @author John Dickerson - 22 February 2020
 */
public abstract class ShapeBuilder {

    /**
     * The Shape being built. Populated by the builder methods and returned by getShape().
     */
    protected final Shape shape = new Shape();

    /**
     * Creates the builder with an empty Shape to populate.
     */
    protected ShapeBuilder() {

    }


    /**
     * Implementing classes need to populate the points array in the Shape class.
     */
    public abstract void buildPoints();


    /**
     * Implementing classes need to specify the paintingLevel in the Shape class. The
     * paintingLevel specifies what order the shapes need to be painted in.  "0" means painted
     * first while larger numbers will be painted next.
     */
    public abstract void setPaintingLevel();


    /**
     * Implementing classes can provide an implementation that translates the points. For example
     * a translation of (1,2) moves each point down by one and to the right by 2.
     */
    public abstract void translateCoordinates();


    /**
     * Returns a reference to the shape.
     *
     * @return
     *      the shape
     */
    public Shape getShape() {

        return this.shape;
    }
}
