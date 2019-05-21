package com.ryotogashi.asynctaskexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AsyncTask Object
        // implement abstract methods from AsyncTask

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

    }

    public void startTask(View view){
        MyBackgroundTask task = new MyBackgroundTask(textView, Integer.parseInt(editText.getText().toString()));
        task.execute();
    }
}
