package com.example.popsicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import java.util.Random;

import com.example.popsicle.models.Constants;
import com.example.popsicle.models.WhichPlayer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

/**
 * HomePage class to creates the HomePage of our
 * Game before the users can start the game
 */
public class HomePage extends AppCompatActivity {

    private static final String TAG = "HomePage";

    /**
     * The FirebaseDatabase to store our data in the firebase
     */
    DatabaseReference mRootRef = FirebaseDatabase.getInstance("https://popsicle-game-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();

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
     * The Boolean on whether or not player B has joined the game
     */
    Boolean isPlayerBBool = false;

    /**
     * The onCreate method to create the HomePage before users
     * begin the game. It is also the method where we determined
     * who is playerA and playerB by clicking the corresponding buttons.
     * Once playerA has clicked the "Player A" button, playerB will
     * then click the "Player B" button and triggers to begin the game.
     * @param savedInstanceState the savedInstanceState
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

        // This states that if you click player A button
        createGameButton = (Button) findViewById(R.id.button);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhichPlayer whichPlayer = new WhichPlayer(true, false);
                createGameActivity();
                String savedUserUID = sp.getString("userUID", "");
                System.out.println("The saved uid is : + " + savedUserUID);
                mRootRef.child("isPlayerAHere").setValue(true);
                mRootRef.child("amIPlayerA").setValue(true);
                mRootRef.child("isPlayerADead").setValue(false);
                mRootRef.child("isPlayerBDead").setValue(false);
                mRootRef.child("isPlayerAWon").setValue(false);
                mRootRef.child("isPlayerBWon").setValue(false);
            }
        });

        // This states that if you click the player B button
        joinGameButton = (Button) findViewById(R.id.button2);
        joinGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhichPlayer whichPlayer = new WhichPlayer(false,true);
                joinGameActivity();
                mRootRef.child("isPlayerBHere").setValue(true);
                mRootRef.child("amIPlayerB").setValue(true);
            }
        });

        // This is to listen if player B has clicked the player B button
        // to trigger beginning the game
        ValueEventListener isPlayerBHere = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean isPlayerB = dataSnapshot.getValue(Boolean.class);
                isPlayerBBool = isPlayerB;
                if (isPlayerBBool){
                    startActivity(new Intent(HomePage.this,MainActivity.class));
                }
                mRootRef.child("isPlayerAHere").setValue(false);
                mRootRef.child("isPlayerBHere").setValue(false);
                mRootRef.child("isPlayerADead").setValue(false);
                mRootRef.child("isPlayerBDead").setValue(false);
                mRootRef.child("isPlayerAWon").setValue(false);
                mRootRef.child("isPlayerBWon").setValue(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mRootRef.child("isPlayerBHere").addValueEventListener(isPlayerBHere);

        // This is if the user decided to play alone by clicking the main "Play" button
        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WhichPlayer whichPlayer = new WhichPlayer(true, false);
                mRootRef.child("isPlayerADead").setValue(false);
                mRootRef.child("isPlayerBDead").setValue(false);
                mRootRef.child("isPlayerAWon").setValue(false);
                mRootRef.child("isPlayerBWon").setValue(false);
                startActivity(new Intent(HomePage.this,MainActivity.class));
            }
        });
    }


    /**
     * The createGameActivity sets the unique user ID of playerA to the userUID
     * (create the game activity).
     */
    public void createGameActivity() {
        mRootRef.child("playerA").setValue(userUID);
        System.out.println("Clicked create Game");
    }

    /**
     * The joinGameActivity sets the unique user ID of playerB to the userUID
     * game page (Join game activity)
     */
    public void joinGameActivity() {
        mRootRef.child("playerB").setValue(userUID);
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