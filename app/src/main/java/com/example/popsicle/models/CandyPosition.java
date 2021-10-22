package com.example.popsicle.models;

/**
 * Instantiates the x and y coordinates
 */
public class CandyPosition {
    private float x,y;

    /**
     *
     * @param x the x-coordinate for the Candy
     * @param y the y-coordinate for the Candy
     */
    public CandyPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return x the x-coordinate
     */
    public float getX() {
        return x;
    }

    /**
     *
     * @return y the y-coordinate
     */
    public float getY() {
        return y;
    }

}
