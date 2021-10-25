package com.example.popsicleproject.models;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;


import com.example.popsicleproject.Character;
import com.example.popsicleproject.Cloud;
import com.example.popsicleproject.MainActivity;
import com.example.popsicleproject.Popsicle;
import com.example.popsicleproject.R;
import com.example.popsicleproject.StartGamePage;
import com.example.popsicleproject.Syrup;



import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *  Universe handles all the actions that are going on in our game.
 *
 *  Notes on connection:
 *  whoever starts the game -- will get the left character
 *  the other who joins -- will get the right characgter
 *  convert a name of a player to a number -- can hash it
 *  whoever is larger, will get the left character
 *  whoever is smaller, will get the right character

 * Universe instantiates all the different
 * Comment:
 * - a lot of constants:
 * --> should have a datastructure that loads all these constants
 * --> shouldn't even have to have them as constants
 * --> constants within the creation of the universe
 *         can have another class "UniverseBuilder" that will provide these constants
 */


public class Universe {

//    Newer Code
    public static final String TAG = "Universe";

    private final int screenX, screenY;
    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private Character characterA, characterB;
    private com.example.popsicleproject.Console up, down, left, right;
    private Cloud cloudA1, cloudA2, cloudB1, cloudB2;
    private Popsicle popsicleA, popsicleB;
    private MainActivity activity;
    private List<Syrup> syrupsA1, syrupsA2, syrupsB1, syrupsB2;

//    Older Code
//    List<Syrup> syrups;
//    List<Syrup> trash;
//    boolean isSyrupActivated = false;

    public Universe(){

        super(activity);

        this.activity = activity;

        this.screenX = screenX;
        this.screenY = screenY;


        up = new com.example.popsicleproject.Console(screenX, screenY, getResources(), R.mipmap.up, "up");
        down = new com.example.popsicleproject.Console(screenX, screenY, getResources(), R.mipmap.down, "down");
        left = new com.example.popsicleproject.Console(screenX, screenY, getResources(), R.mipmap.left, "left");
        right = new com.example.popsicleproject.Console(screenX, screenY, getResources(), R.mipmap.right, "right");

        // For Character A
        characterA = new Character(screenX, screenY, getResources(), R.mipmap.char_a, "a");
        popsicleA = new Popsicle(screenX, screenY, getResources(), R.mipmap.popsicle_a, "a");
        cloudA1 = new Cloud(this,screenX, screenY, getResources(), R.mipmap.cloud, "a1");
        cloudA2 = new Cloud(this,screenX, screenY, getResources(), R.mipmap.cloud, "a2");
        syrupsA1 = new ArrayList<>();
        syrupsA2 = new ArrayList<>();

        // For Character B
        characterB = new Character(screenX, screenY, getResources(), R.mipmap.char_b, "b");
        popsicleB = new Popsicle(screenX, screenY, getResources(), R.mipmap.popsicle_b, "b");
        cloudB1= new Cloud(this,screenX, screenY, getResources(), R.mipmap.cloud, "b1");
        cloudB2 = new Cloud(this,screenX, screenY, getResources(), R.mipmap.cloud, "b2");
        syrupsB1 = new ArrayList<>();
        syrupsB2 = new ArrayList<>();

    }

//    /**
//     * Here we initialize all the variables that we need here:
//     * @return
//     */
//
//    public Character getCharacter() {
//        return characterA;
//    }
//
//    public Character getCharacterB() {
//        return characterB;
//    }
//
//    public Popsicle getPopsicleA() {
//        return popsicleA;
//    }
//
//    public Popsicle getPopsicleB() {
//        return popsicleB;
//    }
//
//    public Cloud getCloudA1() {
//        return cloudA1;
//    }
//
//    public Cloud getCloudA2() {
//        return cloudA2;
//    }
//
//    public Cloud getCloudB() {
//        return cloudB1;
//    }
//
//    public Cloud getCloudB2() {
//        return cloudB2;
//    }

