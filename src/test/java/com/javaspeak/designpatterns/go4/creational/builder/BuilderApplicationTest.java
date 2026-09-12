package com.javaspeak.designpatterns.go4.creational.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the Builder pattern example: the BuildManager directs the builders through the
 * construction steps and the merged Shapes are painted lowest painting level first.
 *
 * @author John Dickerson - 11 September 2026
 */
public class BuilderApplicationTest {

    // The Square (painting level 0) is painted first; the Triangle (painting level 2) is
    // translated by (2,1) and painted on top of it.
    private static final String EXPECTED_MERGED_PAINTING =
            "1111     \n"
                    + "1  1 1   \n"
                    + "1  11 1  \n"
                    + "1111   1 \n"
                    + "  1111111\n";


    @Test
    public void runExamplePaintsMergedSquareAndTriangle() {

        assertEquals( EXPECTED_MERGED_PAINTING, new BuilderApplication().runExample() );
    }


    @Test
    public void paintMergesCoordinatesWhenMergeCoordinatesHasNotBeenCalled() {

        BuildManager buildManager = new BuildManager();
        buildManager.constructShape( new SquareBuilder() );
        buildManager.constructShape( new TriangleBuilder() );

        // paint() is called without calling mergeCoordinates() first
        assertEquals( EXPECTED_MERGED_PAINTING, buildManager.paint() );
    }


    @Test
    public void builderConstructsSquareOnPaintingLevelZero() {

        BuildManager buildManager = new BuildManager();
        SquareBuilder squareBuilder = new SquareBuilder();
        buildManager.constructShape( squareBuilder );

        Shape square = squareBuilder.getShape();

        assertEquals( 0, square.getPaintLevel() );
        assertEquals( 4, square.getPoints().length );
        assertEquals( 4, square.getPoints()[0].length );
    }
}
