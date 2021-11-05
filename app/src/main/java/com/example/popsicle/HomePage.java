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
    DatabaseReference mRootRef = FirebaseDatabase.getInstance("https://popsicle-game-default-rtdb.asia-southeast1.firebasedatabase.app").getReference();
    DatabaseReference mGameRef = mRootRef.child("CharXPos");
    DatabaseReference mPosRef = mRootRef.child("CharYPos");

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

    Boolean isPlayerBBool = false;

    /**
     * The onCreate method to create the HomePage before users
     * begin the game.
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

        // This states that if you click the createGameButton, you call the createGameActivity() method
        // and thus you get sent to the create game page
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

        // This states that if you click the joinGameButton, you call the joinGameActivity() method
        // and thus you get sent to the join game page
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

        ValueEventListener isPlayerBHere = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Boolean isPlayerB = dataSnapshot.getValue(Boolean.class);
                isPlayerBBool = isPlayerB;
//                while (isPlayerABool == false) {} // wait
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

//        ValueEventListener isPlayerAHere = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Boolean isPlayerA = dataSnapshot.getValue(Boolean.class);
//                isPlayerABool = isPlayerA;
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//            }
//        };
//        mRootRef.child("isPlayerAHere").addValueEventListener(isPlayerAHere);

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