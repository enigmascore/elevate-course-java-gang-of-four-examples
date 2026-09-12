package com.javaspeak.designpatterns.go4.structural.composite;

/**
 * TextElement extends AbstractHtmlElement to provide a simple element implementation.
 * TextElement does not make use of its {@code List<HtmlElement>}.  It returns some simple text
 * in its getHtml() method.
 *
 * @author John Dickerson - 24 February 2020
 */
public class TextElement extends AbstractHtmlElement {

    private final String text;

    /**
     * Constructor.
     *
     * @param text the text this element renders in its getHtml() method
     */
    public TextElement( String text ) {

        this.text = text;
    }


    @Override
    public String getHtml() {

        return text;
    }
}
