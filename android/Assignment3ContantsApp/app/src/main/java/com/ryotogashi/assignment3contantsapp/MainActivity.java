package com.ryotogashi.assignment3contantsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ryotogashi.assignment3contantsapp.models.Contact;
import com.ryotogashi.assignment3contantsapp.models.ContactList;
import com.ryotogashi.assignment3contantsapp.network.ContactClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> mContactList;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<ContactList> request = ContactClient.getContacts(10);

        request.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                    mContactList = response.body().getContactList();
                    for(Contact contact: mContactList) {
                        Log.d(TAG, "-------------------------------------------");
                        Log.d(TAG, "onResponse: " + mContactList.size());
                        Log.d(TAG, "onResponse: " + contact);
                    }
                } else {
                    Log.d(TAG, "somehow failure... ");
                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {

            }
        });
    }
}
