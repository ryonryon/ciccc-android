package com.ryotogashi.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner movieTitle;
    private TextView movieContent;
    private ImageView posterIV;

    private static String[] imgRes = {
            "catch_me_if_you_can",
            "fight_club",
            "forrest_gump",
            "god_father",
            "good_will_hunting",
            "pulp_fiction",
            "the_hangover",
            "the_shaw_shank_redemption",
            "titanic"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieTitle = findViewById(R.id.title_spinner);
        movieTitle.setOnItemSelectedListener(this);

    }

    private String getSummery(String name) {

        int resId = getResources().getIdentifier(name, "raw", getPackageName());
        String summary = "";
        Scanner in = new Scanner(getResources().openRawResource(resId));
        while (in.hasNext()) {
            summary += in.nextLine();
        }

        return summary;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // set Image
        posterIV = findViewById(R.id.movieIV);
        posterIV.setImageResource(getResources().getIdentifier(imgRes[position], "drawable", getPackageName()));

        // set Text
        movieContent = findViewById(R.id.movieContentTV);
        String summary = getSummery(imgRes[position]);
        movieContent.setText(summary);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
