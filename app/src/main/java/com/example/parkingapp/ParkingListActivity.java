package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ParkingListActivity extends AppCompatActivity {

    private ImageView Back;
    private ImageView Filter;
    private ImageView Parking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);

        Back = (ImageView)findViewById(R.id.ivBack);
        Filter = (ImageView)findViewById(R.id.ivFilter);
        Parking = (ImageView)findViewById(R.id.ivP1);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingListActivity.this, NavigationActivity.class));
            }
        });

        Parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingListActivity.this, ParkingInfoActivity.class));
            }
        });

        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }
    public void openDialog() {
        FilterDialogFragment dialog = new FilterDialogFragment();
        dialog.show(getSupportFragmentManager(), "example dialog");
    }
}

