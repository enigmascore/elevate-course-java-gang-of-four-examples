package com.javaspeak.designpatterns.go4.structural.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class that implements HtmlElement to provide functionality for the addElement(..) and
 * getHtmlElements() methods.  Does not implement getHtml().  Both TableElement and TextElement
 * extend AbstractHtmlElement.  The reason TextElement extends AbstractHtmlElement is that a sub
 * class of TextElement may wish to become composite and make use of the list of
 * {@code HtmlElement}.  For example a more sophisticated implementation of TextElement may wish
 * to include elements such as "bold" and "underline".
 *
 * @author John Dickerson - 24 February 2020
 */
public abstract class AbstractHtmlElement implements HtmlElement {

    private final List<HtmlElement> htmlElements = new ArrayList<>();

    /**
     * Creates a html element, initially with no child elements.
     */
    protected AbstractHtmlElement() {
    }


    @Override
    public void addElement( HtmlElement htmlElement ) {

        htmlElements.add( htmlElement );
    }


    @Override
    public List<HtmlElement> getHtmlElements() {

        return Collections.unmodifiableList( htmlElements );
    }


    @Override
    public abstract String getHtml();
}
