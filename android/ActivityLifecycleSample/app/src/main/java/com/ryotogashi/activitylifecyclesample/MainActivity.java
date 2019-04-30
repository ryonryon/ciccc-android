package com.ryotogashi.activitylifecyclesample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView showNumber;
    private int countNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNumber = findViewById(R.id.showNumber);
        showNumber.setText(countNumber);
    }

    public void clickIncrement(View view) {
        countNumber++;

        showNumber.setText(countNumber);
    }

    public void clickDecrement(View view) {
        countNumber--;

        showNumber.setText(countNumber);
    }


}
