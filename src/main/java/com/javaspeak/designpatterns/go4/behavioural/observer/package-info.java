/**
 * Gang of Four Observer pattern.
 * <p>
 * Text book description:
 * <p>
 * "Observer: A way of notifying change to a number of classes. Define a one-to-many dependency
 * between objects so that when one object changes state, all its dependents are notified and
 * updated automatically."
 * <p>
 * In this example a {@link com.javaspeak.designpatterns.go4.behavioural.observer.Subject}
 * notifies its subscribed
 * {@link com.javaspeak.designpatterns.go4.behavioural.observer.Listener}s of a new
 * {@link com.javaspeak.designpatterns.go4.behavioural.observer.SubjectEvent}.
 * <p>
 * Note that the JDK's own {@code java.util.Observable} / {@code java.util.Observer} types are
 * deprecated; the modern JDK equivalent of this pattern is
 * {@link java.util.concurrent.Flow}. This package uses the names Subject / Listener to avoid
 * clashing with the deprecated JDK type names.
 *
 * @author John Dickerson - 22 February 2020
 */
package com.javaspeak.designpatterns.go4.behavioural.observer;
