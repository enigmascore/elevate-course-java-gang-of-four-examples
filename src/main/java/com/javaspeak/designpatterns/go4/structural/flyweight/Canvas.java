package com.javaspeak.designpatterns.go4.structural.flyweight;

/**
 * CanvasElements are added to this canvas and then rendered as an ASCII art string.
 *
 * @author John Dickerson - 23 February 2020
 */
public interface Canvas {

    /**
     * Add a CanvasElement to the canvas.
     *
     * @param canvasElement
     *      The CanvasElement to add
     */
    void addCanvasElement( CanvasElement canvasElement );


    /**
     * Renders all the CanvasElements on the Canvas.
     *
     * @return the canvas rendered as ASCII art, one line per pixel row
     */
    String render();
}
