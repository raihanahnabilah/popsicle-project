package com.example.popsicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import java.util.Random;

/**
 * HomePage class to creates the HomePage of our
 * Game before the users can start the game
 */
public class HomePage extends AppCompatActivity {

    /**
     * The createGame button
     */
    private Button createGameButton;

    /**
     * The joinGame button
     */
    private Button joinGameButton;

    /**
     * UserID generated
     */
    public String userUID;

    /**
     * Random generator
     */
    public Random rng = new Random();

    /**
     * SharedPreferences for Firebase
     */
    public SharedPreferences sp;

    /**
     * The onCreate method to create the HomePage before users
     * begin the game.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // This sets the userUID variable to some random string so player A and player B can be identified
        userUID = generateString(rng, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcedfghijklmnopqrstuvwxyz0123456789)!@#$%^&*(", 11);
        System.out.println("The userUID is : " + userUID);

        // This creates a shared preferences data item which stores the userUID in a global context
        // so we can retrieve the userUID throughout all the classes of the application
        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userUID", userUID);
        editor.commit();

        // This states that if you click the createGameButton, you call the createGameActivity() method
        // and thus you get sent to the create game page
        createGameButton = (Button) findViewById(R.id.button);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGameActivity();
                String savedUserUID = sp.getString("userUID", "");
                System.out.println("The saved uid is : + " + savedUserUID);
            }
        });

        // This states that if you click the joinGameButton, you call the joinGameActivity() method
        // and thus you get sent to the join game page
        joinGameButton = (Button) findViewById(R.id.button2);
        joinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinGameActivity();
            }
        });

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,MainActivity.class));
            }
        });
    }

    /**
     * The createGameActivity sends you to the create game page
     * (create the game activity).
     */
    public void createGameActivity() {
        //Intent intent = new Intent(this, CreateGame.class);
        // startActivity(intent);
        System.out.println("Clicked create Game");
    }

    /**
     * The joinGameActivity method sends you to the join
     * game page (Join game activity)
     */
    public void joinGameActivity() {
        // Intent intent = new Intent(this, JoinGame.class);
        // startActivity(intent);
        System.out.println("Clicked join Game");
    }

    /**
     * The generateString method is to generate the random UID
     * for each player (Player A or B)
     * @param rng Random generator
     * @param characters Specified character "a" or "b"
     * @param length the length of the UID
     * @return
     */
    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}