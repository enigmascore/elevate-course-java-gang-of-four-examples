package com.javaspeak.designpatterns.go4.behavioural.templatemethod;

/**
 * Text book description:
 * <p>
 * "Template Method: Defer the exact steps of an algorithm to a subclass. Define the skeleton of
 * an algorithm in an operation, deferring some steps to subclasses.  Template Method lets
 * subclasses redefine certain steps of an algorithm without changing the algorithm's structure."
 * <p>
 * The Template Method Pattern uses an abstract class. The abstract class has a method
 * ( called buildPage() in this example ) which calls the abstract methods in the correct order.
 * <p>
 * The exact implementation of the abstract methods is left to sub classes of the abstract class.
 * <p>
 * In this example AbstractHtmlPage is extended by the class HomePage to provide implementation of
 * the abstract methods, getTitle(), getHeader(), getContent() and getFooter().
 *
 * @author John Dickerson - 22 February 2020
 */
public class TemplateMethodApplication {

    /**
     * Creates a TemplateMethodApplication.
     */
    public TemplateMethodApplication() {

    }


    /**
     * Runs the example: builds the HomePage using the template method of AbstractHtmlPage and
     * returns the formatted html.
     *
     * @return the formatted html of the HomePage
     */
    public String runExample() {

        AbstractHtmlPage htmlPage = new HomePage();
        return htmlPage.getHtml();
    }


    /**
     * Runs the example from the command line and prints the result.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        TemplateMethodApplication application = new TemplateMethodApplication();
        System.out.println( application.runExample() );
    }
}
