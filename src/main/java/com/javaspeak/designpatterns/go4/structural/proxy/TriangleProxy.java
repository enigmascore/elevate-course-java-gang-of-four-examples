package com.javaspeak.designpatterns.go4.structural.proxy;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This proxy class proxies the shape Subject.  After calling drawShape() on the subject it logs
 * how many times the drawShape() method has been called.
 * <p>
 * Note that the relationship to the subject is usually determined at compile time with the proxy
 * pattern.
 * <p>
 * With the decorator pattern, which is quite similar, the relationship is usually defined at
 * runtime.  A runtime relationship means that the class being decorated is passed in via a
 * constructor or setter and a compile time relationship means the proxy is hard coded to proxy
 * a specific class.
 * <p>
 * Decorators are often chained together while proxies are not chained.
 *
 * @author John Dickerson - 23 February 2020
 */
public class TriangleProxy implements Shape {

    private static final Logger logger = LoggerFactory.getLogger( TriangleProxy.class );

    private final Shape subject;
    private final AtomicInteger numberTimesInvoked = new AtomicInteger();

    /**
     * Creates the proxy around the subject it is proxying.
     *
     * @param subject
     *      The Shape subject to proxy
     */
    public TriangleProxy( Shape subject ) {

        this.subject = subject;
    }


    @Override
    public String drawShape() {

        String drawnShape = subject.drawShape();
        logger.info( "drawShape() has been invoked {} times",
                numberTimesInvoked.incrementAndGet() );

        return drawnShape;
    }


    /**
     * Returns how many times drawShape() has been invoked on this proxy.
     *
     * @return the number of drawShape() invocations so far
     */
    public int getNumberTimesInvoked() {

        return numberTimesInvoked.get();
    }
}
