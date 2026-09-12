package com.javaspeak.designpatterns.go4.creational.abstractfactory;

import com.javaspeak.designpatterns.go4.creational.abstractfactory.ShapeSelector.ShapeType;

/**
 * Text book description:
 * <p>
 * "Abstract Factory: Creates an instance of several families of classes. Provide an interface
 * for creating families of related or dependent objects without specifying their concrete
 * classes."
 * <p>
 * The idea behind the AbstractFactory pattern is that the implementation of some functionality
 * can be changed by plugging in a different Factory.
 * <p>
 * None of the calling code in the application needs to change to accommodate a different factory
 * apart from the code that decides which Factory to use.
 * <p>
 * In the code example the application retrieves a factory; then from the factory it gets a
 * shape which it then draws.  If the SquareFactory is plugged in, then a Square is drawn.
 * If the TriangleFactory is plugged in a Triangle is drawn.
 * <p>
 * The calling code in the application looks like:
 * <pre>
 *   ShapeSelector.getShapeFactory( ShapeType.SQUARE ).getShape().drawShape()
 *   ShapeSelector.getShapeFactory( ShapeType.TRIANGLE ).getShape().drawShape()
 * </pre>
 * Notice that a ShapeSelector is used to return a SquareFactory or a TriangleFactory.  Notice
 * that getShape() is then called on the factory which returns a Square or Triangle depending on
 * whether the factory is a SquareFactory or a TriangleFactory. Finally drawShape() is called.
 * If the shape is a Square a Square is drawn while if the shape is a Triangle a Triangle is
 * drawn:
 * <pre>
 * X X X X
 * X     X
 * X     X
 * X X X X
 *
 *
 *     x
 *    x x
 *   x   x
 *  x     x
 * x x x x x
 * </pre>
 *
 * @author John Dickerson - 22 February 2020
 */
public class AbstractFactoryApplication {

    /**
     * Creates the application.
     */
    public AbstractFactoryApplication() {

    }


    /**
     * Runs the example: retrieves a SquareFactory and a TriangleFactory from the ShapeSelector
     * and draws the Shape each factory creates.
     *
     * @return the drawn Square followed by the drawn Triangle
     */
    public String runExample() {

        return ShapeSelector.getShapeFactory( ShapeType.SQUARE ).getShape().drawShape()
                + "\n"
                + ShapeSelector.getShapeFactory( ShapeType.TRIANGLE ).getShape().drawShape();
    }


    /**
     * Runs the example and prints the drawn Shapes.
     *
     * @param args
     *      not used
     */
    public static void main( String[] args ) {

        AbstractFactoryApplication application = new AbstractFactoryApplication();
        System.out.println( application.runExample() );
    }
}
