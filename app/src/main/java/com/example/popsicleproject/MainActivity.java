package com.example.popsicleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private GraphicsRenderer graphicsRenderer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        graphicsRenderer = new GraphicsRenderer(this, point.x, point.y);
        setContentView(graphicsRenderer);
    }

    @Override
    protected void onPause(){
        super.onPause();
        graphicsRenderer.pause();
    }

    protected void onResume(){
        super.onResume();
        graphicsRenderer.resume();
    }


}