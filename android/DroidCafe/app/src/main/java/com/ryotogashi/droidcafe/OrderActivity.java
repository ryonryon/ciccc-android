package com.ryotogashi.droidcafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OrderActivity extends AppCompatActivity {

    private static final String EXTRA_DELIVER_INFO = "DELIVER_INFO";

    private EditText name;
    private EditText street;
    private EditText city;
    private EditText province;
    private EditText postalCodee;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        name = findViewById(R.id.nameET);
        street = findViewById(R.id.streetET);
        city = findViewById(R.id.cityET);
        province = findViewById(R.id.provinceET);
        postalCodee = findViewById(R.id.postCodeET);
        phoneNumber = findViewById(R.id.phoneET);

    }

    public void continueDeliver(View view) {

        UserInformation userInfo = new UserInformation(
                name.getText().toString()
                , street.getText().toString()
                , city.getText().toString()
                , province.getText().toString()
                , postalCodee.getText().toString()
                , phoneNumber.getText().toString()
                , UserInformation.DeliverType.Soon);

        Intent intent = new Intent(this, ConfirmationActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DELIVER_INFO, userInfo);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
