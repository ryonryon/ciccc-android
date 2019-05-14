package com.ryotogashi.assignment3contantsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.ryotogashi.assignment3contantsapp.R;
import com.ryotogashi.assignment3contantsapp.models.Contact;
import com.ryotogashi.assignment3contantsapp.models.ContactList;
import com.ryotogashi.assignment3contantsapp.network.ContactClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ContactList contacts;
    private ContactListAdapter adapter;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Call<ContactList> call = ContactClient.getContacts(10);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {
                    contacts = new ContactList(response.body().getContactList());

                    adapter = new ContactListAdapter(contacts, getApplicationContext());

                    mRecyclerView = findViewById(R.id.recycler_view);

                    mRecyclerView.setAdapter(adapter);

                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                }
            }

            @Override
            public void onFailure(Call<ContactList> call, Throwable t) {
                Log.d(TAG, "failure: " + t);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
