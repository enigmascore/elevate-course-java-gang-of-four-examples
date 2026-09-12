package com.javaspeak.designpatterns.go4.structural.facade;

/**
 * Models a square which can draw itself as ASCII art.
 *
 * @author John Dickerson - 23 February 2020
 */
public interface Square {

    /**
     * Draws the square.
     *
     * @return the square rendered as ASCII art
     */
    String draw();
}
