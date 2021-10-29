package com.example.popsicle;

import android.graphics.Point;
import android.view.SurfaceView;

import com.example.popsicle.io.InputHandler;
import com.example.popsicle.io.InputListener;
import com.example.popsicle.io.MoveCharacterAction;
import com.example.popsicle.models.Universe;
import com.example.popsicle.rendering.GraphicsRenderer;

public class MainController extends Thread{

    private final SurfaceView sv;
    private final Universe universe;
    private final GraphicsRenderer graphicsRenderer;
    Boolean isPlaying = true, isGameOver = false;

    public MainController(SurfaceView sv, int screenX, int screenY){
        // Creating the universe
        this.sv = sv;
        this.universe = new Universe(screenX, screenY, sv, this);

        // Rendering the assets
        this.graphicsRenderer = new GraphicsRenderer(universe, sv.getResources(), screenX, screenY, this);
        this.universe.setCallBack(this.graphicsRenderer);
        this.sv.setWillNotDraw(false);
        this.sv.getHolder().addCallback(this.graphicsRenderer);

        InputListener inputListener = new InputListener();
        this.sv.setOnTouchListener(inputListener);

        InputHandler inputHandler = new InputHandler();
        inputHandler.setOnClickAction(new MoveCharacterAction(this.universe));
        inputListener.setCallback(inputHandler);
    }


    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public Boolean getGameOver() {
        return isGameOver;
    }

    public void setGameOver(Boolean gameOver) {
        isGameOver = gameOver;
    }


    @Override
    public void run() {
        int counter = 0;
        while (isPlaying){
            this.universe.updateCharacter();
            this.universe.syrupSteps();
            counter += 1;
            if (counter % 30 == 0){
                this.universe.addSyrup(500, 830, "a2");
            }
            if (counter % 70 ==0){
                this.universe.addSyrup(810,125, "a1");
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
