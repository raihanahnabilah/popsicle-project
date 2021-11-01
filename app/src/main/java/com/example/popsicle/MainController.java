package com.example.popsicle;

import android.graphics.Point;
import android.view.SurfaceView;

import com.example.popsicle.io.InputHandler;
import com.example.popsicle.io.InputListener;
import com.example.popsicle.io.MoveCharacterAction;
import com.example.popsicle.models.Position;
import com.example.popsicle.models.Universe;
import com.example.popsicle.rendering.GraphicsRenderer;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * The MainController is a thread that creates and runs our Universe,
 * IO, and Graphics Renderer at every time.
 * @author Hana, Valeria
 */
public class MainController extends Thread{

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

    String savedUserUID;

    /**
     * The QueueX to store the x-coordinates of the Characters Position
     */
    Queue<Integer> queueX = new LinkedList<>();

    /**
     * The QueueY to store the y-coordinates of the Characters Position
     */
    Queue<Integer> queueY = new LinkedList<>();

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

//    @IgnoreExtraProperties
//    public class UniverseData {
//
//        public Position characterA;
//        public Position characterB;
//        public Position popsicleA;
//        public Position popsicleB;
//
//        public UniverseData() {
//            // Default constructor required for calls to DataSnapshot.getValue(User.class)
//        }
//
//        public UniverseData() {
//            this.characterA = this.;
//        }
//    }

    /**
     * The run method to continuously update and draw all elements
     * in our Universe by calling the important methods in our Universe
     * such as updating the Character's position and the Syrup's position,
     * generating the Syrups, and checking if the Popsicle collides with the
     * Character or if the Syrup collides with the Character to terminate the
     * game.
     */
    @Override
    public void run() {
        int counter = 0;
        while (this.universe.getPlaying()){
            this.universe.updateCharacter();
            this.universe.syrupSteps();
            this.universe.checkPopsicleCollision();
            this.universe.checkSyrupCollision();
            //READ AND UPDATE FUNCTION
            // TODO: IMPLEMENT IF USER A CONDITION
            if (!queueX.isEmpty()){
                this.universe.readCharacter(queueX.remove(), queueY.remove(), universe.getCharacterB());
            }
            // TODO: IMPLEMENT IF USER B CONDITION
            if (!queueX.isEmpty()){
                this.universe.readCharacter(queueX.remove(), queueY.remove(), universe.getCharacterA());
            }
            // WRITE FUNCTION
            // TODO: IMPLEMENT IF USER A CONDITION
            this.universe.universeToFirebaseA();
            // TODO: IMPLEMENT IF USER b CONDITION
            this.universe.universeToFirebaseB();

            counter += 1;
//            Random ran = new Random();
//            int n = ran.nextInt(4);
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
