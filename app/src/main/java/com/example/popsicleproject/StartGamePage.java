package com.example.popsicleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class StartGamePage extends AppCompatActivity {
    // private Button playButton;
    private Button createGameButton;
    private Button joinGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_page);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        createGameButton = (Button) findViewById(R.id.button1);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGameActivity();
            }
        });

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
                startActivity(new Intent(StartGamePage.this,MainActivity.class));
            }
        });

    }

    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createGameActivity() {
        Intent intent = new Intent(this, CreateGame.class);
        startActivity(intent);
    }

    public void joinGameActivity() {
        Intent intent = new Intent(this, JoinGame.class);
        startActivity(intent);
    }
}
