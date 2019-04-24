package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class parking_info extends AppCompatActivity {

    private ImageView Back;
    private ImageView Floor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);

        Back = (ImageView)findViewById(R.id.ivBack);
        Floor1= (ImageView)findViewById(R.id.ivFloor1);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(parking_info.this,parking_list.class));
            }
        });

        Floor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(parking_info.this,floor_info.class));
            }
        });
    }
}
