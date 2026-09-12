/**
 * Gang of Four Singleton pattern.
 * <p>
 * Text book description:
 * <p>
 * "Singleton: A class of which only a single instance can exist. Ensure a class only has one
 * instance, and provide a global point of access to it."
 * <p>
 * In this example the
 * {@link com.javaspeak.designpatterns.go4.creational.singleton.ShapeManager} is the singleton:
 * its single instance is created eagerly in a final field initializer and retrieved through the
 * static getInstance() method, which is the global point of access used to build and draw a
 * {@link com.javaspeak.designpatterns.go4.creational.singleton.Square}.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.creational.singleton;
