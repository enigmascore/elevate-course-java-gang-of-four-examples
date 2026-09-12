package com.javaspeak.designpatterns.go4.behavioural.templatemethod;

import java.util.ArrayList;
import java.util.List;

/**
 * The Template Method Pattern uses an abstract class. The abstract class has a method
 * ( buildPage() ) which calls the abstract methods in the correct order.
 * <p>
 * The implementation of the abstract methods is left to sub classes of the abstract class.
 *
 * @author John Dickerson - 22 February 2020
 */
public abstract class AbstractHtmlPage {

    /**
     * Creates an AbstractHtmlPage.
     */
    protected AbstractHtmlPage() {

    }


    /**
     * Provides the title of the page.
     *
     * @return the page title
     */
    public abstract String getTitle();


    /**
     * Provides the header fragment of the page.
     *
     * @return the page header html
     */
    public abstract String getHeader();


    /**
     * Provides the main content of the page.
     *
     * @return the page content
     */
    public abstract String getContent();


    /**
     * Provides the footer of the page.
     *
     * @return the page footer
     */
    public abstract String getFooter();


    /**
     * Splits the html into a list of tag and text elements.
     * <p>
     * Each iteration consumes at least one character of the remaining html, so the loop always
     * makes progress; malformed input fails fast with a FormatException instead of looping.
     *
     * @param html
     *      html to parse
     *
     * @return the tag and text elements in document order
     *
     * @throws FormatException
     *      if the html is not well formed, e.g. a {@code <} without a closing {@code >}, a
     *      stray {@code >} with no preceding {@code <}, or a {@code <} opened inside a tag
     */
    private List<String> getElements( String html ) {

        List<String> elements = new ArrayList<>();
        String remaining = html;

        while ( !remaining.isEmpty() ) {

            int start = remaining.indexOf( '<' );
            int end = remaining.indexOf( '>' );

            if ( start == -1 && end == -1 ) {

                elements.add( remaining );
                break;
            }

            if ( start == -1 || ( end != -1 && end < start ) ) {

                throw new FormatException(
                        "Found > without a preceding < near: \"" + remaining + "\"" );
            }

            if ( end == -1 ) {

                throw new FormatException(
                        "Found < but cannot find closing > near: \"" + remaining + "\"" );
            }

            int nextStart = remaining.indexOf( '<', start + 1 );

            if ( nextStart != -1 && nextStart < end ) {

                throw new FormatException(
                        "Found < before the previous < was closed with > near: \"" +
                                remaining.substring( start ) + "\"" );
            }

            if ( start > 0 ) {

                elements.add( remaining.substring( 0, start ) );
            }

            elements.add( remaining.substring( start, end + 1 ) );
            remaining = remaining.substring( end + 1 );
        }

        return elements;
    }


    /**
     * Build up the String for the tabs
     *
     * @param level
     *      the number of tabs
     *
     * @return
     *      the String of spaces representing the tabs
     */
    private String getTab( int level ) {

        return "   ".repeat( Math.max( 0, level ) );
    }


    /**
     * This method formats the html with proper space indents
     *
     * @param html
     *      The html to format
     *
     * @return
     *      formatted html with proper indents
     *
     * @throws FormatException
     *      if the html is not well formed
     */
    private String formatHtml( String html ) {

        String strippedHtml = html.trim().replace( "\n", "" ).replace( "\t", "" );

        int currentLevel = -1;
        List<String> elements = getElements( strippedHtml );
        StringBuilder sb = new StringBuilder();
        String previousElement = null;

        for ( String element : elements ) {

            if ( element.startsWith( "<" ) ) {

                if ( !element.contains( "/" ) ) {

                    if ( previousElement == null || !previousElement.startsWith( "</" ) ) {

                        currentLevel++;
                    }
                }
                else {

                    currentLevel--;
                }
            }
            else {

                currentLevel++;
            }

            sb.append( getTab( currentLevel ) );
            sb.append( element );
            sb.append( '\n' );

            previousElement = element;
        }

        return sb.toString();
    }


    /**
     * This method determines the order in which the abstract methods are called
     *
     * @return
     *      unformatted html
     */
    private String buildPage() {

        return """
                <html><head><title>%s</title></head><body>
                <table width="100%%" cellspacing="10" cellpadding="10" ><tr><td>%s</td></tr>
                <tr><td>%s</td></tr>
                <tr><td>%s</td></tr>
                <tr><td>%s</td></tr>
                </table></body></html>
                """.formatted( getTitle(), getHeader(), getTitle(), getContent(), getFooter() );
    }


    /**
     * Gets the formatted html
     *
     * @return
     *      formatted html
     *
     * @throws FormatException
     *      if the html is badly formed
     */
    public String getHtml() {

        String html = buildPage();
        return formatHtml( html );
    }
}
