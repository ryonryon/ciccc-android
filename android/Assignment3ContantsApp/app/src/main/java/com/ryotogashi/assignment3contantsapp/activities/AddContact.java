package com.ryotogashi.assignment3contantsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.*;

import com.ryotogashi.assignment3contantsapp.R;

public class AddContact extends AppCompatActivity {

    private EditText nameET;
    private EditText phoneET;
    private boolean isNameOk = false;
    private boolean isNumberOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameET = findViewById(R.id.nameEV);
        phoneET = findViewById(R.id.phoneEV);

        nameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                validateName(nameET.getText().toString());
            }
        });

        phoneET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                validatePhoneNumber(phoneET.getText().toString());
            }
        });
    }

    public void submit_data(View view) {

        if(!isNameOk){
            Toast.makeText(getApplicationContext(), "Name has to be changed", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isNumberOk){
            Toast.makeText(getApplicationContext(), "Phone Number has to be changed", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra("name", nameET.getText().toString());
        intent.putExtra("phone", phoneET.getText().toString());

        setResult(RESULT_OK, intent);
        finish();

    }

    public void validateName(String name){
        isNameOk = name.matches("^[a-zA-Z]+ [a-zA-Z]+$");
    }

    public void validatePhoneNumber(String phoneNumber) {
        isNumberOk = phoneNumber.matches("^[0-9]{10}$");
    }
}
