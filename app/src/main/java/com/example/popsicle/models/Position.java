package com.example.popsicle.models;


public class Position {

    private float x,y;

    /**
     *
     * @param x x-coordinate of Character
     * @param y y-coordinate of Character
     */
    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * The following methods are called when the user moves based on the imput
     * The character will 'move' based on adding or subtracting values from
     * x and y coordinates
     */


    /**
     * Moves the character left
     * based on 20 pixels
     */
//    constants here need to be replace with constants.sth
    public void addLeft(){
        this.x = this.x - Constants.charMovementPixels;
    }
    /**
     * Moves the character right
     * based on 20 pixels
     */
    public void addRight(){
        this.x = this.x + Constants.charMovementPixels;
    }
    /**
     * Moves the character Up
     * based on 20 pixels
     */
    public void addUp(){
        this.y = this.y - Constants.charMovementPixels;
    }
    /**
     * Moves the character Down
     * based on 20 pixels
     */
    public void addDown(){
        this.y = this.y + Constants.charMovementPixels;
    }

    public void add(Position p) {
        this.x += p.x;
        this.y += p.y;
    }

    @Override
    public String toString() {
        return "CharacterPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
