/**
 * Gang of Four Adapter pattern.
 * <p>
 * Text book description:
 * <p>
 * "Adapter: Match interfaces of different classes. Convert the interface of a class into another
 * interface clients expect. Adapter lets classes work together that couldn't otherwise because
 * of incompatible interfaces."
 * <p>
 * In this example the application expects to call
 * {@link com.javaspeak.designpatterns.go4.structural.adapter.Square#draw()} (the target
 * interface), while the drawing is actually done by the third party adaptee
 * {@link com.javaspeak.designpatterns.go4.structural.adapter.ShapeImpl}.  The
 * {@link com.javaspeak.designpatterns.go4.structural.adapter.ShapeAdapter} implements Square and
 * wraps a {@link com.javaspeak.designpatterns.go4.structural.adapter.Shape}, translating draw()
 * calls into drawSquare() calls.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.structural.adapter;
