package com.ryotogashi.simplecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText topET;
    private EditText bottomET;
    private TextView ansTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topET = findViewById(R.id.numText1);
        bottomET = findViewById(R.id.numText2);
        ansTV = findViewById(R.id.ansText);
    }

    private void compute(Operator opr) {

        if(topET.getText().toString().isEmpty() || bottomET.getText().toString().isEmpty()){

            return;
        }

        double ans = 0.0;
        double topText = Integer.parseInt(topET.getText().toString());
        double bottomText = Integer.parseInt(bottomET.getText().toString());

        Calculator cal = new Calculator();

        try {
            switch (opr){
                case add:
                    ans = cal.add(topText, bottomText);
                    break;

                case sub:
                    ans = cal.sub(topText, bottomText);
                    break;

                case div:
                    ans = cal.div(topText, bottomText);
                    break;

                case mul:
                    ans = cal.mul(topText, bottomText);
                    break;
            }
        } catch (IllegalArgumentException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        ansTV.setText(String.format("%s", ans));
    }

    public void computeAdd(View view) {
        compute(Operator.add);
    }

    public void computeSub(View view) {
        compute(Operator.sub);
    }

    public void computeDiv(View view) {
        compute(Operator.div);
    }

    public void computeMul(View view) {
        compute(Operator.mul);
    }

    public enum Operator {
        add,
        sub,
        div,
        mul,
    }
}
