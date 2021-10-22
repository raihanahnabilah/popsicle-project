package com.example.popsicle.models;


public class CharacterPosition {

    private float x,y;

    /**
     *
     * @param x x-coordinate of Character
     * @param y y-coordinate of Character
     */
    public CharacterPosition(float x, float y){
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
    public void addLeft(){
        this.x = this.x - 20;
    }
    /**
     * Moves the character right
     * based on 20 pixels
     */
    public void addRight(){
        this.x = this.x + 20;
    }
    /**
     * Moves the character Up
     * based on 20 pixels
     */
    public void addUp(){
        this.y = this.y - 20;
    }
    /**
     * Moves the character Down
     * based on 20 pixels
     */
    public void addDown(){
        this.y = this.y + 20;
    }

    @Override
    public String toString() {
        return "CharacterPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
