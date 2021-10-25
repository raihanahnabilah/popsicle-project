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

    public Cloud(GraphicsRenderer graphicsRenderer, int screenX, int screenY, Resources res, int id, String direction){
        this.direction = direction;
        this.graphicsRenderer = graphicsRenderer;
        cloud = BitmapFactory.decodeResource(res, id);

        width = cloud.getWidth();
        height = cloud.getHeight();

        width /= 20;
        height /= 20;

        cloud = Bitmap.createScaledBitmap(cloud, width, height, false);

        if (direction == "a1"){
            x = 550;
            y = (screenY /2) - 500;
        } else if (direction == "a2"){
            x = 250;
            y = (screenY /2) + 200;
        } else if (direction == "b1"){
            x = screenX - 450;
            y = (screenY /2) - 500;
        } else if (direction == "b2"){
            x = screenX - 850;
            y = (screenY /2) + 200;
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
