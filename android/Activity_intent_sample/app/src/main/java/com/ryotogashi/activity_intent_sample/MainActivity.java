package com.ryotogashi.activity_intent_sample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.ryotogashi.activity_intent_sample.EXTRA_MESSAGE";
    private EditText messageToSend;
    private TextView receivedMessageHeader;
    private TextView receivedMessageContent;

    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageToSend = findViewById(R.id.messageToSend);
        receivedMessageHeader = findViewById(R.id.replyReceive);
        receivedMessageContent = findViewById(R.id.receivedReplyMessage);

    }

    public void sendMessageToAnotherScreen(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        String userMessage = messageToSend.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, userMessage);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                receivedMessageHeader.setVisibility(View.VISIBLE);
                receivedMessageContent.setText(reply);
                receivedMessageContent.setVisibility(View.VISIBLE);

            }
        }
    }

}
