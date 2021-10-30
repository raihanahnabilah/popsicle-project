package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.popsicle.R;

public class Clouds {
    private static final String TAG = "Clouds";
    private Position pos;
    public int width, height, toShoot = 0;
    Bitmap clouds;
    String direction;
    private Universe universe;

    public Clouds(Universe universe, int screenX, int screenY, String direction, Resources res){
        this.universe = universe;
        this.direction = direction;

        clouds = BitmapFactory.decodeResource(res, R.mipmap.cloud);

        width = clouds.getWidth();
        height = clouds.getHeight();

        width /= 20;
        height/= 20;

        clouds = Bitmap.createScaledBitmap(clouds, width, height, false);
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
//        if (toShoot != 0){
//            toShoot--;
//            universe.newSyrup(direction);
//        }
        return clouds;
    }

    public Position getPos() {
        return pos;
    }
}
