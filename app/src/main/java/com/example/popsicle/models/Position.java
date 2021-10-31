package com.example.popsicle.models;

/**
 * Position class to construct the x and y coordinate of all our models classes,
 * such as Candy, Character, Clouds, Syrup, and Console
 * @author Hana
 */
public class Position {

    /**
     * x and y are the x-coordinate and y-coordinate of the Position class
     */
    private float x,y;

    /**
     * Position constructor to create the Position class
     * @param x x-coordinate of the model class (Candy, Character, Clouds, Syrup, Console)
     * @param y y-coordinate of the model class (Candy, Character, Clouds, Syrup, Console)
     */
    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * The x-coordinate getter
     * @return the x-coordinate of the Position
     */
    public float getX() {
        return x;
    }

    /**
     * The y-coordinate getter
     * @return the y-coordinate of the Position
     */
    public float getY() {
        return y;
    }

    /**
     * Moves the Position to the left
     * by 20 pixels by subtracting from the x-coordinate
     */
    public void addLeft(){
        this.x = this.x - Constants.charMovementPixels;
    }

    /**
     * Moves the Position to the right
     * by 20 pixels by adding from the x-coordinate
     */
    public void addRight(){
        this.x = this.x + Constants.charMovementPixels;
    }

    /**
     * Moves the Position up
     * by 20 pixels by subtracting from the y-coordinate
     */
    public void addUp(){
        this.y = this.y - Constants.charMovementPixels;
    }

    /**
     * Moves the Position down
     * by 20 pixels by adding from the y-coordinate
     */
    public void addDown(){
        this.y = this.y + Constants.charMovementPixels;
    }

    /**
     * General add method to move the Position of the model
     * by the input x and y coordinates
     * @param p The input position to be added to the
     *          current x and y coordinates
     */
    public void add(Position p) {
        this.x += p.x;
        this.y += p.y;
    }

    /**
     * toString method to print the Position x-coordinate and y-coordinate
     * for debugging purposes
     * @return the Position of the model, in x and y coordinates
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
