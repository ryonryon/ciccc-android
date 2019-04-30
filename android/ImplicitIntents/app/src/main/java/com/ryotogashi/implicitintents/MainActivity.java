package com.ryotogashi.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteET;
    private EditText mLocationET;
    private EditText mShareTextET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteET = findViewById(R.id.website_edittext);
        mLocationET = findViewById(R.id.location_edittext);
        mShareTextET = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        // 1. get the text from the EditText
        String urlString = mWebsiteET.getText().toString();

        // 2. we need to create an Intent Object (Implicit)
        Uri webUri = Uri.parse(urlString);
        Intent intent = new Intent(Intent.ACTION_VIEW, webUri);

        // 3. start the activity
        if(intent.resolveActivity(getPackageManager()) == null){
            return;
        }
        startActivity(intent);
    }

    public void openLocation(View view) {
        // 1. get the text from the EditText
        String location = mLocationET.getText().toString();

        // 2. we need to create an Intent Object (Implicit)
        Uri locUri = Uri.parse("geo:0,0?q="+ location);
        Intent intent = new Intent(Intent.ACTION_VIEW, locUri);

        // 3. start the activity
        if(intent.resolveActivity(getPackageManager()) == null){
            return;
        }
        startActivity(intent);
    }

    public void shareText(View view) {
        // 1. get the text from the EditText
        String textString = mShareTextET.getText().toString();

        String type = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(type)
                .setChooserTitle("Share this text with: ")
                .setText(textString)
                .startChooser();


    }
}
