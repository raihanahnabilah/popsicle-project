package com.example.popsicleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateGame extends AppCompatActivity {
    Button createGameButton;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mGamesRef = mRootRef.child("games");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        createGameButton = (Button) findViewById(R.id.createGameButton);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGamesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { mGamesRef.setValue("new game"); }
        });
    }

}