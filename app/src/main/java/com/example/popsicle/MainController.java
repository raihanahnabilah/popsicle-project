package com.example.popsicle;

import android.graphics.Point;
import android.view.SurfaceView;

import com.example.popsicle.io.InputHandler;
import com.example.popsicle.io.InputListener;
import com.example.popsicle.io.MoveCharacterAction;
import com.example.popsicle.models.Universe;
import com.example.popsicle.rendering.GraphicsRenderer;

import java.util.Random;

public class MainController extends Thread{

    private final SurfaceView sv;
    private final Universe universe;
    private final GraphicsRenderer graphicsRenderer;
    MainActivity activity;

    public MainController(MainActivity activity, SurfaceView sv, int screenX, int screenY){
        // Creating the universe
        this.sv = sv;
        this.activity = activity;
        this.universe = new Universe(screenX, screenY);

        // Rendering the assets
        this.graphicsRenderer = new GraphicsRenderer(activity, universe, sv.getResources(), screenX, screenY);
        this.universe.setCallBack(this.graphicsRenderer);
        this.sv.setWillNotDraw(false);
        this.sv.getHolder().addCallback(this.graphicsRenderer);

        InputListener inputListener = new InputListener();
        this.sv.setOnTouchListener(inputListener);

        InputHandler inputHandler = new InputHandler();
        inputHandler.setOnClickAction(new MoveCharacterAction(this.universe));
        inputListener.setCallback(inputHandler);
    }

    @Override
    public void run() {
        int counter = 0;
        while (this.universe.getPlaying()){
            this.universe.updateCharacter();
            this.universe.syrupSteps();
            counter += 1;
            Random ran = new Random();
            int n = ran.nextInt(4);
            if (counter % 30 == 0){
                if (n==0){
                    this.universe.addSyrup(this.universe.getCloudA2().getPos().getX(), this.universe.getCloudA2().getPos().getY() + this.universe.getCloudA2().height/2, "a2");
                }
                else if (n==1){
                    this.universe.addSyrup(this.universe.getCloudA2().getPos().getX() + this.universe.getCloudA2().width, this.universe.getCloudA2().getPos().getY() + this.universe.getCloudA2().height/2, "a2");

                }
                else if (n==2){
                    this.universe.addSyrup(this.universe.getCloudA2().getPos().getX() + this.universe.getCloudA2().width/2, this.universe.getCloudA2().getPos().getY() + this.universe.getCloudA2().height/4, "a2");
                }
                else if (n==3){
                    this.universe.addSyrup(this.universe.getCloudA2().getPos().getX() + this.universe.getCloudA2().width/2, this.universe.getCloudA2().getPos().getY() + (this.universe.getCloudA2().height*3)/4, "a2");
                }
            }
            if (counter % 29 == 0){
                if (n==0){
                    this.universe.addSyrup(this.universe.getCloudA1().getPos().getX(), this.universe.getCloudA1().getPos().getY() + this.universe.getCloudA1().height/2, "a1");
                }
                else if (n==1){
                    this.universe.addSyrup(this.universe.getCloudA1().getPos().getX() + this.universe.getCloudA1().width, this.universe.getCloudA1().getPos().getY() + this.universe.getCloudA1().height/2, "a1");

                }
                else if (n==2){
                    this.universe.addSyrup(this.universe.getCloudA1().getPos().getX() + this.universe.getCloudA1().width/2, this.universe.getCloudA1().getPos().getY() + this.universe.getCloudA1().height/4, "a1");
                }
                else if (n==3){
                    this.universe.addSyrup(this.universe.getCloudA1().getPos().getX() + this.universe.getCloudA1().width/2, this.universe.getCloudA1().getPos().getY() + (this.universe.getCloudA1().height*3)/4, "a1");
                }
            }
            if (counter % 31 == 0){
                if (n==0){
                    this.universe.addSyrup(this.universe.getCloudB1().getPos().getX(), this.universe.getCloudB1().getPos().getY() + this.universe.getCloudB1().height/2, "b1");
                }
                else if (n==1){
                    this.universe.addSyrup(this.universe.getCloudB1().getPos().getX() + this.universe.getCloudB1().width, this.universe.getCloudB1().getPos().getY() + this.universe.getCloudB1().height/2, "b1");

                }
                else if (n==2){
                    this.universe.addSyrup(this.universe.getCloudB1().getPos().getX() + this.universe.getCloudB1().width/2, this.universe.getCloudB1().getPos().getY() + this.universe.getCloudB1().height/4, "b1");
                }
                else if (n==3){
                    this.universe.addSyrup(this.universe.getCloudB1().getPos().getX() + this.universe.getCloudB1().width/2, this.universe.getCloudB1().getPos().getY() + (this.universe.getCloudB1().height*3)/4, "b1");
                }
            }
            if (counter % 37 == 0){
                if (n==0){
                    this.universe.addSyrup(this.universe.getCloudB2().getPos().getX(), this.universe.getCloudB2().getPos().getY() + this.universe.getCloudB2().height/2, "b2");
                }
                else if (n==1){
                    this.universe.addSyrup(this.universe.getCloudB2().getPos().getX() + this.universe.getCloudB2().width, this.universe.getCloudB2().getPos().getY() + this.universe.getCloudB2().height/2, "b2");

                }
                else if (n==2){
                    this.universe.addSyrup(this.universe.getCloudB2().getPos().getX() + this.universe.getCloudB2().width/2, this.universe.getCloudB2().getPos().getY() + this.universe.getCloudB2().height/4, "b2");
                }
                else if (n==3){
                    this.universe.addSyrup(this.universe.getCloudB2().getPos().getX() + this.universe.getCloudB2().width/2, this.universe.getCloudB2().getPos().getY() + (this.universe.getCloudB2().height*3)/4, "b2");
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
