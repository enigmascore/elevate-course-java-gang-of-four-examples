package com.javaspeak.designpatterns.go4.structural.composite;

import java.util.stream.Collectors;

/**
 * Extends AbstractHtmlElement to provide a composite implementation for a html table.  A html
 * table is considered a composite object as it can itself contain composite objects such as
 * other html tables.
 * <p>
 * TableElement wraps the child HtmlElements in "tr" and "td" html tags.
 *
 * @author John Dickerson - 24 February 2020
 */
public class TableElement extends AbstractHtmlElement {

    /**
     * Creates a table element, initially with no child elements.
     */
    public TableElement() {
    }


    @Override
    public String getHtml() {

        String rows = getHtmlElements().stream()
                .map( htmlElement -> "<tr><td>" + htmlElement.getHtml() + "</td></tr>\n" )
                .collect( Collectors.joining() );

        return """
                <table cellspacing="5" cellpadding="5">
                %s</table>
                """.formatted( rows );
    }
}
