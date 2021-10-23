package com.example.popsicleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private GraphicsRenderer graphicsRenderer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        should give the current windows height and width
        DisplayMetrics metrics = new DisplayMetrics();
        Window window = getWindow();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int windowHeight = metrics.heightPixels;
        int windowWidth = metrics.widthPixels;


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        Point point = new Point();
//        getWindowManager().getDefaultDisplay().getSize(point);

        graphicsRenderer = new GraphicsRenderer(this, windowWidth, windowHeight);
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