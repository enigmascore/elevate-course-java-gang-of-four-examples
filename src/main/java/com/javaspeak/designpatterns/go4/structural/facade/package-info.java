/**
 * Gang of Four Facade pattern.
 * <p>
 * Text book description:
 * <p>
 * "Facade: A single class that represents an entire subsystem. Provide a unified interface to
 * a set of interfaces in a system. Facade defines a higher-level interface that makes the
 * subsystem easier to use."
 * <p>
 * In this example the
 * {@link com.javaspeak.designpatterns.go4.structural.facade.ShapeFacade} is the single entry
 * point to the shape subsystem: callers ask it for shapes while it internally delegates to the
 * {@link com.javaspeak.designpatterns.go4.structural.facade.SquareBuilder} and
 * {@link com.javaspeak.designpatterns.go4.structural.facade.TriangleBuilder} interfaces.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.structural.facade;
