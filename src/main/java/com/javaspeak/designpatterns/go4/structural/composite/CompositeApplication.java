package com.javaspeak.designpatterns.go4.structural.composite;

/**
 * Text book description:
 * <p>
 * "Composite: A tree structure of simple and composite objects. Compose objects into tree
 * structures to represent part-whole hierarchies. Composite lets clients treat individual
 * objects and compositions of objects uniformly."
 * <p>
 * Has a tree like structure of simple and composite objects.  A Composite object can itself hold
 * composite objects as well as simple objects.
 * <p>
 * In this example the composite objects are modelling html tables while the simple objects are
 * modelling text.  This example is not too dissimilar to the apache tiles framework.  The apache
 * tiles framework uses the composite pattern.
 *
 * @author John Dickerson - 22 February 2020
 */
public class CompositeApplication {

    /**
     * Creates the example application.
     */
    public CompositeApplication() {
    }


    /**
     * Runs the example: builds a web page made up of tables containing text and other tables.
     *
     * @return the generated html of the web page
     */
    public String runExample() {

        HtmlElement pageTableElement = new TableElement();
        pageTableElement.addElement( new TextElement( "Composite Pattern" ) );

        HtmlElement bodyTableElement = new TableElement();
        bodyTableElement.addElement( new TextElement( "Hello" ) );
        bodyTableElement.addElement( new TextElement( "World!" ) );

        pageTableElement.addElement( bodyTableElement );
        pageTableElement.addElement( new TextElement( "Footer & Copyright" ) );

        return """
                <html>
                <head><title>Composite Pattern</title></head>
                <body>
                %s</body>
                </html>
                """.formatted( pageTableElement.getHtml() );
    }


    /**
     * Runs the Composite example and prints the generated html.
     *
     * @param args command line arguments; not used
     */
    public static void main( String[] args ) {

        CompositeApplication application = new CompositeApplication();
        System.out.println( application.runExample() );
    }
}
