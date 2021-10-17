package com.example.popsicle.models;

import android.util.Log;
import android.view.SurfaceView;


import com.example.popsicle.rendering.GraphicsRenderer;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class Universe {

    public static final String TAG = "Universe";
    private final Character characterA;
    private final Character characterB;
    private final Candy candyA;
    private final Candy candyB;
    private final Clouds cloudsA1;
    private final Clouds cloudsA2;
    private final Clouds cloudsB1;
    private final Clouds cloudsB2;
    private final Console upButton;
    private final Console downButton;
    private final Console leftButton;
    private final Console rightButton;
    List<Syrup> syrupA1;
    List<Syrup> syrupB1;
    List<Syrup> syrupA2;
    List<Syrup> syrupB2;

    public Universe(){
        this.characterA = new Character(350,280);
        this.candyA = new Candy(200,280);
        this.cloudsA1 = new Clouds(500,-50);
        this.cloudsA2 = new Clouds(200,650);

        this.characterB = new Character(1700,280);
        this.candyB = new Candy(1850,280);
        this.cloudsB1 = new Clouds(1850,-50);
        this.cloudsB2 = new Clouds(1500,650);

        this.upButton = new Console(1850,550);
        this.downButton = new Console(1850,750);
        this.leftButton = new Console(1730,650);
        this.rightButton = new Console(1970,650);
//        data = new Vector<>();
    }

    public Character getCharacterA() {
        return characterA;
    }

    public Character getCharacterB() {
        return characterB;
    }

    public Candy getCandyA() {
        return candyA;
    }

    public Candy getCandyB() {
        return candyB;
    }

    public Clouds getCloudsA1() {
        return cloudsA1;
    }

    public Clouds getCloudsA2() {
        return cloudsA2;
    }

    public Clouds getCloudsB1() {
        return cloudsB1;
    }

    public Clouds getCloudsB2() {
        return cloudsB2;
    }


    public void CharacterMove (CharacterPosition pos, Character character){
        System.out.println(pos);
        if (pos.getY() >= 730 && pos.getY() <= 855){
            if (pos.getX() >= 1900 && pos.getX() <= 2040){
                character.moveRight();
                castChanges();
            } else if (pos.getX() >= 1660 && pos.getX() <= 1800){
                character.moveLeft();
                castChanges();
            }
        } else if (pos.getX() >= 1780 && pos.getX() <= 1920){
            if (pos.getY() >= 645 && pos.getY() <= 730){
                character.moveUp();
                castChanges();
            } else if (pos.getY() >= 860 && pos.getY() <= 940){
                character.moveDown();
                castChanges();
            }
        }
    }

    public void AStepLeft() {
        characterA.moveLeft();
    }

    public void AStepRight() {
        characterA.moveRight();
    }

    public void AStepUp() {
        characterA.moveUp();
    }

    public void AStepDown() {
        characterA.moveDown();
    }


    public void BStepLeft() {
        characterB.moveLeft();
    }

    public void BStepRight() {
        characterB.moveRight();
    }

    public void BStepUp() {
        characterB.moveUp();
    }

    public void BStepDown() {
        characterB.moveDown();
    }

    @Override
    public String toString() {
        return "Universe{" +
                "characterA=" + characterA +
                ", characterB=" + characterB +
                ", candyA=" + candyA +
                ", candyB=" + candyB +
                ", cloudsA1=" + cloudsA1 +
                ", cloudsA2=" + cloudsA2 +
                ", cloudsB1=" + cloudsB1 +
                ", cloudsB2=" + cloudsB2 +
                ", callback=" + callback +
                '}';
    }

    public Console getUpButton() {
        return upButton;
    }

    public Console getDownButton() {
        return downButton;
    }

    public Console getLeftButton() {
        return leftButton;
    }

    public Console getRightButton() {
        return rightButton;
    }


    /**
     * Callback when the universe changes.
     * These functions here are sort-of a given.
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
