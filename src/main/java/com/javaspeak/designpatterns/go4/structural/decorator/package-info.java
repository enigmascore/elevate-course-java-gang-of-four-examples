/**
 * Gang of Four Decorator pattern.
 * <p>
 * Text book description:
 * <p>
 * "Decorator: Add responsibilities to objects dynamically.  Attach additional responsibilities
 * to an object dynamically. Decorators provide a flexible alternative to subclassing for
 * extending functionality."
 * <p>
 * In this example
 * {@link com.javaspeak.designpatterns.go4.structural.decorator.Transformer} decorators are
 * chained together: each transformer performs its own transformation on the data and then passes
 * the result on to the transformer it decorates.  The example chains a
 * {@link com.javaspeak.designpatterns.go4.structural.decorator.TextCapitaliseTransformer} with a
 * {@link com.javaspeak.designpatterns.go4.structural.decorator.RemoveMultipleSpacesTransformer}.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.structural.decorator;
