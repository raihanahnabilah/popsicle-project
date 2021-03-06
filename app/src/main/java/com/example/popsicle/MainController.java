package com.example.popsicle;

import android.util.Log;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.popsicle.models.Position;
import com.example.popsicle.models.WhichPlayer;
import com.example.popsicle.models.WhichPlayer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.example.popsicle.io.InputHandler;
import com.example.popsicle.io.InputListener;
import com.example.popsicle.io.MoveCharacterAction;
import com.example.popsicle.models.Universe;
import com.example.popsicle.rendering.GraphicsRenderer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The MainController is a thread that creates and runs our Universe,
 * IO, and Graphics Renderer at every time.
 * @author Hana, Valeria
 */
public class MainController extends Thread{

    private static final String TAG = "Main Controller";
    /**
     * The SurfaceView in our MainActivity
     */
    private final SurfaceView sv;

    /**
     * The Universe that creates all elements in our game
     */
    private final Universe universe;

    /**
     * The GraphicsRenderer that render our game on Canvas
     */
    private final GraphicsRenderer graphicsRenderer;

    /**
     * The MainActivity that creates the game
     */
    MainActivity activity;

    /**
     * The savedUserUID gets the user ID from the database.
     */
    String savedUserUID;

    /**
     * The MainController constructor creates the universe, the renderer
     * and the IO of our game.
     * @param activity The MainActivity of our game
     * @param sv The SurfaceView that holds the game being drawn in the Canvas
     */
    public MainController(MainActivity activity, SurfaceView sv, String savedUserUID){
        // Creating the universe
        this.sv = sv;
        this.activity = activity;
        this.universe = new Universe();
        this.savedUserUID = savedUserUID;

        // Rendering the assets
        this.graphicsRenderer = new GraphicsRenderer(activity, universe, sv.getResources());
        this.universe.setCallBack(this.graphicsRenderer);
        this.sv.setWillNotDraw(false);
        this.sv.getHolder().addCallback(this.graphicsRenderer);

        InputListener inputListener = new InputListener();
        this.sv.setOnTouchListener(inputListener);

        InputHandler inputHandler = new InputHandler();
        inputHandler.setOnClickAction(new MoveCharacterAction(this.universe));
        inputListener.setCallback(inputHandler);
    }

    /**
     * The run method to continuously update and draw all elements
     * in our Universe by calling the important methods in our Universe
     * such as updating the Character's position and the Syrup's position,
     * generating the Syrups, and checking if the Popsicle collides with the
     * Character or if the Syrup collides with the Character to terminate the
     * game. Additionally, it continues to write to and read from the firebase to
     * continuously update and render the position of one's and the enemy's Character.
     *
     */
    @Override
    public void run() {
        int counter = 0;
        while (this.universe.getPlaying()){
            this.universe.updateCharacter();
            this.universe.syrupSteps();
            if (WhichPlayer.amIPlayerA){
                this.universe.universeToFirebaseA();
                this.universe.readCharacterBFromCharacterA();
                this.universe.checkSyrupCollisionA();
                this.universe.checkPopsicleACollision();
                this.universe.readStatusBFromA();
                if (!this.universe.getQueueX().isEmpty()) {
                    if (this.universe.getQueueX().size() > this.universe.getQueueY().size()) {
                        this.universe.readCharacter(this.universe.getQueueX().remove(), (int) this.universe.getCharacterB().getPos().getY(), universe.getCharacterB());
                    } else if (this.universe.getQueueX().size() == this.universe.getQueueY().size()) {
                        this.universe.readCharacter(this.universe.getQueueX().remove(), this.universe.getQueueY().remove(), universe.getCharacterB());
                    }
                }
                if (!this.universe.getQueueY().isEmpty()) {
                    if (this.universe.getQueueX().size() < this.universe.getQueueY().size()){
                        this.universe.readCharacter((int) this.universe.getCharacterB().getPos().getX(), this.universe.getQueueY().remove(), universe.getCharacterB());
                    } else if (this.universe.getQueueX().size() == this.universe.getQueueY().size()){
                        this.universe.readCharacter(this.universe.getQueueX().remove(), this.universe.getQueueY().remove(), universe.getCharacterB());
                    }
                }
            }


            if (WhichPlayer.amIPlayerB){
                this.universe.readCharacterAFromCharacterB();
                this.universe.universeToFirebaseB();
                this.universe.checkSyrupCollisionB();
                this.universe.checkPopsicleBCollision();
                this.universe.readStatusAFromB();
                if (!this.universe.getQueueX().isEmpty()) {
                    if (this.universe.getQueueX().size() > this.universe.getQueueY().size()) {
                        this.universe.readCharacter(this.universe.getQueueX().remove(), (int) this.universe.getCharacterA().getPos().getY(), universe.getCharacterA());
                    } else if (this.universe.getQueueX().size() == this.universe.getQueueY().size()) {
                        this.universe.readCharacter(this.universe.getQueueX().remove(), this.universe.getQueueY().remove(), universe.getCharacterA());
                    }
                }
                if (!this.universe.getQueueY().isEmpty()) {
                    if (this.universe.getQueueX().size() < this.universe.getQueueY().size()){
                        this.universe.readCharacter((int) this.universe.getCharacterA().getPos().getX(), this.universe.getQueueY().remove(), universe.getCharacterA());
                    } else if (this.universe.getQueueX().size() == this.universe.getQueueY().size()){
                        this.universe.readCharacter(this.universe.getQueueX().remove(), this.universe.getQueueY().remove(), universe.getCharacterA());
                    }
                }
            }
            counter += 1;
            if (counter % 30 == 0){
                this.universe.randomlyAddSyrups((counter-1) % 4, universe.getCloudA2(), "a2");
            }
            if (counter % 221 == 0){
                this.universe.randomlyAddSyrups((counter-1) % 4, universe.getCloudA1(), "a1");
            }
            if (counter % 220 == 0){
                this.universe.randomlyAddSyrups((counter-1) % 4, universe.getCloudB1(), "b1");
            }
            if (counter % 29 == 0){
                this.universe.randomlyAddSyrups((counter-1) % 4, universe.getCloudB2(), "b2");
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
