package com.example.popsicle.models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.popsicle.R;

/**
 * Character Class is controls the positional information regarding the character
 */
public class Character {
    private static final String TAG = "Character";
    private Position pos;
    int width, height;
    Bitmap character;
    Boolean isMovingUp = false, isMovingDown = false, isMovingLeft = false, isMovingRight = false;

    public Boolean getMovingUp() {
        return isMovingUp;
    }

    public Boolean getMovingDown() {
        return isMovingDown;
    }

    public Boolean getMovingLeft() {
        return isMovingLeft;
    }

    public Boolean getMovingRight() {
        return isMovingRight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Character(int screenX, int screenY, String direction){
        if(direction.equals("a")){
            this.pos = new Position(screenX/8, (screenY *45)/100);
        } else if (direction == "b"){
            this.pos = new Position((screenX * 13)/16, (screenY *45)/100);
        }

        this.width = Constants.charWidth;
        this.height = Constants.charHeight;

    }

//    WHAT? HELP HERE
public Rect getCollisionShape(){
    int left = (int) (this.getPos().getX()*9)/8 ;
    int top = (int) (this.getPos().getY()*15)/16 ;
    int right = (int) (this.getPos().getX() + this.getWidth())*7/8;
    int bottom = (int) (this.getPos().getY() + this.getHeight())*15/16;
    return new Rect(left, top, right, bottom);
}


    public Bitmap getCharacter() {
        return character;
    }

    /**
     * Returns pos - The position of the Characters
     */
    public Position getPos() {
        return pos;
    }


    /**
     * Moving left: using the Character Position Class
     */
    public void moveLeft() {
            this.pos.addLeft();
    }
    /**
     * Moving Right: using the Character Position Class
     */
    public void moveRight() {
            this.pos.addRight();
    }
    /**
     * Moving Up: using the Character Position Class
     */
    public void moveUp() {
            this.pos.addUp();
    }
    /**
     * Moving Down: using the Character Position Class
     */
    public void moveDown() {
            this.pos.addDown();
    }


    /**
     * @return String to edit/override the xml file
     */

    @Override
    public String toString() {
        return "Character{" +
                "pos=" + pos +
                '}';
    }

    public void setMovingUp(Boolean movingUp) {
        isMovingUp = movingUp;
    }

    public void setMovingDown(Boolean movingDown) {
        isMovingDown = movingDown;
    }

    public void setMovingLeft(Boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    public void setMovingRight(Boolean movingRight) {
        isMovingRight = movingRight;
    }
}
