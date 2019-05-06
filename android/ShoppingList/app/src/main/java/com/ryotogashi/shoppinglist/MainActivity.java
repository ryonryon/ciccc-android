package com.ryotogashi.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView fruitLV;
    private static final String[] listData = {
        "banana"
        , "apple"
        , "grape"
        , "pomegranate"
        , "tomato"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruitLV = findViewById(R.id.fruitLV);

        BaseAdapter adapter = new ListViewAdapter(this.getApplicationContext(),
                R.layout.list, listData);

        // ListViewにArrayAdapterを設定する
        fruitLV.setAdapter(adapter);
    }
}
