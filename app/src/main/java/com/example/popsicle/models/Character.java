package com.example.popsicle.models;

import android.graphics.Rect;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Character Class is to create the Characters in the Universe.
 * It stores several information of the Characters, such as its
 * Position in the universe, its Boolean on whether or not
 * the Character is moving Up, Down, Left, or Right,
 * and the width and height of the Character.
 * @author Hana, Valeria
 */
public class Character {
    private static final String TAG = "Character";
//    /**
//     * Firebase data reference for our Firebase
//     */
//    DatabaseReference mRootRef = FirebaseDatabase.getInstance("https://popsicle-game-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
//
//    /**
//     * Firebase data reference to store the data under the root child "game"
//     */
//    DatabaseReference mGameRef = mRootRef.child("CharXPos");
//    DatabaseReference mPosRef = mRootRef.child("CharYPos");

    /**
     * Position is the position of the Character in the Universe
     */
    private Position pos;

    /**
     * Width is the width of the Character, Height is the height of the Character,
     * ScreenX is the screen width of the emulator device, ScreenY is the screen height of the emulator device
     */
    int width, height, screenX, screenY;

    /**
     * Booleans as a Flag on the movement direction of the Character.
     * Booleans initial state are False. When the user clicks the Console,
     * then depending on which Consoles they click (Up, Down, Left, or Right),
     * the corresponding Boolean will be set to true.
     */
    Boolean isMovingUp = false, isMovingDown = false, isMovingLeft = false, isMovingRight = false;

    /**
     * Getter function to get the move up boolean
     * @return Boolean of isMovingUp
     */
    public Boolean getMovingUp() {
        return isMovingUp;
    }

    /**
     * Getter function to get the move down boolean
     * @return Boolean of isMovingDown
     */
    public Boolean getMovingDown() {
        return isMovingDown;
    }

    /**
     * Getter function to get the move left boolean
     * @return Boolean of isMovingLeft
     */
    public Boolean getMovingLeft() {
        return isMovingLeft;
    }

    /**
     * Getter function to get the move right boolean
     * @return Boolean of isMovingRight
     */
    public Boolean getMovingRight() {
        return isMovingRight;
    }

    /**
     * Getter function to get the width of the character
     * @return width of the character
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter function to get the height of the character
     * @return height of the character
     */
    public int getHeight() {
        return height;
    }
//    /**
//     * The QueueX to store the x-coordinates of the Characters Position
//     */
//    Queue<Integer> queueX = new LinkedList<>();
//
//    /**
//     * The QueueY to store the y-coordinates of the Characters Position
//     */
//    Queue<Integer> queueY = new LinkedList<>();

    int livesCounter = 9;

    /**
     * Character class constructor that takes the "direction" of the Character.
     * The direction is essentially which Character it is: Character A or Character B.
     * If it's character A (the direction specified is "a"), then the Position of
     * character A will be on the left of the screen.
     * If it's character B (the direction specified is "b"), then the Position of
     * character B will be on the right of the screen.
     * The Character class constructor also sets the width and height of the Character
     * by taking the value from our Constants class.
     * @param direction Specifying Character "a" or "b"
     */
    public Character(String direction){
        Constants constants = new Constants();
        this.screenX = constants.screenX;
        this.screenY = constants.screenY;

        if(direction.equals("a")){
            this.pos = new Position(screenX/8, (screenY *45)/100);
        } else if (direction == "b"){
            this.pos = new Position((screenX * 13)/16, (screenY *45)/100);
        }

        this.width = Constants.charWidth;
        this.height = Constants.charHeight;
    }

    /**
     * Creates the rectangle of the Character based on the size of the Character picture.
     * The purpose of this is so that when a Character collides with a Syrup or a Popsicle
     * by the coordinates of their rectangle pictures, then the game will be terminated.
     * @return the Rectangle picture of the Character
     */
    public Rect getCollisionShape(){
        int left = (int) (this.getPos().getX()*65)/64 ;
        int top = (int) (this.getPos().getY()*65)/64 ;
        int right = (int) (this.getPos().getX() + this.getWidth())*61/64;
        int bottom = (int) (this.getPos().getY() + this.getHeight())*61/64;
        return new Rect(left, top, right, bottom);
}

    /**
     * Getter function for the Character's position
     * @return The position of the Character
     */
    public Position getPos() {
        return pos;
    }


    /**
     * Moves the Character to the left, using the method from the Position class
     */
    public void moveLeft() {
//        readingWoohoo();
        this.pos.addLeft();
    }

    /**
     * Moves the Character to the right, using the method from the Position class
     */
    public void moveRight() {
//        readingWoohoo();
        this.pos.addRight();
    }

    /**
     * Moves the Character up, using the method from the Position class
     */
    public void moveUp() {
//        readingWoohoo();
        this.pos.addUp();
    }

    /**
     * Moves the Character down, using the method from the Position class
     */
    public void moveDown() {
//        readingWoohoo();
        this.pos.addDown();
    }

    /**
     * toString method to print the Position x-coordinate and y-coordinate
     * of the Character for debugging purposes
     * @return the Position of the Character, in x and y coordinates
     */
    @Override
    public String toString() {
        return "Character{" +
                "pos=" + pos +
                '}';
    }

    /**
     * To set whether the Character is moving Up.
     * If the user clicks the up console, the isMovingUp
     * will be set to True. If not, it will be False.
     * @param movingUp the state of the character moving up (True or False)
     *
     */
    public void setMovingUp(Boolean movingUp) {
        isMovingUp = movingUp;
    }

    /**
     * To set whether the Character is moving Down.
     * If the user clicks the up console, the isMovingDown
     * will be set to True. If not, it will be False.
     * @param movingDown the state of the character moving down (True or False)
     *
     */
    public void setMovingDown(Boolean movingDown) {
        isMovingDown = movingDown;
    }

    /**
     * To set whether the Character is moving Left.
     * If the user clicks the up console, the isMovingLeft
     * will be set to True. If not, it will be False.
     * @param movingLeft the state of the character moving left (True or False)
     *
     */
    public void setMovingLeft(Boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    /**
     * To set whether the Character is moving Right.
     * If the user clicks the up console, the isMovingRight
     * will be set to True. If not, it will be False.
     * @param movingRight the state of the character moving right (True or False)
     *
     */
    public void setMovingRight(Boolean movingRight) {
        isMovingRight = movingRight;
    }

    /**
     * The setter function to set the position of the Character on the Universe
     * @param pos the position of the character
     */
    public void setPos(Position pos) {
        this.pos = pos;
    }

//    public void readingWoohoo(){
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Integer pos = dataSnapshot.getValue(Integer.class);
//                queueX.add(pos);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//            }
//        };
//        mGameRef.addListenerForSingleValueEvent(postListener);
//
//        ValueEventListener yListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Integer pos = dataSnapshot.getValue(Integer.class);
//                queueY.add(pos);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//            }
//        };
//        mPosRef.addListenerForSingleValueEvent(yListener);
//    }

//    public Queue<Integer> getQueueX() {
//        return queueX;
//    }
//
//    public Queue<Integer> getQueueY() {
//        return queueY;
//    }

    public int getLivesCounter() {
        return livesCounter;
    }

    public int decrementLives(){
        this.livesCounter = this.livesCounter - 1;
        return livesCounter;
    }

    public int getVisualizedLivesCounter() {
        return livesCounter/3;
    }
}
