package com.example.popsicle.models;

import android.graphics.Rect;

/**
 * Candy Class is to create the Candies/Popsicles in the Universe.
 * It stores several information of the Candies/Popsicles, such as its
 * Position in the universe, and the width and height of Candy/Popsicle.
 * @author Hana, Valeria
 */
public class Candy {
    private static final String TAG = "Candies";

    /**
     * Width is the width of the Candy/Popsicle, Height is the height of the Candy/Popsicle,
     * ScreenX is the screen width of the emulator device, ScreenY is the screen height of the emulator device
     */
    int width, height, screenX, screenY;

    /**
     * Position is the position of the Candy/Popsicle in the Universe
     */
    private Position pos;

    /**
     * Getter function to get the width of the Candy/Popsicle
     * @return width of the candy/popsicle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter function to get the height of the Candy/Popsicle
     * @return height of the candy/popsicle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Candy class constructor that takes the "direction" of the Candy/Popsicle.
     * The direction is essentially which Candy/Popsicle it is: Candy/Popsicle A or Candy/Popsicle Popsicle B.
     * If it's Candy/Popsicle A (the direction specified is "a"), then the Position of
     * Candy/Popsicle A will be on the left of the screen.
     * If it's Candy/Popsicle B (the direction specified is "b"), then the Position of
     * Candy/Popsicle B will be on the right of the screen.
     * The Candy class constructor also sets the width and height of the Candy/Popsicle
     * by taking the value from our Constants class.
     * @param direction Specifying Candy/Popsicle "a" or "b"
     */
    public Candy(String direction){
        Constants constants = new Constants();
        this.screenX = constants.screenX;
        this.screenY = constants.screenY;

        if (direction == "a"){
            this.pos = new Position(screenX/16, (screenY *45)/100);
        } else if (direction == "b"){
            this.pos = new Position((screenX * 14)/16, (screenY *45)/100);
        }

        this.width = Constants.popsicleWidth;
        this.height = Constants.popsicleHeight;
    }

    /**
     * Creates the rectangle of the Candy/Popsicle based on the size of the Candy/Popsicle picture.
     * The purpose of this is so that when a Candy/Popsicle collides with a Character
     * by the coordinates of their rectangle pictures, then the game will be terminated.
     * @return the Rectangle picture of the Candy/Popsicle
     */
    public Rect getCollisionShape(){
        int left = (int) this.getPos().getX();
        int top = (int) this.getPos().getY();
        int right = (int) this.getPos().getX() + this.getWidth();
        int bottom = (int) this.getPos().getY() + this.getHeight();

        return new Rect(left, top, right, bottom);
    }

    /**
     * Getter function for the Candy/Popsicle's position
     * @return The position of the Candy/Popsicle
     */
    public Position getPos() {
        return pos;
    }
}
