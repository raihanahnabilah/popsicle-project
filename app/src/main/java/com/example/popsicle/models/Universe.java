package com.example.popsicle.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 *  Universe handles all the actions that are going on in the game.
 *  It also creates the "Universe" by creating the elements/models in the game,
 *  such as the Popsicles/Candies, Characters, Clouds, Console, and the Syrups.
 *  @author Hana, Valeria, James, Sangho
 */
public class Universe {

    public static final String TAG = "Universe";
    /**
     * The QueueX to store the x-coordinates of the Characters Position
     */
    Queue<Integer> queueX = new LinkedList<>();

    /**
     * The QueueY to store the y-coordinates of the Characters Position
     */
    Queue<Integer> queueY = new LinkedList<>();
    /**
     * Characters class in the universe
     */
    private final Character characterA, characterB;

    /**
     * Clouds class in the universe
     */
    private final Clouds cloudA1, cloudA2, cloudB1, cloudB2;

    /**
     * Screen width (x) and height (y) of the emulator
     */
    private int screenX, screenY;

    /**
     * Popsicles class in the universe
     */
    private final Candy popsicleA, popsicleB;

    /**
     * Consoles class in the universe
     */
    private final Console up, down, left, right;

    /**
     * List of Syrups created in the universe
     */
    private List<Syrup> syrups;

    /**
     * Boolean isPlaying and isGameOver to either terminate or continue the game
     */
    Boolean isPlaying = true, isGameOver = false, isGameWon = false;

    /**
     * Constants class is the important constants for the elements, such as
     * width-height of the elements, screen size, etc
     */
    Constants constants;

