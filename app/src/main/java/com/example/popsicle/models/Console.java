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

    public Console(int screenX, int screenY, String direction, Resources res){
        if (direction == "up"){
            this.pos = new Position(screenX - 350, (screenY/2) + 140);
            console = BitmapFactory.decodeResource(res, R.mipmap.up);
        } else if (direction == "down"){
            this.pos = new Position(screenX-350, (screenY/2) + 350);
            console = BitmapFactory.decodeResource(res, R.mipmap.down);
        } else if (direction == "left"){
            this.pos = new Position(screenX - 460, (screenY/2) + 250);
            console = BitmapFactory.decodeResource(res, R.mipmap.left);
        } else if (direction == "right"){
            this.pos = new Position(screenX - 240, (screenY/2) + 250);
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
