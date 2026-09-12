/**
 * Gang of Four Visitor pattern.
 * <p>
 * Text book description:
 * <p>
 * "Visitor: Defines a new operation to a class without change. Represent an operation to be
 * performed on the elements of an object structure. Visitor lets you define a new operation
 * without changing the classes of the elements on which it operates."
 * <p>
 * In this example the Visitables,
 * {@link com.javaspeak.designpatterns.go4.behavioural.visitor.TriangleVisitable} and
 * {@link com.javaspeak.designpatterns.go4.behavioural.visitor.SquareVisitable}, accept a
 * {@link com.javaspeak.designpatterns.go4.behavioural.visitor.ShapeVisitor} such as
 * {@link com.javaspeak.designpatterns.go4.behavioural.visitor.SmallShapeVisitor} or
 * {@link com.javaspeak.designpatterns.go4.behavioural.visitor.BigShapeVisitor}, which renders
 * its own version of each shape.  New renderings can be added by writing a new visitor instead
 * of editing every shape class.
 * <p>
 * The classic visitor pattern operates over a closed set of element classes, so
 * {@link com.javaspeak.designpatterns.go4.behavioural.visitor.ShapeVisitable} is a sealed
 * interface permitting only TriangleVisitable and SquareVisitable.  Note that Java's sealed
 * interfaces combined with pattern-matching switch are the modern alternative to the visitor
 * pattern for closed hierarchies: the compiler checks that a switch over a sealed hierarchy is
 * exhaustive, giving the same "add an operation without touching the element classes" benefit
 * without the accept/visit double-dispatch plumbing.  VisitorApplication shows the equivalent
 * pattern-matching switch in a comment.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.visitor;
