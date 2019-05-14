package com.ryotogashi.scorekeeper;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private TextView team1TV;
    private TextView team2TV;
    private int team1Count;
    private int team2Count;
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        team1Count = 0;
        team2Count = 0;

        team1TV = findViewById(R.id.score_1);
        team2TV = findViewById(R.id.score_2);

        team1TV.setText(String.valueOf(team1Count));
        team2TV.setText(String.valueOf(team2Count));

        if (savedInstanceState != null) {
            team1Count = savedInstanceState.getInt(STATE_SCORE_1);
            team1Count = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            team1TV.setText(String.valueOf(team1Count));
            team2TV.setText(String.valueOf(team1Count));
        }
    }

    public void decrease(View view) {
        switch (view.getId()){
            case R.id.decreaseTeam1:
                team1Count--;
                team1TV.setText(String.valueOf(team1Count));
                break;

            case R.id.decreaseTeam2:
                team2Count--;
                team2TV.setText(String.valueOf(team2Count));
                break;
        }
    }

    public void increase(View view) {
        switch (view.getId()){
            case R.id.increaseTeam1:
                team1Count++;
                team1TV.setText(String.valueOf(team1Count));
                break;

            case R.id.increaseTeam2:
                team2Count++;
                team2TV.setText(String.valueOf(team2Count));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        outState.putInt(STATE_SCORE_1, team1Count);
        outState.putInt(STATE_SCORE_2, team1Count);
        super.onSaveInstanceState(outState);
    }

}
