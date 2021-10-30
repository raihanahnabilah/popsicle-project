package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.popsicle.R;

public class Console {
    private static final String TAG = "Console";
    Bitmap console;
    private Position pos;
    int width, height;

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

    public Console(int screenX, int screenY, String direction, float refX, float refY, float refWidth, int refHeight){
        if (direction == "up"){
            this.pos = new Position(refX - refWidth, refY-refHeight);
        } else if (direction == "down"){
            this.pos = new Position(refX - refWidth, refY+refHeight);
        } else if (direction == "left"){
            this.pos = new Position(refX - refWidth, refY-refHeight);
        } else if (direction == "right"){
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