    /**
     * Firebase data reference for our Firebase
     */
    DatabaseReference mRootRef = FirebaseDatabase.getInstance("https://popsicle-game-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

    /**
     * The Universe constructor will create the Characters, Clouds,
     * Popsicles, Console, and Syrups in the Universe.
     */
    public Universe(){
        this.constants = new Constants();
        this.screenX = constants.screenX;
        this.screenY = constants.screenY;
        this.characterA = new Character("a");
        this.characterB = new Character("b");
        this.cloudA1 = new Clouds("a1");
        this.cloudA2 = new Clouds("a2");
        this.cloudB1 = new Clouds("b1");
        this.cloudB2 = new Clouds("b2");
        this.popsicleA = new Candy("a");
        this.popsicleB = new Candy("b");
        this.right = new Console("right");
        this.up = new Console("up");
        this.down = new Console("down");
        this.left = new Console("left");
        this.syrups = new Vector<>();

    }

    /**
     * Getter function to get the constants, such as the width and height of the elements,
     * the screen size of the emulator, the pixel movement of the syrup and
     * character, from the Constants class
     * @return the constants class
     */
    public Constants getConstants() {
        return constants;
    }

    /**
     * Getter function to get character A class
     * @return Character A class
     */
    public Character getCharacterA() {
        return characterA;
    }

    /**
     * Getter function to get character B class
     * @return Character B class
     */
    public Character getCharacterB() {
        return characterB;
    }

    /**
     * Getter function to get cloud A1 class
     * @return Cloud A1 class
     */
    public Clouds getCloudA1() {
        return cloudA1;
    }

    /**
     * Getter function to get cloud A2 class
     * @return Cloud A2 class
     */
    public Clouds getCloudA2() {
        return cloudA2;
    }

    /**
     * Getter function to get cloud B1 class
     * @return Cloud B1 class
     */
    public Clouds getCloudB1() {
        return cloudB1;
    }

    /**
     * Getter function to get cloud B2 class
     * @return Cloud B2 class
     */
    public Clouds getCloudB2() {
        return cloudB2;
    }

    /**
     * Getter function to get popsicle A class
     * @return Popsicle A class
     */
    public Candy getPopsicleA() {
        return popsicleA;
    }

    /**
     * Getter function to get popsicle B class
     * @return Popsicle B class
     */
    public Candy getPopsicleB() {
        return popsicleB;
    }

    /**
     * Getter function to get console up class
     * @return Console Up class
     */
    public Console getUp() {
        return up;
    }

    /**
     * Getter function to get console down class
     * @return Console Down class
     */
    public Console getDown() {
        return down;
    }

    /**
     * Getter function to get console left class
     * @return Console Left class
     */
    public Console getLeft() {
        return left;
    }

    /**
     * Getter function to get console right class
     * @return Console Right class
     */
    public Console getRight() {
        return right;
    }

    /**
     * Getter function to get the Collection of Syrups
     * @return Syrups inside the Collection
     */
    public Collection<Syrup> getSyrups() {
        return syrups;
    }


    /**
     * The CharacterMove function is called in the MoveCharacterAction class, that will be executed
     * when the user touches the screen. This function will check whether the user touches the screen
     * on the coordinates/location of the Up, Left, Down, or Right console button. It will then set
     * the Boolean isMovingUp/Left/Down/Right to true depending on the Console that is clicked.
     * @param pos position of the Console in the Universe (in this case, the user's touch on screen)
     * @param character the Character class that we want to move (character A or B)
     */
    public void CharacterMove(Position pos, Character character){
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

    /**
     * The addSyrup method is to add the Syrup into the game or the Universe.
     * The syrup will be automatically shot by the Clouds A1, A2, B1, or B2 when the game
     * is started. This method is called in the MainController when the Thread is being run.
     * The syrups are added to the universe at a random time and at a random direction every time.
     * This serves as an obstacle in the game.
     * @param x the x-coordinate of the Syrup position
     * @param y the y-coordinate of the Syrup position
     * @param direction from which Cloud is the Syrup coming from (either "a1", "a2", "b1", or "b2")
     */
    public void addSyrup(float x, float y, String direction){
        Syrup syrup = new Syrup(direction);
        syrup.setPos(new Position(x, y));
        syrups.add(syrup);
        castChanges();
    }

    /**
     * The syrupSteps method is to make all the Syrups in the Universe move
     * in a certain direction, depending on from which Clouds is the Syrups being shot from
     */
    public void syrupSteps(){
        for (Syrup syrup: syrups){
            syrup.syrupMove(new Position(syrup.getMovex(),syrup.getMovey()));
        }
        castChanges();
    }

    /**
     * The checkSyrupCollisionA method is to check if any of the Syrups in
     * the Universe collides with either Character A. If it
     * collides with the Character, then the game will be terminated with Character B winning
     * and Character A loosing.
     */
    public void checkSyrupCollisionA(){
        for (Syrup syrup: syrups){
            if (Rect.intersects(characterA.getCollisionShape(), syrup.getCollisionShape()) && characterA.getLivesCounter() > 0 ){
                characterA.decrementLives();
            }

            else if (characterA.getLivesCounter() <= 0){
                this.setGameOver(true);
                mRootRef.child("isPlayerADead").setValue(true);
                mRootRef.child("CharA_Position_X").setValue(screenX/8);
                mRootRef.child("CharA_Position_Y").setValue((screenY*45)/100);
                mRootRef.child("CharB_Position_X").setValue(screenX*13/16);
                mRootRef.child("CharB_Position_Y").setValue((screenY*45)/100);
                return;
            }
        }
        castChanges();
    }

    /**
     * The checkSyrupCollisionB method is to check if any of the Syrups in
     * the Universe collides with either Character B. If it
     * collides with the Character, then the game will be terminated with Character A winning
     * and Character B loosing.
     */
    public void checkSyrupCollisionB(){
        for (Syrup syrup: syrups){
            if (Rect.intersects(characterB.getCollisionShape(), syrup.getCollisionShape()) && characterB.getLivesCounter() > 0 ){
                characterB.decrementLives();
            }
            if (Rect.intersects(characterB.getCollisionShape(), syrup.getCollisionShape())){
                this.setGameOver(true);
                mRootRef.child("isPlayerBDead").setValue(true);
                mRootRef.child("CharA_Position_X").setValue(screenX/8);
                mRootRef.child("CharA_Position_Y").setValue((screenY*45)/100);
                mRootRef.child("CharB_Position_X").setValue(screenX*13/16);
                mRootRef.child("CharB_Position_Y").setValue((screenY*45)/100);
                return;
            }
        }
        castChanges();
    }

    /**
     * The checkPopsicleACollision method is to check if Character A is in close enough proximity to
     * the popsicle of Character B. If it is the game will be terminated with Character A winning
     * and Character B loosing.
     */
    public void checkPopsicleACollision(){
        if (Rect.intersects(characterA.getCollisionShape(), popsicleB.getCollisionShape())){
            mRootRef.child("isPlayerAWon").setValue(true);
            this.setGameWon(true);
            mRootRef.child("CharA_Position_X").setValue(screenX/8);
            mRootRef.child("CharA_Position_Y").setValue((screenY*45)/100);
            return;
        }
    }

    /**
     * The checkPopsicleBCollision method is to check if Character B is in close enough proximity to
     * the popsicle of Character A. If it is the game will be terminated with Character B winning
     * and Character A loosing.
     */
    public void checkPopsicleBCollision(){
        if (Rect.intersects(characterB.getCollisionShape(), popsicleA.getCollisionShape())){
            mRootRef.child("isPlayerBWon").setValue(true);
            this.setGameWon(true);
            mRootRef.child("CharB_Position_X").setValue(screenX*13/16);
            mRootRef.child("CharB_Position_Y").setValue((screenY*45)/100);
            return;
        }
    }
    /**
     * This method is to add syrups in the universe at random direction. This
     * method is called at the main controller.
     * @param n The random integer chosen to generate a random direction of the syrup
     * @param cloud The cloud in which the syrup is shooting from
     * @param direction The specified cloud (either "a1", "a2", "b1", or "b2")
     */
    public void randomlyAddSyrups(int n, Clouds cloud, String direction){
        if (n==0){
            addSyrup(cloud.getPos().getX(), cloud.getPos().getY() + cloud.height/2, direction);
        }
        else if (n==1){
            addSyrup(cloud.getPos().getX() + cloud.width, cloud.getPos().getY() + cloud.height/2, direction);

        }
        else if (n==2){
            addSyrup(cloud.getPos().getX() + cloud.width/2, cloud.getPos().getY() + cloud.height/4, direction);
        }
        else if (n==3){
            addSyrup(cloud.getPos().getX() + cloud.width/2, cloud.getPos().getY() + (cloud.height*3)/4, direction);
        }
    }

    /**
     * The updateCharacter method is to move the Character either Up, Left, Down, or Right
     * depending on which Boolean is set to true after the user clicks on either the
     * Up, Left, Down, or Right Console.
     */
    public void updateCharacter(){

        if (getCharacterA().getMovingRight()){
            System.out.println("Move Right");
            characterA.moveRight();
            castChanges();
            characterA.setMovingRight(false);
        }

        if (getCharacterA().getMovingLeft()){
            System.out.println("Move Left");
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

        if (getCharacterB().getMovingRight()){
            characterB.moveRight();
            castChanges();
            characterB.setMovingRight(false);
        }

        if (getCharacterB().getMovingLeft()){
            characterB.moveLeft();
            castChanges();
            characterB.setMovingLeft(false);
        }

        if (getCharacterB().getMovingUp()){
            characterB.moveUp();
            castChanges();
            characterB.setMovingUp(false);
        }

        if (getCharacterB().getMovingDown()){
            characterB.moveDown();
            castChanges();
            characterB.setMovingDown(false);
        }


    }

    /**
     * The universeToFirebaseA method writes the position of Character A to the firebase to be read by Character B
     */
    public void universeToFirebaseA() {
        mRootRef.child("CharA_Position_X").setValue(this.characterA.getPos().getX());
        mRootRef.child("CharA_Position_Y").setValue(this.characterA.getPos().getY());
    }

    /**
     * The universeToFirebaseB method writes the position of Character B to the firebase to be read by Character A
     */
    public void universeToFirebaseB() {
        mRootRef.child("CharB_Position_X").setValue(this.characterB.getPos().getX());
        mRootRef.child("CharB_Position_Y").setValue(this.characterB.getPos().getY());
    }

    /**
     * The readCharacter sets the position of a given character
     * @param x x coordinate of the position of the character
     * @param y y coordinate of the position of the character
     * @param character character whose position is being given to be set
     */
    public void readCharacter(int x, int y, Character character){
        character.setPos(new Position(x, y));
    }

    /**
     * The readStatusAFromB method reads if Character A is dead or has won the game,
     * so Character B wins or looses accordingly.
     */
    public void readStatusAFromB(){
        ValueEventListener playerADead = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean playerAStat = dataSnapshot.getValue(Boolean.class);
                isGameWon = playerAStat;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("isPlayerADead").addListenerForSingleValueEvent(playerADead);
        ValueEventListener playerAWon = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean playerAWonStat = dataSnapshot.getValue(Boolean.class);
                isGameOver = playerAWonStat;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("isPlayerAWon").addListenerForSingleValueEvent(playerAWon);
    }

    /**
     * The readStatusBFromA method reads if Character B is dead or has won the game,
     * so Character A wins or looses accordingly.
     */
    public void readStatusBFromA(){
        ValueEventListener playerBDead = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean playerBStat = dataSnapshot.getValue(Boolean.class);
                isGameWon = playerBStat;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("isPlayerBDead").addListenerForSingleValueEvent(playerBDead);
        ValueEventListener playerBWon = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean playerBWonStat = dataSnapshot.getValue(Boolean.class);
                isGameOver = playerBWonStat;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("isPlayerBWon").addListenerForSingleValueEvent(playerBWon);
    }


    /**
     * The readCharacterBFromCharacterA method reads the position of Character B from the database,
     * so the screen of the user using Character A can render it accordingly.
     */
    public void readCharacterBFromCharacterA(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer pos = dataSnapshot.getValue(Integer.class);
                queueX.add(pos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("CharB_Position_X").addListenerForSingleValueEvent(postListener);

        ValueEventListener yListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer pos = dataSnapshot.getValue(Integer.class);
                queueY.add(pos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("CharB_Position_Y").addListenerForSingleValueEvent(yListener);
    }

    /**
     * The readCharacterAFromCharacterB method reads the position of Character A from the database,
     * so the screen of the user using Character B can render it accordingly.
     */
    public void readCharacterAFromCharacterB(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer pos = dataSnapshot.getValue(Integer.class);
                queueX.add(pos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("CharA_Position_X").addListenerForSingleValueEvent(postListener);

        ValueEventListener yListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer pos = dataSnapshot.getValue(Integer.class);
                queueY.add(pos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("CharA_Position_Y").addListenerForSingleValueEvent(yListener);
    }

    /**
     * Getter function to get the current state of the isPlaying Boolean.
     * isPlaying Boolean is used as a running condition on the MainController thread.
     * @return isPlaying boolean
     */
    public Boolean getPlaying() {
        return isPlaying;
    }

    /**
     * Setter function to set the current state of the isPlaying Boolean.
     * If we want to terminate the game, the isPlaying boolean will be set
     * to False by using this function
     * @param playing the set isPlaying boolean
     */
    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    /**
     * Getter function to get the current state of the isGameOver Boolean.
     * @return isGameOver boolean
     */
    public Boolean getGameOver() {
        return isGameOver;
    }

    /**
     * Setter function to set the current state of the isGameOver Boolean.
     * If we want to terminate the game, the isGameOver boolean will be set
     * to True by using this function
     * @param gameOver the set isGameOver boolean
     */
    public void setGameOver(Boolean gameOver) {
        isGameOver = gameOver;
    }

    public Queue<Integer> getQueueX() {
        return queueX;
    }

    public Queue<Integer> getQueueY() {
        return queueY;
    }

    public Boolean getGameWon() {
        return isGameWon;
    }

    public void setGameWon(Boolean gameWon) {
        isGameWon = gameWon;
    }

    /**
     * Interface Callback is triggered when the universe changes.
     * Every time we add an element or make the element move in the methods
     * in the Universe, it will call castChanges() method in the Callback which
     * essentially will change the current state of the Universe
     * and send that data to the GraphicsRenderer to render the new view
     */
    public interface Callback {
        void universeChanged ( Universe u ) ;
    }

    /**
     * setCallback is to send the data to the GraphicsRenderer to render the new view
     * every time changes are made to the Universe.
     * @param c the Callback interface from other modules/Class, like GraphicsRenderer
     */
    public void setCallBack(Callback c) {
        callback = c;
    }

    /**
     * addCallback is to add a callback to the Universe
     * @param c the Callback interface
     */
    public void addCallBack(Callback c) {
        this.callback = c;
    }

    /**
     * castChanges method will call the Callback interface universeChanges
     * to change the current state of the Universe every time changes are
     * made in the Universe.
     */
    protected void castChanges() {
        if (callback != null) {
            callback.universeChanged(this);
        }
//        else {
//            Log.w(TAG, "Callback is not available.");
//        }
    }

    /**
     * The Callback state
     */
    private Callback callback = null;

}
