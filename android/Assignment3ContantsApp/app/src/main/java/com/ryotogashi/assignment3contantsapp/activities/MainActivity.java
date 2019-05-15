package com.ryotogashi.assignment3contantsapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ryotogashi.assignment3contantsapp.R;
import com.ryotogashi.assignment3contantsapp.models.Contact;
import com.ryotogashi.assignment3contantsapp.models.ContactList;
import com.ryotogashi.assignment3contantsapp.network.ContactClient;

import java.io.IOException;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recycler_view);

        Call<ContactList> call = ContactClient.getContacts(10);
        call.enqueue(new Callback<ContactList>() {
            @Override
            public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                if (response.isSuccessful()) {

                    mRecyclerView.setAdapter(new ContactListAdapter(response.body(), getApplicationContext()));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_button) {
            Intent intent = new Intent(MainActivity.this, AddContact.class);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        if (requestCode == 1001) {
            if (resultCode == Activity.RESULT_OK) {

                String name = intent.getStringExtra("name");
                String number = intent.getStringExtra("phone");

//                ContactList newContacts = new ContactList(this.contacts.getContactList());
//
//                newContacts.addContact(new Contact(name, name, number));
                contacts.addContact(new Contact(name, name, number));
            }
        }
    }
}