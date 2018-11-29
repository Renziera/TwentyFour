package com.interpixel.twentyfour;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RoundActivity extends AppCompatActivity {

    public static int ronde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        if(getIntent().getBooleanExtra("baru", false)){
            ronde = 0;
        }

        ronde++;

        TextView round = findViewById(R.id.round);
        round.setText("Ronde " + ronde);

        final Intent intent = new Intent(this, GameActivity.class);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 1000);

    }

    @Override
    public void onBackPressed() {

    }
}
