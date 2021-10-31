package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.popsicle.R;

/*
* Class representing what is 'shot' from the clouds to make the enemy character loose lives
 */
public class Syrup {
    private Position pos;
    String clouds;
    Bitmap syrup;
    int width, height, screenX, screenY;
    float moveX, moveY;
    String direction;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Syrup(String direction){
//        this.screenX = Constants.screenX;
//        this.screenY = Constants.screenY;
        Constants constants = new Constants();
        this.screenX = constants.screenX;
        this.screenY = constants.screenY;
//        System.out.println(this.screenX);

        width = Constants.syrupWidth;
        height = Constants.syrupHeight;

        if (direction == "a1"){
            moveX =  Constants.syrupMovementPixelsX;
            moveY = Constants.syrupMovementPixelsX*(screenX/screenY);
        }
        if (direction == "a2"){
            moveX =  Constants.syrupMovementPixelsX;
            moveY = -Constants.syrupMovementPixelsX*(screenX/screenY);
        }
        if (direction == "b1"){
            moveX =  -Constants.syrupMovementPixelsX;
            moveY = Constants.syrupMovementPixelsX*(screenX/screenY);
        }
        if (direction == "b2"){
            moveX =  -Constants.syrupMovementPixelsX;
            moveY = -Constants.syrupMovementPixelsX*(screenX/screenY);
        }

    }


    public Rect getCollisionShape(){
        int left = (int) this.getPos().getX()*33/32;
        int top = (int) this.getPos().getY()*33/32;
        int right = (int) (this.getPos().getX() + this.getWidth())*31/32;
        int bottom = (int) (this.getPos().getY() + this.getHeight())*61/64;

        return new Rect(left, top, right, bottom);
    }


    /*
    * Updates the location of the drop for it to move
    * @param m position coordinates
     */

    public void syrupMove(Position p) {
        this.pos.add(p);
    }

    /*
    * Gets the current Syrup position
    */
//    public Position getPosition() {
//        return this.pos;
//    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Bitmap getSyrup() {
        return syrup;
    }

    public float getMovex() {
        return moveX;
    }

    public float getMovey() {
        return moveY;
    }


}