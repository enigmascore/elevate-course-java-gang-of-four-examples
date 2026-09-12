package com.javaspeak.designpatterns.go4.structural.composite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the Composite pattern example: composite table elements render their child elements
 * (simple text and nested tables) into the expected nested html.
 *
 * @author John Dickerson - 11 September 2026
 */
public class CompositeApplicationTest {

    @Test
    public void tableElementRendersNestedChildren() {

        HtmlElement table = new TableElement();
        table.addElement( new TextElement( "Hello" ) );

        HtmlElement innerTable = new TableElement();
        innerTable.addElement( new TextElement( "World!" ) );
        table.addElement( innerTable );

        String expectedHtml = """
                <table cellspacing="5" cellpadding="5">
                <tr><td>Hello</td></tr>
                <tr><td><table cellspacing="5" cellpadding="5">
                <tr><td>World!</td></tr>
                </table>
                </td></tr>
                </table>
                """;

        assertEquals( expectedHtml, table.getHtml() );
    }


    @Test
    public void getHtmlElementsReturnsUnmodifiableView() {

        HtmlElement table = new TableElement();
        table.addElement( new TextElement( "Hello" ) );

        assertEquals( 1, table.getHtmlElements().size() );

        assertThrows(
                UnsupportedOperationException.class,
                () -> table.getHtmlElements().add( new TextElement( "World!" ) ) );
    }


    @Test
    public void runExampleGeneratesWebPageWithNestedTables() {

        String html = new CompositeApplication().runExample();

        assertTrue( html.startsWith( "<html>\n<head><title>Composite Pattern</title></head>" ) );
        assertTrue( html.contains( "<tr><td>Composite Pattern</td></tr>" ) );

        // the body table is nested inside a cell of the page table
        assertTrue( html.contains( """
                <tr><td><table cellspacing="5" cellpadding="5">
                <tr><td>Hello</td></tr>
                <tr><td>World!</td></tr>
                </table>
                </td></tr>
                """ ) );

        assertTrue( html.contains( "<tr><td>Footer & Copyright</td></tr>" ) );
        assertTrue( html.endsWith( "</body>\n</html>\n" ) );
    }
}
