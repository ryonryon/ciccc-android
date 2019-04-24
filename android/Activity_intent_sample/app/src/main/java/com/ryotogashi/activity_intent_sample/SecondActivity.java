package com.ryotogashi.activity_intent_sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    // id for extra (Intent)
    public static final String EXTRA_REPLY = "com.ryotogashi.activity_intent_sample.EXTRA_REPLY";

    private TextView receivedMessage;
    private EditText replyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        receivedMessage = findViewById(R.id.receivedMessage);
        replyMessage = findViewById(R.id.replyMessage);

        receivedMessage.setText(message);

    }

    public void replyMessage(View view) {
        String userReply = replyMessage.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, userReply);
        replyMessage = findViewById(R.id.replyMessage);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
