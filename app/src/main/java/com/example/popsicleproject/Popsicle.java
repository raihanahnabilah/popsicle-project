package com.example.popsicleproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Popsicle {

    int x, y, width, height;
    Bitmap popsicle;

    public Popsicle(int screenX, int screenY, Resources res, int id, String direction){
        popsicle = BitmapFactory.decodeResource(res, id);

        width = popsicle.getWidth();
        height = popsicle.getHeight();

        width /= 15;
        height /= 15;

        popsicle = Bitmap.createScaledBitmap(popsicle, width, height, false);

        if (direction == "a"){
            y = (screenY /2) - 100;
            x = 100;
        } else if (direction == "b"){
            y = (screenY / 2) - 100;
            x = screenX - 280;
        }


    }

    Bitmap getPopsicle(){
        return popsicle;
    }

    public Rect getCollissionShape() {
        return new Rect(x+100, y, x+width-100, y+height);
    }
}
