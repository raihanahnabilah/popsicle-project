package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.popsicle.R;

public class Console {
    private static final String TAG = "Console";
    Bitmap console;
    private Position pos;
    int width, height, screenY, screenX;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Console(String direction, float refX, float refY, float refWidth, int refHeight){
        this.screenX = Constants.screenX;
        this.screenY = Constants.screenY;

        if (direction.equals("up")){
            this.pos = new Position(refX - refWidth, refY-refHeight);
        } else if (direction.equals("down")){
            this.pos = new Position(refX - refWidth, refY+refHeight);
        } else if (direction.equals("left")){
            this.pos = new Position(refX - refWidth, refY-refHeight);
        } else if (direction.equals("right")){
            this.pos = new Position((screenX * 28 )/32, (screenY*3)/4);
        }

        width = Constants.consoleWidth;
        height = Constants.consoleHeight;
    }

    public Bitmap getConsole(){
        return console;
    }

    public Position getPos() {
        return pos;
    }
}
