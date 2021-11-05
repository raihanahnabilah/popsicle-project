package com.example.popsicle.models;

import android.content.res.Resources;

/**
 * Constants Class is to store the Constants values necessary for the elements in the Universe,
 * such as the pixel movement that the Syrup or Character moves, the width and height of the
 * elements (characters, clouds, popsicles, syrups, consoles), and the screen size of the
 * emulator device.
 * @author Hana, Valeria
 */
public class Constants {
    /**
     * The movement of the Syrup. Each time a Syrup move, it
     * is incremented by this value.
     */
    public static int syrupMovementPixelsX = 20;

    /**
     * The movement of the Character. Each time a Character move, it
     * is incremented by this value.
     */
    public static int charMovementPixels = 20;

    /**
     * The width of the Character
     */
    public static int charWidth = 176;

    /**
     * The height of the Character
     */
    public static int charHeight = 176;

    /**
     * The width of the Cloud
     */
    public static int cloudWidth = 286;

    /**
     * The height of the Cloud
     */
    public static int cloudHeight = 286;

    /**
     * The width of the Popsicle
     */
    public static int popsicleWidth = 137;

    /**
     * The height of the Popsicle
     */
    public static int popsicleHeight = 137;

    /**
     * The width of the Syrup
     */
    public static int syrupWidth = 70;

    /**
     * The height of the Syrup
     */
    public static int syrupHeight = 70;

    /**
     * The width of the Console
     */
    public static int consoleWidth = 140;

    /**
     * The height of the Console
     */
    public static int consoleHeight = 140;

    /**
     * The width of the emulator screen
     */
    public static int screenX ;

    /**
     * The height of the emulator screen
     */
    public static int screenY;

    /**
     * Constants constructor to get the screenX (width) and screenY (height) of
     * the device emulator. For unit testing purposes, since there is no device,
     * we set the default width to be 1080 px and the height to be 1794 px.
     */
    public Constants() {
        try {
            this.screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
            this.screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
        } catch (Exception e) {
            this.screenX = 1080;
            this.screenY = 1794;
        }
    }
}
