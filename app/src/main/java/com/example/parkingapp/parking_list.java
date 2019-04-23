package com.example.parkingapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class parking_list extends AppCompatActivity {

    private ImageView Back;
    private ImageView Filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);

        Back = (ImageView)findViewById(R.id.ivBack);
        Filter = (ImageView)findViewById(R.id.ivFilter);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(parking_list.this,FirstMenu.class));
            }
        });

        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fr;
            }
        });
    }
}
