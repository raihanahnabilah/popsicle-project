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

    public Console(int screenX, int screenY, String direction, Resources res, float refX, float refY, float refWidth, int refHeight){
        if (direction == "up"){
            this.pos = new Position(refX - refWidth, refY-refHeight);
            console = BitmapFactory.decodeResource(res, R.mipmap.up);
        } else if (direction == "down"){
            this.pos = new Position(refX - refWidth, refY+refHeight);
            console = BitmapFactory.decodeResource(res, R.mipmap.down);
        } else if (direction == "left"){
            this.pos = new Position(refX - refWidth, refY-refHeight);
            console = BitmapFactory.decodeResource(res, R.mipmap.left);
        } else if (direction == "right"){
            this.pos = new Position((screenX * 28 )/32, (screenY*3)/4);
            console = BitmapFactory.decodeResource(res, R.mipmap.right);
        }

        width = console.getWidth();
        height = console.getHeight();

        width /= 10;
        height/= 10;

        console = Bitmap.createScaledBitmap(console, width, height, false);
    }

    public Bitmap getConsole(){
        return console;
    }

    public Position getPos() {
        return pos;
    }
}
