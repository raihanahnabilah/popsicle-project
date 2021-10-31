package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.popsicle.R;

public class Clouds {
    private static final String TAG = "Clouds";
    private Position pos;
    public int width, height, screenX, screenY;
    Bitmap clouds;
    String direction;
    private Universe universe;

    public Clouds(String direction){
        this.direction = direction;
        Constants constants = new Constants();
        this.screenX = constants.screenX;
        this.screenY = constants.screenY;

        this.width = Constants.cloudWidth;
        this.height = Constants.cloudHeight;

        //this is the initial position of the clouds
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

    public Bitmap getClouds(){
        return clouds;
    }

    public Position getPos() {
        return pos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
