package com.example.popsicleproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinGame extends AppCompatActivity {

    TextView number;
    Button updateButton2;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mRootRef.child("number");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        // Get the uid from shared preferences VERY IMPORTANT
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String savedUserUID = sp.getString("userUID", "");
        System.out.println("The user UID is from sharedp is " + savedUserUID);

        number = (TextView) findViewById(R.id.number);
        updateButton2 = (Button) findViewById(R.id.updateButton2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                number.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConditionRef.setValue("RESET THE BUTTON");
                // System.out.println("The user UID is from sharedp is " + userUID);
            }
        });
    }
}