package com.javaspeak.designpatterns.go4.behavioural.visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests the Visitor pattern example: both visitors produce their expected renderings over the
 * same visitable structure.
 *
 * @author John Dickerson - 11 September 2026
 */
public class VisitorApplicationTest {

    @Test
    public void smallShapeVisitorRendersSmallShapes() {

        ShapeVisitor smallShapeVisitor = new SmallShapeVisitor();
        ShapeVisitable triangle = new TriangleVisitable();
        ShapeVisitable square = new SquareVisitable();

        String expectedTriangle = """
                  T
                 TTT
                TTTTT
                """;

        String expectedSquare = """
                xxxx
                xxxx
                xxxx
                """;

        assertEquals( expectedTriangle, triangle.accept( smallShapeVisitor ) );
        assertEquals( expectedSquare, square.accept( smallShapeVisitor ) );
    }


    @Test
    public void bigShapeVisitorRendersBigShapes() {

        ShapeVisitor bigShapeVisitor = new BigShapeVisitor();
        ShapeVisitable triangle = new TriangleVisitable();
        ShapeVisitable square = new SquareVisitable();

        String expectedTriangle = """
                    T
                   TTT
                  TTTTT
                 TTTTTTT
                TTTTTTTTT

                Triangle
                """;

        String expectedSquare = """
                xxxxxxxx
                xxxxxxxx
                xxxxxxxx
                xxxxxxxx
                xxxxxxxx

                Square
                """;

        assertEquals( expectedTriangle, triangle.accept( bigShapeVisitor ) );
        assertEquals( expectedSquare, square.accept( bigShapeVisitor ) );
    }


    @Test
    public void runExampleRendersBothVisitorsOverTheSameStructure() {

        String report = new VisitorApplication().runExample();

        assertTrue( report.contains( "SmallShapeVisitor" ) );
        assertTrue( report.contains( "BigShapeVisitor" ) );
        assertTrue( report.contains( "TTTTT" ) );
        assertTrue( report.contains( "TTTTTTTTT" ) );
        assertTrue( report.contains( "Triangle" ) );
        assertTrue( report.contains( "Square" ) );
        assertTrue( report.indexOf( "SmallShapeVisitor" ) < report.indexOf( "BigShapeVisitor" ) );
    }
}
