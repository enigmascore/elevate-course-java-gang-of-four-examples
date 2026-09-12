/**
 * Gang of Four Composite pattern.
 * <p>
 * Text book description:
 * <p>
 * "Composite: A tree structure of simple and composite objects. Compose objects into tree
 * structures to represent part-whole hierarchies. Composite lets clients treat individual
 * objects and compositions of objects uniformly."
 * <p>
 * In this example a web page is composed from
 * {@link com.javaspeak.designpatterns.go4.structural.composite.HtmlElement}s: the composite
 * {@link com.javaspeak.designpatterns.go4.structural.composite.TableElement} can contain other
 * tables as well as simple
 * {@link com.javaspeak.designpatterns.go4.structural.composite.TextElement}s, and calling
 * getHtml() on the root element renders the whole tree.
 *
 * @author John Dickerson - 11 September 2026
 */
package com.javaspeak.designpatterns.go4.structural.composite;
