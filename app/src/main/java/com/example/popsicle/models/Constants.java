package com.example.popsicle.models;

import android.content.res.Resources;

public class Constants {
    public static int syrupMovementPixelsX = 20;
    public static int charMovementPixels = 20;
    public static int charWidth = 176;
    public static int charHeight = 176;
    public static int cloudWidth = 286;
    public static int cloudHeight = 286;
    public static int popsicleWidth = 137;
    public static int popsicleHeight = 137;
    public static int syrupWidth = 70;
    public static int syrupHeight = 70;
    public static int consoleWidth = 140;
    public static int consoleHeight = 140;
//    public static int screenX = Resources.getSystem().getDisplayMetrics().widthPixels;
//    public static int screenY = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static int screenX ;
    public static int screenY;
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
