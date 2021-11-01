package com.example.popsicle.models;

import android.graphics.Rect;

/**
 * Syrup Class is to create the Syrup in the Universe.
 * It stores several information of the Syrups, such as the movement
 * of the Syrup (incremented by x-coordinate and y-coordinate), depending
 * on which Syrup it is. It also stores information of the width
 * and height of the Syrup.
 * @author Hana, Valeria
 */
public class Syrup {

    /**
     * Position is the position of the Syrup in the Universe
     */
    private Position pos;

    /**
     * Width is the width of the Syrup, Height is the height of the Syrup,
     * ScreenX is the screen width of the emulator device, ScreenY is the screen height of the emulator device
     */
    int width, height, screenX, screenY;

    /**
     * The x-coordinate and y-coordinate of the Syrup's movement to get incremented
     */
    float moveX, moveY;

    /**
     * Getter function to get the width of the Syrup
     * @return width of the Syrup
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter function to get the height of the Syrup
     * @return height of the Syrup
     */
    public int getHeight() {
        return height;
    }

    /**
     * Syrup class constructor that takes the "direction" of the Syrup.
     * The direction is essentially which Syrup it is: Syrup A1, A2, B1, or B2.
     * Syrup A1 means that the Syrup is coming from Cloud A1. Syrup A2 means
     * that the Syrup is coming from Cloud A2. Syrup B1 means that the Syrup is coming
     * from Cloud B1. Syrup B2 means that the Syrup is coming from Cloud B2.
     * Depending on which Syrup it is, the movement of the Syrup will be different.
     * If it's Syrup A1, then the Syrups will be moving down right. If it's Syrup A2,
     * then the Syrups will be moving up right. If it's Syrup B1, then the Syrups
     * will be moving down left. If it's Syrup B2, then the Syrups will be moving
     * up left.
     *
     * The Syrup class constructor also sets the width and height of the Syrups
     * by taking the value from our Constants class.
     * @param direction Specifying Syrup "a1", "a2", "b1", or "b2"
     */
    public Syrup(String direction){
        Constants constants = new Constants();
        this.screenX = constants.screenX;
        this.screenY = constants.screenY;

        width = Constants.syrupWidth;
        height = Constants.syrupHeight;

        if (direction == "a1"){
            moveX =  Constants.syrupMovementPixelsX;
            moveY = Constants.syrupMovementPixelsX*(screenX/screenY);
        }
        if (direction == "a2"){
            moveX =  Constants.syrupMovementPixelsX;
            moveY = -Constants.syrupMovementPixelsX*(screenX/screenY);
        }
        if (direction == "b1"){
            moveX =  -Constants.syrupMovementPixelsX;
            moveY = Constants.syrupMovementPixelsX*(screenX/screenY);
        }
        if (direction == "b2"){
            moveX =  -Constants.syrupMovementPixelsX;
            moveY = -Constants.syrupMovementPixelsX*(screenX/screenY);
        }
    }

    /**
     * Creates the rectangle of the Syrup based on the size of the Syrup picture.
     * The purpose of this is so that when a Syrup collides with a Character
     * by the coordinates of their rectangle pictures, then the game will be terminated.
     * @return the Rectangle picture of the Syrup
     */
    public Rect getCollisionShape(){
        int left = (int) this.getPos().getX()*33/32;
        int top = (int) this.getPos().getY()*33/32;
        int right = (int) (this.getPos().getX() + this.getWidth())*31/32;
        int bottom = (int) (this.getPos().getY() + this.getHeight())*61/64;

        return new Rect(left, top, right, bottom);
    }

    /**
     * Moves the Syrup in the specified direction, using the method from the Position class
     * @param p the Position of the syrup
     */
    public void syrupMove(Position p) {
        this.pos.add(p);
    }

    /**
     * Getter function for the Syrup's position
     * @return The position of the Syrup
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Setter function for the Syrup' position
     * @param pos The position of the Syrup
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

    /**
     * Getter function to get the new x-coordinate for the Syrups to move
     * @return the x-coordinate of Syrups' movement
     */
    public float getMovex() {
        return moveX;
    }

    /**
     * Getter function to get the new y-coordinate for the Syrups to move
     * @return the y-coordinate of Syrups' movement
     */
    public float getMovey() {
        return moveY;
    }


}