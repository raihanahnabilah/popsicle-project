package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.popsicle.R;

/**
 * This is the class for Candy
 */
public class Candy {
    private static final String TAG = "Candies";
    Bitmap popsicle;
    int width, height, screenX, screenY;
    private Position pos;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Candy(String direction){
        this.screenX = Constants.screenX;
        this.screenY = Constants.screenY;

        if (direction == "a"){
//            change this position of popsicle a
            this.pos = new Position(screenX/16, (screenY *45)/100);
        } else if (direction == "b"){
//            change this position of popsicle b
            this.pos = new Position((screenX * 14)/16, (screenY *45)/100);
        }

        this.width = Constants.popsicleWidth;
        this.height = Constants.popsicleHeight;

    }

    public Bitmap getPopsicle() {
        return popsicle;
    }


    public Rect getCollisionShape(){
        int left = (int) this.getPos().getX();
        int top = (int) this.getPos().getY();
        int right = (int) this.getPos().getX() + this.getWidth();
        int bottom = (int) this.getPos().getY() + this.getHeight();

        return new Rect(left, top, right, bottom);
    }

    /**
     *
     * @return pos Position of the Candy based on the CandyPosition class
     */
    public Position getPos() {
        return pos;
    }
}
