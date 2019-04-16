package com.ryotogashi.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private Button leftBtt;
    private Button rightBtt;
    private TextView questionTV;
    private TextView scoreTV;
    private boolean isRightAnswer;

    private void generateQuestion() {
        Question question = new Question();
        leftBtt.setBackgroundColor(question.getLeftColor());
        rightBtt.setBackgroundColor(question.getRightColor());
        questionTV.setText(question.getQuestionLabel());
        isRightAnswer = question.isRightAnswer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTV = findViewById(R.id.scoreTV);
        scoreTV.setText("Score: " + score);
        leftBtt = findViewById(R.id.leftBtn);
        rightBtt = findViewById(R.id.rightBtn);
        questionTV = findViewById(R.id.questionTV);

        generateQuestion();

    }

    public void buttonTapped(View view) {

        Button btn = (Button) view;

        if(btn.getId() == R.id.leftBtn) {
            if(isRightAnswer) {
                score--;
                toast("Wrong!");
            } else {
                score++;
                toast("Righte!");
            }
        } else {
            if(isRightAnswer) {
                score++;
                toast("Righte!");
            } else {
                score--;
                toast("Wrong!");
            }
        }
        scoreTV.setText("Score: " + score);
        generateQuestion();
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
