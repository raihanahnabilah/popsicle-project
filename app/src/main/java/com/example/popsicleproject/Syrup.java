package com.example.popsicleproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Syrup {

    int x, y, width, height;
    Bitmap syrup;

    Syrup (Resources res){
        syrup = BitmapFactory.decodeResource(res, R.mipmap.syrup);

        width = syrup.getWidth();
        height = syrup.getHeight();

        width /= 20;
        height /= 20;

        syrup = Bitmap.createScaledBitmap(syrup, width, height, false);

    }

    public Rect getCollissionShape() {
        return new Rect(x, y, x+width, y+height);
    }
}
