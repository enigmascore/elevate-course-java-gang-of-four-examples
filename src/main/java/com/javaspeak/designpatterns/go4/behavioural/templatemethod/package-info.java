/**
 * Gang of Four Template Method pattern.
 * <p>
 * Text book description:
 * <p>
 * "Template Method: Defer the exact steps of an algorithm to a subclass. Define the skeleton of
 * an algorithm in an operation, deferring some steps to subclasses.  Template Method lets
 * subclasses redefine certain steps of an algorithm without changing the algorithm's structure."
 * <p>
 * In this example
 * {@link com.javaspeak.designpatterns.go4.behavioural.templatemethod.AbstractHtmlPage} defines
 * the template method, buildPage(), which assembles an html page by calling the abstract methods
 * getTitle(), getHeader(), getContent() and getFooter() in the correct order.
 * {@link com.javaspeak.designpatterns.go4.behavioural.templatemethod.HomePage} supplies the
 * implementations of those steps.
 * <p>
 * If the assembled html is not well formed a
 * {@link com.javaspeak.designpatterns.go4.behavioural.templatemethod.FormatException} is thrown
 * while formatting it.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.behavioural.templatemethod;
