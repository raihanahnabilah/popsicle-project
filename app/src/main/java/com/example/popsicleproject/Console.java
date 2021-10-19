package com.example.popsicleproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Console {
    int x, y, width, height;
    Bitmap console;

    Console (int screenX, int screenY, Resources res, int id, String direction){
        console = BitmapFactory.decodeResource(res, id);

        width = console.getWidth();
        height = console.getHeight();

        width /= 10;
        height /= 10;

        console = Bitmap.createScaledBitmap(console, width, height, false);

        if (direction == "up"){
            x = screenX - 350;
            y = screenY / 2 + 140;
        } else if (direction == "down"){
            x = screenX - 350;
            y = screenY / 2 + 360;
        } else if (direction == "left"){
            x = screenX - 460;
            y = screenY / 2 + 250;
        } else if (direction == "right"){
            x = screenX - 240;
            y = screenY / 2 + 250;
        }
    }

    Bitmap getConsole(){
        return console;
    }
}
