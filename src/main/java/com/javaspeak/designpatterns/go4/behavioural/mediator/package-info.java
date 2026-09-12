/**
 * Gang of Four Mediator pattern.
 * <p>
 * Text book description:
 * <p>
 * "Mediator: Defines simplified communication between classes. Define an object that encapsulates
 * how a set of objects interact. Mediator promotes loose coupling by keeping objects from
 * referring to each other explicitly, and it lets you vary their interaction independently."
 * <p>
 * In this example the
 * {@link com.javaspeak.designpatterns.go4.behavioural.mediator.ChatMediator} mediates between
 * {@link com.javaspeak.designpatterns.go4.behavioural.mediator.ChatUser}s.  Only the ChatMediator
 * has references to all ChatUsers: a ChatUser sends a message to a group via the ChatMediator and
 * the ChatMediator delivers it to the other ChatUsers subscribed to that group.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.mediator;
