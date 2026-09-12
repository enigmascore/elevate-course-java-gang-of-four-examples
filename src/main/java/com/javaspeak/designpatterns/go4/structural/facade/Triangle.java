package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Models a triangle which can draw itself as ASCII art.
 *
 * @author John Dickerson - 23 February 2020
 */
public interface Triangle {

    /**
     * Draws the triangle.
     *
     * @return the triangle rendered as ASCII art
     */
    String draw();
}
