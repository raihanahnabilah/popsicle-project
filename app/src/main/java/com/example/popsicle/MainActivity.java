package com.example.popsicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

/**
 * MainActivity class is to create the main game on screen
 * @author Hana
 */
public class MainActivity extends AppCompatActivity{


    private static final String TAG = "MainActivity";

    /**
     * The onCreate method is to create the main game on screen
     * by finding the surfaceView ID in the mainActivity layout and
     * call the mainController. The MainController is a thread that
     * creates and runs our Universe, IO, and Graphics Renderer.
     * @param savedInstanceState the savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate Started");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Get the uid from shared preferences VERY IMPORTANT
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String savedUserUID = sp.getString("userUID", "");
        System.out.println("The user UID is from sharedp is " + savedUserUID);

        // Using surfaceView
        SurfaceView sv = findViewById(R.id.surfaceView);

        MainController mc = new MainController(this, sv, savedUserUID);
        mc.start();

        Log.d(TAG, "onCreate Finished");

    }

}