package com.javaspeak.designpatterns.go4.structural.composite;

import java.util.List;

/**
 * Used to define the structure of composite and simple elements.
 * <p>
 * AbstractHtmlElement provides implementations for addElement(..) and getHtmlElements() but
 * leaves getHtml() to be implemented by sub classes.  Both TableElement and TextElement extend
 * AbstractHtmlElement and provide an implementation for the getHtml() method.  TableElement is a
 * composite element which can itself be comprised of composite elements while TextElement is
 * simple.  TextElement is simple as it does not make use of its child elements in its getHtml()
 * method.
 *
 * @author John Dickerson - 24 February 2020
 */
public interface HtmlElement {

    /**
     * Adds a child {@code HtmlElement} to the end of this element's list of child elements.
     *
     * @param htmlElement the child HtmlElement to add
     */
    void addElement( HtmlElement htmlElement );


    /**
     * Returns the child {@code HtmlElement}s of this element.
     *
     * @return an unmodifiable view of the child HtmlElements; use addElement(..) to add to them
     */
    List<HtmlElement> getHtmlElements();


    /**
     * Returns the html.  If the HtmlElement is a simple element it will not be using its
     * {@code List<HtmlElement>}, however if it is a composite element it will iterate through
     * the child HtmlElements and call getHtml() on each of them while building up the html to
     * return.
     *
     * @return the generated html
     */
    String getHtml();
}
