package com.example.popsicle.models;

/**
 * This is the class for Candy
 */
public class Candy {
    private static final String TAG = "Candies";
    private CandyPosition pos;

    /**
     * Constructor: implementing the CandyPosition class
     * @param x x-coordinates of the Candy
     * @param y y-coordinates of the Candy
     */
    public Candy(float x, float y){
        this.pos = new CandyPosition(x, y);
    }

    /**
     *
     * @return pos Position of the Candy based on the CandyPosition class
     */
    public CandyPosition getPos() {
        return pos;
    }
}
