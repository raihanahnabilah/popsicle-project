package com.example.popsicleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import java.util.Random;


public class StartGamePage extends AppCompatActivity {
    private Button createGameButton;
    private Button joinGameButton;
    public String userUID;
    public Random rng = new Random();
    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_page);

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
        createGameButton = (Button) findViewById(R.id.button1);
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

        // This states that if you click the play button you will start the game
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartGamePage.this,MainActivity.class));
            }
        });
    }

    // This method sends you to the create game page (create game activity)
    public void createGameActivity() {
        Intent intent = new Intent(this, CreateGame.class);
        startActivity(intent);
    }

    // This method sends you to the join game page (join game activity)
    public void joinGameActivity() {
        Intent intent = new Intent(this, JoinGame.class);
        startActivity(intent);
    }

    // This is to generate the random UID for each player (Player A or Player B)
    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }


}
