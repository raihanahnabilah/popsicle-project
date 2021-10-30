package com.example.popsicle.models;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceView;


import com.example.popsicle.MainController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 *  Universe handles all the actions that are going on in our game.
 *
 *  Notes on connection:
 *  whoever starts the game -- will get the left character
 *  the other who joins -- will get the right characgter
 *  convert a name of a player to a number -- can hash it
 *  whoever is larger, will get the left character
 *  whoever is smaller, will get the right character
 */

public class Universe {

    public static final String TAG = "Universe";
    private final Character characterA, characterB;
    private final Clouds cloudA1, cloudA2, cloudB1, cloudB2;
    private final int screenX, screenY;
    private final SurfaceView sv;
    private final Candy popsicleA, popsicleB;
    private final Console up, down, left, right;
    private List<Syrup> syrups;

//    ADDED THIS TO Constants (not in use)
//    private int toShoot = 0;
//    private int toShootA2 = 0;
    private MainController mc;

    /**
     * Universe instantiates all the different
     * Comment:
     * - a lot of constants:
     * --> should have a datastructure that loads all these constants
     * --> shouldn't even have to have them as constants
     * --> constants within the creation of the universe
     *         can have another class "UniverseBuilder" that will provide these constants
     */
    public Universe(int screenX, int screenY, SurfaceView sv, MainController mc){
        this.screenX = screenX;
        this.screenY = screenY;
        this.sv = sv;
        this.mc = mc;
        this.characterA = new Character(screenX, screenY, "a", sv.getResources());
        this.characterB = new Character(screenX, screenY, "b", sv.getResources());
        this.cloudA1 = new Clouds(this, screenX, screenY, "a1", sv.getResources());
        this.cloudA2 = new Clouds(this, screenX, screenY, "a2", sv.getResources());
        this.cloudB1 = new Clouds(this, screenX, screenY, "b1", sv.getResources());
        this.cloudB2 = new Clouds(this, screenX, screenY, "b2", sv.getResources());
        this.popsicleA = new Candy(screenX, screenY, "a", sv.getResources());
        this.popsicleB = new Candy(screenX, screenY, "b", sv.getResources());
        this.right = new Console(screenX,screenY,"right", sv.getResources(), 0, 0,0,0);
        this.up = new Console(screenX,screenY,"up", sv.getResources(), this.getRight().getPos().getX(), this.getRight().getPos().getY(), this.getRight().width,this.getRight().height);
        this.down = new Console(screenX,screenY,"down", sv.getResources(), this.getRight().getPos().getX(), this.getRight().getPos().getY(), this.getRight().width,this.getRight().height);
        this.left = new Console(screenX,screenY,"left", sv.getResources(), this.getDown().getPos().getX(), this.getDown().getPos().getY(), this.getDown().width,this.getDown().height);
        this.syrups = new Vector<>();

    }

    public Character getCharacterA() {
        return characterA;
    }

    public Character getCharacterB() {
        return characterB;
    }

    public Clouds getCloudA1() {
        return cloudA1;
    }

    public Clouds getCloudA2() {
        return cloudA2;
    }

    public Clouds getCloudB1() {
        return cloudB1;
    }

    public Clouds getCloudB2() {
        return cloudB2;
    }

    public Candy getPopsicleA() {
        return popsicleA;
    }

    public Candy getPopsicleB() {
        return popsicleB;
    }

    public Console getUp() {
        return up;
    }

    public Console getDown() {
        return down;
    }

    public Console getLeft() {
        return left;
    }

    public Console getRight() {
        return right;
    }

    public Collection<Syrup> getSyrups() {
        return syrups;
    }


    /**
     *
     * @param pos position of the Character
     * @param character character variable
     */

//    this is the console event optimisation
//    used to say Position pos but changes to Position event
    public void CharacterMove(Position pos, Character character){
        // In here, the characterPosition pos is actually the position of the button
        if ((pos.getX() > this.right.getPos().getX() + this.right.width/4) && (pos.getX() < (this.right.getPos().getX() + this.right.width - this.right.width/4)) &&
                (pos.getY() > this.right.getPos().getY() + this.right.height/4) && (pos.getY() < this.right.getPos().getY() + this.right.height - this.right.height/4)){
            character.setMovingRight(true);
        }
        if ((pos.getX() > this.left.getPos().getX() + this.left.width/4) && (pos.getX() < (this.left.getPos().getX() + this.left.width - this.left.width/4)) &&
                (pos.getY() > this.left.getPos().getY() + this.left.height/4) && (pos.getY() < this.left.getPos().getY() + this.left.height - this.left.height/4)){
            character.setMovingLeft(true);
        }
        if ((pos.getX() > this.up.getPos().getX() + this.up.width/4) && (pos.getX() < (this.up.getPos().getX() + this.up.width - this.up.width/4)) &&
                (pos.getY() > this.up.getPos().getY() + this.up.height/4) && (pos.getY() < this.up.getPos().getY() + this.up.height - this.up.height/4)){
            character.setMovingUp(true);
        }
        if ((pos.getX() > this.down.getPos().getX() + this.down.width/4) && (pos.getX() < (this.down.getPos().getX() + this.down.width - this.down.width/4)) &&
                (pos.getY() > this.down.getPos().getY() + down.height/4) && (pos.getY() < this.down.getPos().getY() + this.down.height - this.down.height/4)){
            character.setMovingDown(true);
        }
    }

//I HAVE ADDED screenX and Y as input for syrup
    public void addSyrup(float x, float y, String direction){
        Syrup syrup = new Syrup(sv.getResources(), direction, this.screenX, this.screenY);
        syrup.setPos(new Position(x, y));
        syrups.add(syrup);
        castChanges();
    }

    public void syrupSteps(){
        for (Syrup syrup: syrups){
            syrup.syrupMove(new Position(syrup.getMovex(),syrup.getMovey()));

            if (Rect.intersects(characterA.getCollisionShape(), syrup.getCollisionShape()) ||
                    Rect.intersects(characterB.getCollisionShape(), syrup.getCollisionShape())){
                mc.setGameOver(true);
                return;
            }
        }
        castChanges();
    }


    public void updateCharacter(){

        if (Rect.intersects(characterA.getCollisionShape(), popsicleB.getCollisionShape()) ||
                Rect.intersects(characterB.getCollisionShape(), popsicleA.getCollisionShape())){
             mc.setGameOver(true);
            return;
        }

        if (getCharacterA().getMovingRight()){
            characterA.moveRight();
            castChanges();
            characterA.setMovingRight(false);
        }

        if (getCharacterA().getMovingLeft()){
            characterA.moveLeft();
            castChanges();
            characterA.setMovingLeft(false);
        }

        if (getCharacterA().getMovingUp()){
            characterA.moveUp();
            castChanges();
            characterA.setMovingUp(false);
        }

        if (getCharacterA().getMovingDown()){
            characterA.moveDown();
            castChanges();
            characterA.setMovingDown(false);
        }


    }


    /**
     * Callback when the universe changes.
     * These functions here are given.
     * So don't touch this section below, please.
     */

    public interface Callback {
        void universeChanged ( Universe u ) ;
    }

    public void setCallBack(Callback c) {
        callback = c;
    }

    public void addCallBack(Callback c) {
        this.callback = c;
    }

    protected void castChanges() {
        if (callback != null) {
            callback.universeChanged(this);
        } else {
            Log.w(TAG, "Callback is not available.");
        }
    }

    private Callback callback = null;

}
