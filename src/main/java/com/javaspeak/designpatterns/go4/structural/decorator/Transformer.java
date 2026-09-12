package com.javaspeak.designpatterns.go4.structural.decorator;

/**
 * This interface is central to this example's Decorator pattern. This Transformer (Decorator) has
 * a transform method which can transform data of type E.
 * <p>
 * The implementation of this Transformer interface can optionally decorate another Transformer.
 * <p>
 * What the decoration means is that the implementation of the transform( E input ) method should
 * do its transformation on E and then, if a Transformer has been previously added via its
 * addDecoration( Transformer transformer ) method, it should call the transform( E input )
 * method of that transformer.
 * <p>
 * This allows many Decorators to be chained together, each Decorator holding a reference to
 * (decorating) the next Decorator. The last Decorator in the chain will not hold a reference to
 * another Decorator.
 *
 * @param <E> the type of data being transformed
 *
 * @author John Dickerson - 20 February 2020
 */
public interface Transformer<E> {

    /**
     * The implementation of this method should perform a transformation on the input.  If the
     * Transformer holds a reference to another Transformer it should then call
     * transform( E input ) on that Transformer.
     *
     * @param input
     *     The input data to transform
     *
     * @return
     *     The transformed data
     */
    E transform( E input );


    /**
     * The implementation of this method needs to add a reference to the next transformer in
     * the chain.
     *
     * @param transformer
     *     The next transformer in the chain.
     */
    void addDecoration( Transformer<E> transformer );
}
