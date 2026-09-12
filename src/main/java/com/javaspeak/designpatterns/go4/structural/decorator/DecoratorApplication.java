package com.javaspeak.designpatterns.go4.structural.decorator;

/**
 * Text book description:
 * <p>
 * "Decorator: Add responsibilities to objects dynamically.  Attach additional responsibilities
 * to an object dynamically. Decorators provide a flexible alternative to subclassing for
 * extending functionality."
 * <p>
 * The Decorator pattern is useful when some data needs to be transformed several times by
 * different plugable transformers.
 * <p>
 * A Decorator can transform the data and then call another Decorator it is chained to, to do
 * some further processing on the data.  When the data has been transformed by all the decorators
 * it is returned.
 * <p>
 * In our example we have 2 decorators.  One decorator transforms the text to upper case and the
 * other decorator replaces multiple spaces with one space.
 * <p>
 * Each Decorator (transformer) holds a reference to another Decorator unless it is the last
 * Decorator in the chain.
 * <p>
 * Note that the code for the structural Decorator pattern is the same as the behavioural Chain
 * of Responsibility pattern (see ChainOfResponsibilityApplication in the
 * chainofresponsibility package).
 *
 * @author John Dickerson - 20 February 2020
 */
public class DecoratorApplication {

    /**
     * Creates the example application.
     */
    public DecoratorApplication() {
    }


    /**
     * Runs the example: chains a capitalising transformer to a transformer which collapses
     * multiple spaces, then transforms "Hello    World!" through the chain.
     *
     * @return the transformed message, "HELLO WORLD!"
     */
    public String runExample() {

        Transformer<String> transformer = new TextCapitaliseTransformer();
        transformer.addDecoration( new RemoveMultipleSpacesTransformer() );

        return transformer.transform( "Hello    World!" );
    }


    /**
     * Runs the example and prints its output.
     *
     * @param args not used
     */
    public static void main( String[] args ) {

        DecoratorApplication application = new DecoratorApplication();
        System.out.println( application.runExample() );
    }
}
