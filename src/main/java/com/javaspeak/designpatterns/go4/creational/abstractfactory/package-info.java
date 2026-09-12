/**
 * Gang of Four Abstract Factory pattern.
 * <p>
 * Text book description:
 * <p>
 * "Abstract Factory: Creates an instance of several families of classes. Provide an interface
 * for creating families of related or dependent objects without specifying their concrete
 * classes."
 * <p>
 * In this example the
 * {@link com.javaspeak.designpatterns.go4.creational.abstractfactory.ShapeSelector} returns a
 * {@link com.javaspeak.designpatterns.go4.creational.abstractfactory.ShapeFactory} for a
 * requested shape type.  Depending on whether a SquareFactory or a TriangleFactory is plugged
 * in, the {@link com.javaspeak.designpatterns.go4.creational.abstractfactory.Shape} the factory
 * creates draws a Square or a Triangle.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.creational.abstractfactory;
