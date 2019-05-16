package com.ryotogashi.assignment3contantsapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ryotogashi.assignment3contantsapp.R;

public class AddContact extends AppCompatActivity {

    private TextView name;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = findViewById(R.id.nameTV);
        phone = findViewById(R.id.phoneTV);
    }

    public void submit_data(View view) {

        Intent intent = new Intent();
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("phone", phone.getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}
