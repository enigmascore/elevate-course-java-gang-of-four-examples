/**
 * Gang of Four Iterator pattern.
 * <p>
 * Text book description:
 * <p>
 * "Iterator: The iterator pattern is used to provide a standard interface for traversing a
 * collection of items in an aggregate object without the need to understand its underlying
 * structure."
 * <p>
 * The aggregate object in this example,
 * {@link com.javaspeak.designpatterns.go4.behavioural.iterator.ConcurrentLinkedList}, is a
 * lock-free multi-threaded data structure built on non-blocking CAS operations, so on the side
 * of learning the pattern this package also shows how to build your own concurrent collection
 * using {@link java.lang.invoke.VarHandle} atomics.
 *
 * @author John Dickerson - 21 February 2020
 */
package com.javaspeak.designpatterns.go4.behavioural.iterator;
