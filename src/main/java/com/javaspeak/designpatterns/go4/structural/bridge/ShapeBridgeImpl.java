package com.javaspeak.designpatterns.go4.structural.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class extends the AbstractShapeBridge and adds functionality.
 * <p>
 * The added functionality is a drawShape() method.
 *
 * @author John Dickerson - 24 February 2020
 */
public class ShapeBridgeImpl extends AbstractShapeBridge {

    private static final Logger logger = LoggerFactory.getLogger( ShapeBridgeImpl.class );

    /**
     * Constructor.  Plugs a TriangleBuilder into the bridge.
     */
    public ShapeBridgeImpl() {

        super( new TriangleBuilder() );
    }


    /**
     * Provides an additional method which the application can call.
     * <p>
     * Extends the functionality of AbstractShapeBridge.
     *
     * @return the drawn shape as ASCII art
     */
    public String drawShape() {

        logger.info( "Drawing a Shape using {}", shapeBuilder.getClass().getName() );
        return buildShape().draw();
    }
}
