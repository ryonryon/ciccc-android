package com.ryotogashi.activitiesexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nameTV = findViewById(R.id.nameTV);

        // get the intent object that started this activity
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        // display
        nameTV.setText(username);


    }
}
