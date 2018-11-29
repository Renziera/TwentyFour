package com.interpixel.twentyfour;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OptionActivity extends AppCompatActivity {

    private EditText namaPemain;
    private Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        namaPemain = findViewById(R.id.namaPlayer);
        apply = findViewById(R.id.applyButton);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getDefaultSharedPreferences(OptionActivity.this)
                        .edit().putString("nama", namaPemain.getText().toString()).apply();
                finish();
            }
        });
    }
}
