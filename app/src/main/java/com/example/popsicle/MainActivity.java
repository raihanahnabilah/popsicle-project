package com.example.popsicle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import com.example.popsicle.io.InputHandler;
import com.example.popsicle.io.InputListener;
import com.example.popsicle.io.MoveCharacterAction;
import com.example.popsicle.models.Universe;
import com.example.popsicle.rendering.GraphicsRenderer;

public class MainActivity extends AppCompatActivity{


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate Started");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        // Using surfaceView
        SurfaceView sv = findViewById(R.id.surfaceView);

        MainController mc = new MainController(this, sv, point.x, point.y);
        mc.start();

        Log.d(TAG, "onCreate Finished");

    }

}