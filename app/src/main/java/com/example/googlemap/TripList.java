package com.example.googlemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TripList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_list);
        this.getSupportActionBar().hide();
    }
}
