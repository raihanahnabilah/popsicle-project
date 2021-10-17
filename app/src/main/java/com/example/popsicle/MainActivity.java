package com.example.popsicle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

import com.example.popsicle.io.InputHandler;
import com.example.popsicle.io.InputListener;
import com.example.popsicle.io.MoveCharacterAction;
import com.example.popsicle.models.Universe;
import com.example.popsicle.rendering.GraphicsRenderer;

public class MainActivity extends AppCompatActivity{


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate Started");

        // Using surfaceView
        SurfaceView sv = findViewById(R.id.surfaceView);

        // Creating the universe
        Universe universe = new Universe();

        // Rendering the assets
        GraphicsRenderer graphicsRenderer = new GraphicsRenderer(universe, sv.getResources());
        universe.setCallBack(graphicsRenderer);
        sv.setWillNotDraw(false);
        sv.getHolder().addCallback(graphicsRenderer);


        InputListener inputListener = new InputListener();
        sv.setOnTouchListener(inputListener);

        InputHandler inputHandler = new InputHandler();
        inputHandler.setOnClickAction(new MoveCharacterAction(universe));
        inputListener.setCallback(inputHandler);

        Log.d(TAG, "onCreate Finished");

    }

}