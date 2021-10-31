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

//        if (direction.equals("up")){
//            this.pos = new Position(refX - refWidth, refY-refHeight);
//        } else if (direction.equals("down")){
//            this.pos = new Position(refX - refWidth, refY+refHeight);
//        } else if (direction.equals("left")){
//            this.pos = new Position(refX - refWidth, refY-refHeight);
//        } else if (direction.equals("right")){
//            this.pos = new Position((screenX * 28 )/32, (screenY*3)/4);
//        }
    }

    public Bitmap getConsole(){
        return console;
    }

    public Position getPos() {
        return pos;
    }
}
