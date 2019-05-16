package com.example.parkingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SlotInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_detailed_info);

        String typeSlot = getIntent().getStringExtra("typeSlot");
    }
}
