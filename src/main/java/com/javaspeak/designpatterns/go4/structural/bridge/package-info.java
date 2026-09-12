/**
 * Gang of Four Bridge pattern.
 * <p>
 * Text book description:
 * <p>
 * "Bridge: Separates an object's interface from its implementation. Decouple an abstraction from
 * its implementation so that the two can vary independently."
 * <p>
 * In this example the abstraction is
 * {@link com.javaspeak.designpatterns.go4.structural.bridge.AbstractShapeBridge}, which bridges
 * to a pluggable {@link com.javaspeak.designpatterns.go4.structural.bridge.ShapeBuilder}
 * implementation (here a
 * {@link com.javaspeak.designpatterns.go4.structural.bridge.TriangleBuilder}).
 * {@link com.javaspeak.designpatterns.go4.structural.bridge.ShapeBridgeImpl} extends the
 * abstraction with a drawShape() method, while the builder implementation can be swapped
 * independently.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.structural.bridge;
