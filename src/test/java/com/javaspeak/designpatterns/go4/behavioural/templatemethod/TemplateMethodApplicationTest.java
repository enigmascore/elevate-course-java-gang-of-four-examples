package com.javaspeak.designpatterns.go4.behavioural.templatemethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the Template Method pattern example: the rendered html page has the expected structure
 * and malformed html fails fast with a FormatException.
 *
 * @author John Dickerson - 11 September 2026
 */
public class TemplateMethodApplicationTest {

    /**
     * A page whose content is malformed html: a stray {@code >} with no preceding {@code <}.
     * This is the kind of input on which a naive tokenizer could fail to make progress.
     */
    private static class StrayClosingBracketPage extends HomePage {

        @Override
        public String getContent() {

            return "oops > broken";
        }
    }


    /**
     * A page whose footer opens a tag that is never closed.
     */
    private static class UnclosedTagPage extends HomePage {

        @Override
        public String getFooter() {

            return "<b unclosed";
        }
    }


    @Test
    public void rendersHtmlPageStructure() {

        String html = new HomePage().getHtml();
        String[] lines = html.split( "\n" );

        assertEquals( "<html>", lines[0] );
        assertEquals( "   <head>", lines[1] );
        assertEquals( "      <title>", lines[2] );
        assertEquals( "         My Home Page", lines[3] );
        assertEquals( "      </title>", lines[4] );
        assertEquals( "   </head>", lines[5] );

        assertTrue( html.contains( "Google" ) );
        assertTrue( html.contains( "All knowledge can be had from google" ) );
        assertTrue( html.contains( "Design Production" ) );
        assertTrue( html.contains( "</html>" ) );
    }


    @Test
    public void runExampleReturnsRenderedPage() {

        String html = new TemplateMethodApplication().runExample();

        assertTrue( html.startsWith( "<html>" ) );
        assertTrue( html.contains( "My Home Page" ) );
        assertTrue( html.contains( "</html>" ) );
    }


    @Test
    public void strayClosingBracketThrowsFormatException() {

        FormatException exception = assertThrows(
                FormatException.class, () -> new StrayClosingBracketPage().getHtml() );

        assertTrue( exception.getMessage().contains( "Found > without a preceding <" ) );
    }


    @Test
    public void unclosedTagThrowsFormatException() {

        FormatException exception = assertThrows(
                FormatException.class, () -> new UnclosedTagPage().getHtml() );

        assertTrue( exception.getMessage().contains( "before the previous < was closed" ) );
    }
}
