package com.ryotogashi.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String mOrderMessage;

    private TextView donutDescription;
    private TextView iceCreamDescription;
    private TextView froyoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        donutDescription = findViewById(R.id.donut_description);
        iceCreamDescription = findViewById(R.id.ice_cream_description);
        froyoDescription = findViewById(R.id.froyo_description);

        registerForContextMenu(donutDescription);
        registerForContextMenu(iceCreamDescription);
        registerForContextMenu(froyoDescription);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_discription, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_order) {
            goToOrderActivity();

            return true;
        } else if (id == R.id.action_contact) {
            displayToast("Contact Selected.");

            return true;
        } else if (id == R.id.action_favorites){
            displayToast("Favorites Selected.");

            return true;
        } else if (id == R.id.action_status) {
            displayToast("Status Selected.");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showDonutOrder(View view) {
        mOrderMessage = getString(R.string.donuts_are_glazed_and_sprinkled_with_candy);
        displayToast(mOrderMessage);
    }

    public void showIceCreamOrder(View view) {
        mOrderMessage = getString(R.string.ice_cream_sandwiches_have_chocolate_wafers_and_vanilla_filling);
        displayToast(mOrderMessage);
    }

    public void showFroyoOrder(View view) {
        mOrderMessage = getString(R.string.froyo_is_premium_self_serve_frozen_yogurt);
        displayToast(mOrderMessage);
    }

    public void onClick(View view) {
        goToOrderActivity();
    }

    private void goToOrderActivity() {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_Copy:
                displayToast("Copy is selected");
                return true;
            case R.id.action_Search:
                displayToast("Search is selected");
                return true;
            case R.id.action_Share:
                displayToast("Share is selected");
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }
}
