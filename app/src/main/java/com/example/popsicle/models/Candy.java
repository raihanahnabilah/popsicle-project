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
    int width, height;
    private Position pos;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Candy(int screenX, int screenY, String direction, Resources res){
        popsicle = BitmapFactory.decodeResource(res, R.mipmap.cloud);
        if (direction == "a"){
//            change this position of popsicle a
            this.pos = new Position(screenX/16, (screenY *45)/100);
            popsicle = BitmapFactory.decodeResource(res, R.mipmap.popsicle_a);
        } else if (direction == "b"){
//            change this position of popsicle b
            this.pos = new Position((screenX * 14)/16, (screenY *45)/100);
            popsicle = BitmapFactory.decodeResource(res, R.mipmap.popsicle_b);
        }

        width = popsicle.getWidth();
        height = popsicle.getHeight();

        width /= 20;
        height/= 20;

        popsicle = Bitmap.createScaledBitmap(popsicle, width, height, false);

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
