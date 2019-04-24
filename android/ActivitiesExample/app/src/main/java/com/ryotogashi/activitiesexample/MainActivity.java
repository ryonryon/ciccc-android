package com.ryotogashi.activitiesexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText nameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.mainActivity);
    }

    public void goToSecondActivity(View view) {
        // go to second activity
        // 1. create an Intent (explicit)
        Intent intent = new Intent(this, SecondActivity.class);
        String userInput = nameET.getText().toString();
        // 2. passing data (put Extra data)
        intent.putExtra("username", userInput);

        // 3. startActivity with intent
        startActivity(intent);

    }

    public void browse(View view) {
        // Implicit Intent
        // "start an activity that can browse this url"
        // 1. create an Intent (implicit)
        // 2. startActivity with intent
        Uri uri = Uri.parse("https://www.google.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
