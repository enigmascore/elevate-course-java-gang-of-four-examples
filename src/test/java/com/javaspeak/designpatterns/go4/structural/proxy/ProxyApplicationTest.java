package com.javaspeak.designpatterns.go4.structural.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Proxy pattern example: the proxy delegates drawing to its subject and counts how
 * many times drawShape() has been invoked.
 *
 * @author John Dickerson - 11 September 2026
 */
public class ProxyApplicationTest {

    private static final String TRIANGLE = """
               x
              x x
             x   x
            xxxxxxx
            """;

    @Test
    public void proxyDelegatesToItsSubject() {

        TriangleProxy triangleProxy = new TriangleProxy( new Triangle() );

        assertEquals( TRIANGLE, triangleProxy.drawShape() );
    }


    @Test
    public void proxyCountsInvocations() {

        TriangleProxy triangleProxy = new TriangleProxy( new Triangle() );

        assertEquals( 0, triangleProxy.getNumberTimesInvoked() );

        triangleProxy.drawShape();
        triangleProxy.drawShape();
        triangleProxy.drawShape();

        assertEquals( 3, triangleProxy.getNumberTimesInvoked() );
    }


    @Test
    public void runExampleDrawsTheTriangleTwice() {

        assertEquals( TRIANGLE + "\n" + TRIANGLE, new ProxyApplication().runExample() );
    }
}
