package com.interpixel.twentyfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ScoreActivity extends AppCompatActivity {

    TextView playerName;
    TextView skorPlayer, skorBot1, skorBot2, skorBot3;
    Button nextRound, mainMenu;
    private static int skorTotalPemain;
    private static int skorTotalBot1;
    private static int skorTotalBot2;
    private static int skorTotalBot3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Random random = new Random();

        int skorPemain = getIntent().getIntExtra("skor", 1000);

        playerName = findViewById(R.id.playerName);
        skorPlayer = findViewById(R.id.skorPemain);
        skorBot1 = findViewById(R.id.skorBot1);
        skorBot2 = findViewById(R.id.skorBot2);
        skorBot3 = findViewById(R.id.skorBot3);
        nextRound = findViewById(R.id.nextround);
        mainMenu = findViewById(R.id.mainmenu);

        if(RoundActivity.ronde == 1){
            skorTotalPemain = 0;
            skorTotalBot1 = 0;
            skorTotalBot2 = 0;
            skorTotalBot3 = 0;
        }

        skorTotalPemain += skorPemain;
        skorTotalBot1 += random.nextInt(100);
        skorTotalBot2 += random.nextInt(100);
        skorTotalBot3 += random.nextInt(100);

        skorPlayer.setText("" + skorTotalPemain);
        skorBot1.setText("" + skorTotalBot1);
        skorBot2.setText("" + skorTotalBot2);
        skorBot3.setText("" + skorTotalBot3);

        if(RoundActivity.ronde < 8){
            nextRound.setVisibility(View.VISIBLE);
            mainMenu.setVisibility(View.INVISIBLE);
        }else{
            nextRound.setVisibility(View.INVISIBLE);
            mainMenu.setVisibility(View.VISIBLE);
        }

        nextRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, RoundActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

    }
}
