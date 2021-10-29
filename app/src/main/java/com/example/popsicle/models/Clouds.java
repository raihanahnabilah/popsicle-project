package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.popsicle.R;

public class Clouds {
    private static final String TAG = "Clouds";
    private Position pos;
    int width, height, toShoot = 0;
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

        if (direction == "a1"){
            this.pos = new Position(550, (screenY/2)-500);
        } else if (direction == "a2"){
            this.pos = new Position(250, (screenY/2) + 200);
        } else if (direction == "b1"){
            this.pos = new Position(screenX - 450, (screenY/2) -500);
        } else if (direction == "b2"){
            this.pos = new Position(screenX - 850, (screenY/2) + 200);
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
