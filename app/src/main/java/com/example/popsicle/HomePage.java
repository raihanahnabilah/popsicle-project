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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

public class HomePage extends AppCompatActivity {

    private Button createGameButton;
    private Button joinGameButton;
    public String userUID;
    public Random rng = new Random();
    public SharedPreferences sp;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance("https://popsicle-game-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    DatabaseReference mGameRef = mRootRef.child("game");

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

    // This sets the playerA userUID to the current userUID
    public void createGameActivity() {
        Game game = new Game(userUID, "NaN");
        mGameRef.setValue(game);
        System.out.println("Clicked create Game");
    }

    // This sets the playerB userUID to the current userUID
    public void joinGameActivity() {
        mGameRef.child("playerB").setValue(userUID);
        System.out.println("Clicked join Game");
    }

    // This is to generate the random UID for each player (Player A or Player B)
    public static String generateString(Random rng, String characters, int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    // This is to generate the initial Game object and record it in firebase
    @IgnoreExtraProperties
    public class Game {
        public String playerA;
        public String playerB;

        public Game() {

        }

        public Game(String playerA, String playerB) {
            this.playerA = playerA;
            this.playerB = playerB;
        }
    }
}