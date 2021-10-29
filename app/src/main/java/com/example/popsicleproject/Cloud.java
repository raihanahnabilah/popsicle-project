package com.example.popsicleproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Cloud {
    public int toShoot = 0;
    int x, y, width, height;
    Bitmap cloud;
    private GraphicsRenderer graphicsRenderer;
    String direction;

    Cloud (GraphicsRenderer graphicsRenderer, int screenX, int screenY, Resources res, int id, String direction){
        this.direction = direction;
        this.graphicsRenderer = graphicsRenderer;
        cloud = BitmapFactory.decodeResource(res, id);

        width = cloud.getWidth();
        height = cloud.getHeight();

        width /= 20;
        height /= 20;

//        this are the screens width and height
//        screen_width = MainActivity.window
        cloud = Bitmap.createScaledBitmap(cloud, width, height, false);

        if (direction == "a1"){
            x = screenX/4;
            y = (screenY /64);
        } else if (direction == "a2"){
            x = screenX/8;
            y = (screenY *7)/10;
        } else if (direction == "b1"){
            x = (screenX *3)/4;
            y = (screenY /60);
        } else if (direction == "b2"){
            x = screenX /2;
            y = (screenY *3)/4;
        }

    }

    Bitmap getCloud(){
        if (toShoot != 0){
            toShoot--;
            graphicsRenderer.newSyrup(direction);
        }
        return cloud;
    }

}
