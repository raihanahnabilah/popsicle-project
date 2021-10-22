package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.popsicle.R;

/*
* Class representing what is 'shot' from the clouds to make the enemy character loose lives
 */
public class Syrup {
    private SyrupPosition pos;
    String clouds;

    // Add syrup
    // Every single clouds need to have a handle of the global state that is the syrup
    // Global object to update the universe -- to keep all of the elements updated
    // Direction inside the syrup object

    /*
    * Creates a new SyrupPosition for the Syrup
    * @param x assign the initial x coordinate location of the syrup drop
    * @param y assign the initial y coordinate location of the syrup drop
     */
    public Syrup(float x, float y) {
        this.pos = new SyrupPosition(x,y);
    }

    /*
    * Updates the location of the drop for it to move
    * @param m position coordinates
     */
    public void move(SyrupMotion m) {
        this.pos.add(m);
    }

    /*
    * Gets the current Syrup position
    */
    public SyrupPosition getPosition() {
        return this.pos;
    }
}