package com.example.popsicleproject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Character {

    Boolean isMovingUp = false, isMovingDown = false, isMovingLeft = false, isMovingRight = false;
    int x, y, width, height;
    Bitmap character;

    Character (int screenX, int screenY, Resources res, int id, String direction){
        character = BitmapFactory.decodeResource(res, id);

        width = character.getWidth();
        height = character.getHeight();

        width /= 4;
        height /= 4;

        character = Bitmap.createScaledBitmap(character, width, height, false);

        if (direction == "a"){
            y = (screenY /2) - 100;
            x = 250;
        } else if (direction == "b"){
            y = (screenY / 2) - 100;
            x = screenX - 450;
        }


    }

    Bitmap getCharacter(){
        return character;
    }

    public Rect getCollissionShape() {
        return new Rect(x-100, y+50, x+width-100, y+height-80);
    }
}
