package com.example.popsicle.models;

import android.graphics.Point;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class Universe {
    // connection:
    // whoevr starts the game -- will get the left character
    // the other who joins -- will get the right characgter
    // convert a name of a player to a number -- can hash it
    // whoever is larger, will get the left character
    // whoever is smaller, will get the right character

    public static final String TAG = "Universe";
    private final Character characterA, characterB;
    private final Candy candyA, candyB;
    private final Clouds cloudsA1, cloudsA2, cloudsB1, cloudsB2;
    private final Console upButton, downButton, leftButton, rightButton;
    List<Syrup> syrups;
    List<Syrup> trash;
    boolean isSyrupActivated = false;

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

        this.syrups = new ArrayList<>();
        this.trash = new ArrayList<>();

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


    // Moving the characters using the console button (location of console in the universe)
    public void CharacterMove(CharacterPosition pos, Character character){
        // In here, the characterPosition pos is actually the position of the button
        if ((pos.getY() >= 730) && (pos.getY() <= 855)){
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


    // Moving the syrup using the clouds button (location of clouds in the universe)
    public void SyrupMove(CharacterPosition pos){
        // If cloud A1 is clicked
        if ((pos.getX() >= getCloudsA1().getPos().getX() - 100) && (pos.getX() <= getCloudsA1().getPos().getX() + 100)
                && (pos.getY() >= getCloudsA1().getPos().getY() + 150) && (pos.getY() <= getCloudsA1().getPos().getY() + 250)) {
            String cloud = "Cloud A1";
            GenerateSyrup(cloud);
        }
        // If cloud A2 is clicked
        else if ((pos.getX() >= getCloudsA2().getPos().getX() - 100) && (pos.getX() <= getCloudsA2().getPos().getX() + 100)
                && (pos.getY() >= getCloudsA2().getPos().getY() + 150) && (pos.getY() <= getCloudsA2().getPos().getY() + 250)){
            GenerateSyrup("Cloud A2");
        }
        else if ((pos.getX() >= getCloudsB1().getPos().getX() - 100) && (pos.getX() <= getCloudsB1().getPos().getX() + 100)
                && (pos.getY() >= getCloudsB1().getPos().getY() + 150) && (pos.getY() <= getCloudsB1().getPos().getY() + 250)){
            GenerateSyrup("Cloud B1");
        }
        else if ((pos.getX() >= getCloudsB2().getPos().getX() - 100) && (pos.getX() <= getCloudsB2().getPos().getX() + 100)
                && (pos.getY() >= getCloudsB2().getPos().getY() + 150) && (pos.getY() <= getCloudsB2().getPos().getY() + 250)){
            GenerateSyrup("Cloud B2");
        }
        isSyrupActivated = true;
        while (isSyrupActivated){
            syrup_move();
        }
    }

    public void GenerateSyrup(String clouds){
        if (clouds == "Cloud A1"){
            Syrup syrup = new Syrup(getCloudsA1().getPos().getX() + 100, getCloudsA1().getPos().getY() + 250);
            syrup.clouds = clouds;
            syrups.add(syrup);
            castChanges();
        } else if (clouds == "Cloud A2"){
            Syrup syrup = new Syrup(getCloudsA2().getPos().getX() + 100, getCloudsA2().getPos().getY() + 250);
            syrup.clouds = clouds;
            syrups.add(syrup);
            castChanges();
        } else if (clouds == "Cloud B1"){
            Syrup syrup = new Syrup(getCloudsB1().getPos().getX() - 100, getCloudsB1().getPos().getY() + 150);
            syrup.clouds = clouds;
            syrups.add(syrup);
            castChanges();
        } else if (clouds == "Cloud B2"){
            Syrup syrup = new Syrup(getCloudsB2().getPos().getX() - 100, getCloudsB2().getPos().getY() + 150);
            syrup.clouds = clouds;
            syrups.add(syrup);
            castChanges();
        }

    }

    public void syrup_move(){
        for (Syrup syrup: syrups){
            if (syrup.clouds == "Cloud A1"){
                syrup.move(new SyrupMotion(18, 5f));
            } else if (syrup.clouds == "Cloud A2"){
                syrup.move(new SyrupMotion(18, -5f));
            } else if (syrup.clouds == "Cloud B1"){
                syrup.move(new SyrupMotion(-18, 5f));
            } else if (syrup.clouds == "Cloud B2"){
                syrup.move(new SyrupMotion(-18, -5f));
            }
            float screenX1Limit = getRightButton().getPos().getX();
            float screenX2Limit = getCandyA().getPos().getX();
            // Removing the syrups once it's out of the thing
            // but this happens sequentially though.. I want it to happen concurrently
            if (syrup.getPosition().getX() >= screenX1Limit || syrup.getPosition().getX() <= screenX2Limit){
                isSyrupActivated = false;
                syrups.remove(syrup);
            }
        }
        castChanges();
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

    /***
     * Character A and B steps for left, right, up, down
     * for testing purposes
     */
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

    public List<Syrup> getSyrups() {
        return syrups;
    }

    public boolean isSyrupActivated() {
        return isSyrupActivated;
    }

    public void setSyrupActivated(boolean syrupActivated) {
        isSyrupActivated = syrupActivated;
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
