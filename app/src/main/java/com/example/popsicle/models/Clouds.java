package com.example.popsicle.models;

/**
 * Clouds Class is to create the Clouds in the Universe.
 * It stores several information of the Clouds, such as its
 * Position in the universe, and the width and height of the Clouds.
 * @author Hana, Valeria
 */
public class Clouds {
    private static final String TAG = "Clouds";

    /**
     * Position is the position of the Cloud in the Universe
     */
    private Position pos;

    /**
     * Width is the width of the Cloud, Height is the height of the Cloud,
     * ScreenX is the screen width of the emulator device, ScreenY is the screen height of the emulator device
     */
    public int width, height, screenX, screenY;

    /**
     * Direction is the which cloud is the user creating in the Universe
     */
    String direction;

    /**
     * Clouds class constructor that takes the "direction" of the Clouds.
     * The direction is essentially which Clouds it is: Cloud A1, A2, B1, or B2.
     * If it's Cloud A1 (the direction specified is "a1"), then the Position of
     * Cloud A1 will be on the top left of the screen. If it's Cloud A2 (the
     * direction specified is "a2"), then the Position of Cloud A2 will be on the
     * bottom left of the screen. If it's Cloud B1 (the direction specified is "b1"),
     * then the Position of Cloud B1 will be on the top right of the screen.
     * If it's Cloud B2 (the direction specified is "b2"), then the Position of Cloud B2
     * will be on the bottom right of the screen.
     * The Clouds class constructor also sets the width and height of the Clouds
     * by taking the value from our Constants class.
     * @param direction Specifying Cloud "a1", "a2", "b1", or "b2"
     */
    public Clouds(String direction){
        this.direction = direction;
        Constants constants = new Constants();
        this.screenX = constants.screenX;
        this.screenY = constants.screenY;

        this.width = Constants.cloudWidth;
        this.height = Constants.cloudHeight;

        if (direction == "a1"){
            this.pos = new Position(screenX/4, (screenY /64));
        } else if (direction == "a2"){
            this.pos = new Position(screenX/8, (screenY *7)/10);
        } else if (direction == "b1"){
            this.pos = new Position((screenX *3)/4, (screenY /60));
        } else if (direction == "b2"){
            this.pos = new Position(screenX /2, (screenY *3)/4);
        }
    }

    /**
     * Getter function for the Clouds' position
     * @return The position of the Clouds
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Getter function to get the width of the Clouds
     * @return width of the clouds
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter function to get the height of the Clouds
     * @return height of the clouds
     */
    public int getHeight() {
        return height;
    }
}
