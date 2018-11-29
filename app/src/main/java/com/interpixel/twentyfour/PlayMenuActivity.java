package com.interpixel.twentyfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_menu);

        Button hostGame = findViewById(R.id.hostgame);
        Button joinGame = findViewById(R.id.joingame);

        hostGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayMenuActivity.this, RoundActivity.class);
                intent.putExtra("baru", true);
                startActivity(intent);
                finish();
            }
        });

        joinGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayMenuActivity.this, RoundActivity.class);
                intent.putExtra("baru", true);
                startActivity(intent);
                finish();
            }
        });

    }
}
