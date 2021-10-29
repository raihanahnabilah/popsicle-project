package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.popsicle.R;

public class Background {
    private Bitmap background;
    int width, height;


    public Background(int screenX, int screenY, Resources res){
        background = BitmapFactory.decodeResource(res, R.mipmap.bg);

        width = background.getWidth();
        height = background.getHeight();

        background = Bitmap.createScaledBitmap(background, width, height, false);

    }

    public Bitmap getBackground() {
        return background;
    }
}
