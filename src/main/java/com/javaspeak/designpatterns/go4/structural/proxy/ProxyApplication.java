package com.javaspeak.designpatterns.go4.structural.proxy;

/**
 * Text book description:
 * <p>
 * "Proxy: An object representing another object. Provide a surrogate or placeholder for
 * another object to control access to it."
 * <p>
 * The proxy class in this example, TriangleProxy, proxies the Triangle Subject.  After
 * calling drawShape() on the subject it logs how many times the drawShape() method has been
 * called.
 * <p>
 * The proxy pattern is similar to the decorator pattern.  One of the differences is that with
 * the Proxy pattern the relationship between a proxy and the subject is usually defined at
 * compile time, whereas with decorators the relationship between the decorator and the class
 * being decorated can be defined at runtime.
 *
 * @author John Dickerson - 22 February 2020
 */
public class ProxyApplication {

    /**
     * Creates the example application.
     */
    public ProxyApplication() {
    }


    /**
     * Runs the example: wraps a Triangle in a TriangleProxy and draws it twice through the
     * proxy.  The proxy logs how many times drawShape() has been invoked.
     *
     * @return the shapes drawn by the two calls, as ASCII art
     */
    public String runExample() {

        Shape triangleProxy = new TriangleProxy( new Triangle() );

        return triangleProxy.drawShape() + "\n" + triangleProxy.drawShape();
    }


    /**
     * Main method.
     *
     * @param args
     *      command line arguments; not used
     */
    public static void main( String[] args ) {

        ProxyApplication application = new ProxyApplication();
        System.out.println( application.runExample() );
    }
}
