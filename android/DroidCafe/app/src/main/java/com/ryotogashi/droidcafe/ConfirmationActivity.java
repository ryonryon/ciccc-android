package com.ryotogashi.droidcafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmationActivity extends AppCompatActivity {

    private static final String EXTRA_DELIVER_INFO = "DELIVER_INFO";

    private TextView nameTV;
    private TextView addressTV;
    private TextView phoneNumberTV;
    private TextView deliverTypeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        nameTV = findViewById(R.id.nameTV);
        addressTV = findViewById(R.id.addressTV);
        phoneNumberTV = findViewById(R.id.phoneNumberTV);
        deliverTypeTV = findViewById(R.id.delivaryTypeTV);

        UserInformation userInfo = (UserInformation)getIntent().getSerializableExtra(EXTRA_DELIVER_INFO);

        nameTV.setText(userInfo.getName());
        addressTV.setText(userInfo.getCity());
        phoneNumberTV.setText(userInfo.getPhoneNumber());
        deliverTypeTV.setText(userInfo.getDeliverType().toString());


    }

    public void clickSubmit(View view) {
        Toast.makeText(getApplicationContext(), "Your order are confirmed", Toast.LENGTH_LONG).show();
    }
}
