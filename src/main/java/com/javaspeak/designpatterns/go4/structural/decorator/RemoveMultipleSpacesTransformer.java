package com.javaspeak.designpatterns.go4.structural.decorator;

/**
 * This class is a Decorator which replaces runs of multiple whitespace characters with a single
 * space. It holds a reference to another decorator. The decorators implement Transformer.  The
 * reference to another decorator can be added by calling
 * addDecoration( {@code Transformer<String>} transformer ).
 * <p>
 * After calling transform on some input data it calls transform on the decorator it references.
 * <p>
 * In this way a chain of decorators (transformers) is created, the last transformer in the chain
 * holding no reference to a decorator.
 *
 * @author John Dickerson - 20 February 2020
 */
public class RemoveMultipleSpacesTransformer implements Transformer<String> {

    private Transformer<String> transformer;

    /**
     * Creates the transformer, initially with no decorator chained to it.
     */
    public RemoveMultipleSpacesTransformer() {
    }


    @Override
    public void addDecoration( Transformer<String> transformer ) {

        this.transformer = transformer;
    }


    @Override
    public String transform( String input ) {

        String output = input.replaceAll( "\\s+", " " );

        if ( this.transformer != null ) {

            return this.transformer.transform( output );
        }

        return output;
    }
}
