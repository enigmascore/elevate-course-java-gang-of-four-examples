/**
 * Gang of Four Flyweight pattern.
 * <p>
 * Text book description:
 * <p>
 * "Flyweight: A fine-grained instance used for efficient sharing. Use sharing to support
 * large numbers of fine-grained objects efficiently. A flyweight is a shared object that can
 * be used in multiple contexts simultaneously. The flyweight acts as an independent object
 * in each context - it is indistinguishable from an instance of the object that is not shared."
 * <p>
 * In this example the immutable
 * {@link com.javaspeak.designpatterns.go4.structural.flyweight.Shape} flyweights are shared via
 * the {@link com.javaspeak.designpatterns.go4.structural.flyweight.ShapeCache} factory.  Many
 * {@link com.javaspeak.designpatterns.go4.structural.flyweight.CanvasElement}s reference the
 * same Shape instance while providing their own unique data: the location of the shape on the
 * {@link com.javaspeak.designpatterns.go4.structural.flyweight.Canvas}.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.structural.flyweight;
