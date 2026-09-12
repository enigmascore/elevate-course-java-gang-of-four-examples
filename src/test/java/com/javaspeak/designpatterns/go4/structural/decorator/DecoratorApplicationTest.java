package com.javaspeak.designpatterns.go4.structural.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Decorator pattern example: each transformer performs its own transformation and
 * chaining works whichever transformer comes first in the chain.
 *
 * @author John Dickerson - 11 September 2026
 */
public class DecoratorApplicationTest {

    @Test
    public void capitaliseThenRemoveMultipleSpaces() {

        Transformer<String> transformer = new TextCapitaliseTransformer();
        transformer.addDecoration( new RemoveMultipleSpacesTransformer() );

        assertEquals( "HELLO WORLD!", transformer.transform( "Hello    World!" ) );
    }


    @Test
    public void removeMultipleSpacesThenCapitalise() {

        Transformer<String> transformer = new RemoveMultipleSpacesTransformer();
        transformer.addDecoration( new TextCapitaliseTransformer() );

        assertEquals( "HELLO WORLD!", transformer.transform( "Hello    World!" ) );
    }


    @Test
    public void unchainedTransformersTransformAlone() {

        assertEquals( "HELLO    WORLD!",
                new TextCapitaliseTransformer().transform( "Hello    World!" ) );

        assertEquals( "Hello World!",
                new RemoveMultipleSpacesTransformer().transform( "Hello    World!" ) );
    }


    @Test
    public void runExampleTransformsThroughTheChain() {

        assertEquals( "HELLO WORLD!", new DecoratorApplication().runExample() );
    }
}
