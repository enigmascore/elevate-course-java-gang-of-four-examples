package com.javaspeak.designpatterns.go4.structural.flyweight;

import java.util.ArrayList;
import java.util.List;

/**
 * CanvasElements are added to this canvas and then rendered as an ASCII art string.
 *
 * @author John Dickerson - 23 February 2020
 */
public class CanvasImpl implements Canvas {

    private final List<CanvasElement> canvasElements = new ArrayList<>();

    /**
     * Creates an empty canvas.
     */
    public CanvasImpl() {
    }


    /**
     * Works out the dimension of the Canvas required to accommodate all the Canvas Elements.
     *
     * @param canvasElements
     *      CanvasElements which will be added to the Canvas
     *
     * @return
     *      the Dimension of the Canvas
     */
    private Dimension getCanvasDimension( List<CanvasElement> canvasElements ) {

        int height = 0;
        int width = 0;

        for ( CanvasElement canvasElement : canvasElements ) {

            height = Math.max(
                    height, canvasElement.shape().getHeight() + canvasElement.yCoordinate() );

            width = Math.max(
                    width, canvasElement.shape().getWidth() + canvasElement.xCoordinate() );
        }

        return new Dimension( width, height );
    }


    /**
     * Renders a CanvasElement on the canvas. This involves copying the pixels of the shape onto
     * the canvasPixels array.
     *
     * @param canvasElement
     *      The CanvasElement to render
     *
     * @param canvasPixels
     *      The canvas pixels to copy the shape's pixels onto
     */
    private void render( CanvasElement canvasElement, int[][] canvasPixels ) {

        Shape shape = canvasElement.shape();

        for ( int y = 0; y < shape.getHeight(); y++ ) {

            for ( int x = 0; x < shape.getWidth(); x++ ) {

                if ( shape.isPixelSet( y, x ) ) {

                    canvasPixels[y + canvasElement.yCoordinate()][x
                            + canvasElement.xCoordinate()] = 1;
                }
            }
        }
    }


    /**
     * Paints the canvas pixels as an ASCII art string.
     *
     * @param canvasPixels
     *      The canvas pixels to paint
     *
     * @return
     *      the painted canvas, one line per pixel row
     */
    private String paint( int[][] canvasPixels ) {

        var canvas = new StringBuilder();

        for ( int[] row : canvasPixels ) {

            var line = new StringBuilder();

            for ( int pixel : row ) {

                line.append( pixel == 1 ? "1" : " " );
            }

            canvas.append( line.toString().stripTrailing() ).append( '\n' );
        }

        return canvas.toString();
    }


    @Override
    public void addCanvasElement( CanvasElement canvasElement ) {

        canvasElements.add( canvasElement );
    }


    @Override
    public String render() {

        Dimension canvasDimension = getCanvasDimension( canvasElements );

        int[][] canvasPixels = new int[canvasDimension.height()][canvasDimension.width()];

        for ( CanvasElement canvasElement : canvasElements ) {

            render( canvasElement, canvasPixels );
        }

        return paint( canvasPixels );
    }
}
