package com.example.popsicle.models;

/**
 * Console Class is to create the Console in the Universe.
 * It stores several information of the Console, such as its
 * Position in the universe, and the width and height of the Console.
 * @author Hana, Valeria
 */
public class Console {
    private static final String TAG = "Console";

    /**
     * Position is the position of the Console in the Universe
     */
    private Position pos;

    /**
     * Width is the width of the Console, Height is the height of the Console,
     * ScreenX is the screen width of the emulator device, ScreenY is the screen height of the emulator device
     */
    int width, height, screenY, screenX;

    /**
     * Getter function to get the width of the Console
     * @return width of the console
     */
    public int getWidth() {
        return width;
    }

    /**
     * Setter function to set the width of the Console
     * @param width width of the console
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter function to get the height of the Console
     * @return height of the console
     */
    public int getHeight() {
        return height;
    }

    /**
     * Setter function to set the height of the Console
     * @param height height of the console
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Console class constructor that takes the "direction" of the Console.
     * The direction is essentially which Console it is: Console Up, Down, Left, or Right.
     * If it's Console Up (the direction specified is "up"), then the Position of
     * Console Up will be on top of Left, Right, and Down Console. If it's Console Down,
     * (the direction specified is "down"), then the Position of Console Down will be below
     * the Up, Down, and Right Console. If it's Console Left (the direction specified is "left"),
     * then the Position of Console Left will be on the left of Up, Down, and Right Console.
     * If it's Console Right (the direction specified is "right"), then the Position of Console Right
     * will be on the right of Up, Down, and Left console.
     * The Console class constructor also sets the width and height of the Console
     * by taking the value from our Constants class.
     * @param direction Specifying Console "up", "down", "left", or "right"
     */
    public Console(String direction){
        Constants constants = new Constants();
        this.screenX = constants.screenX;
        this.screenY = constants.screenY;

        width = Constants.consoleWidth;
        height = Constants.consoleHeight;

        if (direction.equals("up")){
            this.pos = new Position((screenX * 28 )/32 - width, (screenY*3)/4- height);
        } else if (direction.equals("down")){
            this.pos = new Position((screenX * 28 )/32 - width, (screenY*3)/4+height);
        } else if (direction.equals("left")){
            this.pos = new Position((screenX * 28 )/32 - width - width, (screenY*3)/4+height-height);
        } else if (direction.equals("right")){
            this.pos = new Position((screenX * 28 )/32, (screenY*3)/4);
        }
    }


    /**
     * Getter function for the Console's position
     * @return The position of the Console
     */
    public Position getPos() {
        return pos;
    }
}
