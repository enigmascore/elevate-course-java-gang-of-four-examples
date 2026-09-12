/**
 * Gang of Four Prototype pattern.
 * <p>
 * Text book description:
 * <p>
 * "Prototype: A fully initialized instance to be copied or cloned. Specify the kinds of
 * objects to create using a prototypical instance, and create new objects by copying this
 * prototype."
 * <p>
 * In this example the
 * {@link com.javaspeak.designpatterns.go4.creational.prototype.PrototypeFactory} caches one
 * pre-initialised prototype
 * {@link com.javaspeak.designpatterns.go4.creational.prototype.Shape} of each
 * {@link com.javaspeak.designpatterns.go4.creational.prototype.ShapeType} and returns copies of
 * the cached prototypes, made with copy constructors, instead of running the expensive Shape
 * constructors again.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.creational.prototype;