    /**
     * This is the part where we move our variables:
     */
    private void update() {

        // This is only character A
        if (characterA.isMovingUp){
            characterA.y -= 20;
        }
        if (characterA.isMovingDown){
            characterA.y += 20;
        }
        if (characterA.isMovingRight){
            characterA.x += 20;
        }
        if (characterA.isMovingLeft){
            characterA.x -= 20;
        }

        // This is only character B
        if (characterB.isMovingUp){
            characterB.y -= 20;
        }
        if (characterB.isMovingDown){
            characterB.y += 20;
        }
        if (characterB.isMovingRight){
            characterB.x += 20;
        }
        if (characterB.isMovingLeft){
            characterB.x -= 20;
        }

        if (Rect.intersects(characterA.getCollissionShape(), popsicleB.getCollissionShape()) ||
                Rect.intersects(characterB.getCollissionShape(), popsicleA.getCollissionShape())){
            isGameOver = true;
            return;
        }

        List<Syrup> trashA1 = new ArrayList<>();

        for (Syrup syrup: syrupsA1){
            if (syrup.x > screenX){
                trashA1.add(syrup);
            }
            syrup.x += 20;
            syrup.y += 7f;

            if (Rect.intersects(characterA.getCollissionShape(), syrup.getCollissionShape()) ||
                    Rect.intersects(characterB.getCollissionShape(), syrup.getCollissionShape())){
                isGameOver = true;
                return;
            }
        }

        for (Syrup syrup: trashA1){
            syrupsA1.remove(syrup);
        }

        List<Syrup> trashA2 = new ArrayList<>();

        for (Syrup syrup: syrupsA2){
            if (syrup.x > screenX){
                trashA2.add(syrup);
            }
            syrup.x += 20;
            syrup.y -= 5f;

            if (Rect.intersects(characterA.getCollissionShape(), syrup.getCollissionShape()) ||
                    Rect.intersects(characterB.getCollissionShape(), syrup.getCollissionShape())){
                isGameOver = true;
                return;
            }
        }

        for (Syrup syrup: trashA2){
            syrupsA2.remove(syrup);
        }

        List<Syrup> trashB1 = new ArrayList<>();

        for (Syrup syrup: syrupsB1){
            if (syrup.x > screenX){
                trashB1.add(syrup);
            }
            syrup.x -= 20;
            syrup.y += 5f;

            if (Rect.intersects(characterA.getCollissionShape(), syrup.getCollissionShape()) ||
                    Rect.intersects(characterB.getCollissionShape(), syrup.getCollissionShape())){
                isGameOver = true;
                return;
            }
        }

        for (Syrup syrup: trashB1){
            syrupsB1.remove(syrup);
        }


        List<Syrup> trashB2 = new ArrayList<>();

        for (Syrup syrup: syrupsB2){
            if (syrup.x > screenX){
                trashB2.add(syrup);
            }
            syrup.x -= 20;
            syrup.y -= 7f;

            if (Rect.intersects(characterA.getCollissionShape(), syrup.getCollissionShape()) ||
                    Rect.intersects(characterB.getCollissionShape(), syrup.getCollissionShape())){
                isGameOver = true;
                return;
            }
        }

        for (Syrup syrup: trashB2){
            syrupsB2.remove(syrup);
        }

    }


    /**
     *
     * @param pos position of the Character
     * @param character character variable
     */
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
                ", popsicleA=" + popsicleA +
                ", popsicleB=" + popsicleB +
                ", cloudsA1=" + cloudA1 +
                ", cloudsA2=" + cloudA2 +
                ", cloudsB1=" + cloudB1 +
                ", cloudsB2=" + cloudB2 +
                // Adding in the syrup:



                ", callback=" + callback +
                '}';
    }


    private void waitBeforeExiting() {
        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, StartGamePage.class));
            activity.finish();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume () {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause () {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                System.out.println(event.getX());
                System.out.println(event.getY());
                // This is to move character A
                if ((event.getX() > 1740) && (event.getX() < 1880) &&
                        (event.getY() > 700) && (event.getY() < 790)){
                    characterA.isMovingUp = true;
                }
                if ((event.getX() > 1740) && (event.getX() < 1880) &&
                        (event.getY() > 935) && (event.getY() < 1020)){
                    characterA.isMovingDown = true;
                }
                if ((event.getX() > 1880) && (event.getX() < 1970) &&
                        (event.getY() > 800) && (event.getY() < 920)){
                    characterA.isMovingRight = true;
                }
                if ((event.getX() > 1650) && (event.getX() < 1740) &&
                        (event.getY() > 800) && (event.getY() < 920)){
                    characterA.isMovingLeft = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                characterA.isMovingUp = false;
                characterA.isMovingDown = false;
                characterA.isMovingRight = false;
                characterA.isMovingLeft = false;
                characterB.isMovingUp = false;
                characterB.isMovingDown = false;
                characterB.isMovingRight = false;
                characterB.isMovingLeft = false;


                // To activate the clouds
                if ((event.getX() > 570) && (event.getX() < 830) &&
                        (event.getY() > 111) && (event.getY() < 252)){
                    cloudA1.toShoot++;
                }
                if ((event.getX() > 270) && (event.getX() < 508) &&
                        (event.getY() > 830) && (event.getY() < 955)){
                    cloudA2.toShoot++;
                }
                if ((event.getX() > 1644) && (event.getX() < 1907) &&
                        (event.getY() > 111) && (event.getY() < 252)){
                    cloudB1.toShoot++;
                }
                if ((event.getX() > 1250) && (event.getX() < 1500) &&
                        (event.getY() > 810) && (event.getY() < 950)){
                    cloudB2.toShoot++;
                }
                break;

        }

        return true;
    }

    public void newSyrup(String direction){
        Syrup syrup = new Syrup(getResources());
        if (direction == "a1"){
            syrup.x = 810;
            syrup.y = 125;
            syrupsA1.add(syrup);
        }
        else if (direction == "a2"){
            syrup.x = 508;
            syrup.y = 830;
            syrupsA2.add(syrup);
        }
        else if (direction == "b1"){
            syrup.x = 1620;
            syrup.y = 120;
            syrupsB1.add(syrup);
        }
        else if (direction == "b2"){
            syrup.x = 1200;
            syrup.y = 850;
            syrupsB2.add(syrup);
        }
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