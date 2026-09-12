package com.javaspeak.designpatterns.go4.creational.prototype;

import java.util.EnumMap;
import java.util.Map;

/**
 * Factory which caches one pre-initialised prototype Shape of each ShapeType and returns copies
 * of the cached prototypes instead of constructing new Shapes from scratch.
 *
 * @author John Dickerson - 24 February 2020
 */
public class PrototypeFactory {

    private static final Map<ShapeType, Shape> shapeCache =
            new EnumMap<>( Map.of(
                    ShapeType.SQUARE, new Square(),
                    ShapeType.TRIANGLE, new Triangle() ) );

    /**
     * Utility class which is not instantiated.
     */
    private PrototypeFactory() {

    }


    /**
     * Retrieves the prototype Shape from the map and returns a copy of it.
     *
     * @param shapeType
     *      the kind of Shape to retrieve
     *
     * @return a copy of the cached prototype Shape
     */
    public static Shape getShape( ShapeType shapeType ) {

        return shapeCache.get( shapeType ).copy();
    }
}
