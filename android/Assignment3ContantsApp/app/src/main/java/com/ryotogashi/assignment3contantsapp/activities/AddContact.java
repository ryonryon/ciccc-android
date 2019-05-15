package com.ryotogashi.assignment3contantsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ryotogashi.assignment3contantsapp.R;

public class AddContact extends AppCompatActivity {

    private TextView name;
    private TextView phone;
    public static final String TAG = AddContact.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        name = findViewById(R.id.nameTV);
        phone = findViewById(R.id.phoneTV);
    }

    public void submit_data(View view) {

        String nameStr = name.getText().toString();
        String phoneStr = phone.getText().toString();

//        Log.d(TAG, "name: " + nameStr);
//        Log.d(TAG, "phone: " + phoneStr);

        Intent intent = new Intent( this, MainActivity.class );
        intent.putExtra("name", nameStr);
        intent.putExtra("phone", phoneStr);
        int requestCode = 1001;
        startActivityForResult( intent, requestCode );
        finish();
    }
}
