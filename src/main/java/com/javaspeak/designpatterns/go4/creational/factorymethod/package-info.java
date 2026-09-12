/**
 * Gang of Four Factory Method pattern.
 * <p>
 * Text book description:
 * <p>
 * "Factory Method: Creates an instance of several derived classes. Define an interface for
 * creating an object, but let subclasses decide which class to instantiate. Factory Method
 * lets a class defer instantiation to subclasses."
 * <p>
 * In this example the {@link com.javaspeak.designpatterns.go4.creational.factorymethod.Drawing}
 * class has the factory methods createSquare() and createTriangle() which create
 * {@link com.javaspeak.designpatterns.go4.creational.factorymethod.Shape} implementations
 * (a Square and a Triangle) without the calling code naming the concrete classes.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.creational.factorymethod;
