package com.javaspeak.designpatterns.go4.creational.builder;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * BuildManager has a constructShape(..) method which calls the appropriate methods on the
 * SquareBuilder and TriangleBuilder to populate the Shape instance with values which model a
 * Square and Triangle respectively.
 * <p>
 * When a shape is constructed it is added to the "shapes" SortedSet.
 * <p>
 * The SortedSet sorts the Shapes by their paintingLevel.  Shapes which are on level 0 will be
 * painted before Shapes which are on a higher level.
 *
 * @author John Dickerson - 22 February 2020
 */
public class BuildManager {

    // When a shape is constructed it is added to the "shapes" SortedSet. The SortedSet sorts the
    // Shapes by their paintingLevel. Shapes which are on level 0 will be painted before Shapes
    // which are on a higher level.
    private final SortedSet<Shape> shapes =
            new TreeSet<>( Comparator.comparingInt( Shape::getPaintLevel ) );

    // The merged coordinates of all the constructed shapes. Merged lazily: paint() merges the
    // coordinates if mergeCoordinates() has not been called yet.
    private int[][] points;

    /**
     * Creates a BuildManager with no constructed Shapes.
     */
    public BuildManager() {

    }


    /**
     * Finds the maximum dimension for the different Shape instances in the shapes SortedSet.
     *
     * @param shapes
     *      the shapes to find the maximum dimension for
     *
     * @return
     *      max dimension
     */
    private Dimension getMaxDimension( SortedSet<Shape> shapes ) {

        int height = 0;
        int width = 0;

        for ( Shape shape : shapes ) {

            if ( shape.getPoints().length > height ) {

                height = shape.getPoints().length;
            }

            if ( shape.getPoints()[0].length > width ) {

                width = shape.getPoints()[0].length;
            }
        }

        return new Dimension( width, height );
    }


    /**
     * The constructShape(..) method is responsible for calling the builder methods in the
     * correct order.  For example a shape cannot have its coordinates translated before it has
     * created its points.
     *
     * @param shapeBuilder
     *      the builder which populates the Shape it is constructing
     */
    public void constructShape( ShapeBuilder shapeBuilder ) {

        shapeBuilder.setPaintingLevel();
        shapeBuilder.buildPoints();
        shapeBuilder.translateCoordinates();
        shapes.add( shapeBuilder.getShape() );
    }


    /**
     * This method iterates through the shapes and copies their coordinates into a new points
     * array.  Note that shapes which are copied after others overwrite previous points.
     */
    public void mergeCoordinates() {

        Dimension dimension = getMaxDimension( shapes );
        points = new int[dimension.height()][dimension.width()];

        for ( Shape shape : shapes ) {

            int[][] shapePoints = shape.getPoints();

            for ( int y = 0; y < shapePoints.length; y++ ) {

                for ( int x = 0; x < shapePoints[y].length; x++ ) {

                    if ( shapePoints[y][x] == 1 ) {

                        points[y][x] = shapePoints[y][x];
                    }
                }
            }
        }
    }


    /**
     * Paints the merged coordinates.  If the coordinates of the constructed shapes have not been
     * merged yet, they are merged first, so paint() can safely be called without calling
     * mergeCoordinates() beforehand.
     *
     * @return the painted shapes, one line per row of merged points
     */
    public String paint() {

        if ( points == null ) {

            mergeCoordinates();
        }

        var sb = new StringBuilder();

        for ( int[] row : points ) {

            for ( int point : row ) {

                sb.append( point == 1 ? '1' : ' ' );
            }

            sb.append( '\n' );
        }

        return sb.toString();
    }
}
