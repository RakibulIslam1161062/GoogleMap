package com.example.googlemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CommitteeList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_list);
        this.getSupportActionBar().hide();
    }
}
