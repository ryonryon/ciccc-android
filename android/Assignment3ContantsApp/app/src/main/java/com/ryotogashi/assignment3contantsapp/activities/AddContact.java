package com.ryotogashi.assignment3contantsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.ryotogashi.assignment3contantsapp.R;

public class AddContact extends AppCompatActivity {

    private EditText nameET;
    private EditText phoneET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameET = findViewById(R.id.nameEV);
        phoneET = findViewById(R.id.phoneEV);
    }

    public void submit_data(View view) {

        Intent intent = new Intent();
        intent.putExtra("name", nameET.getText().toString());
        intent.putExtra("phone", phoneET.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}
