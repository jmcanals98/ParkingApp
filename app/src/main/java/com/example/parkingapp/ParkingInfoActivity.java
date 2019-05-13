package com.example.parkingapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class ParkingInfoActivity extends AppCompatActivity {

    private ImageView Back;
    private ImageView Floor1;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);

        Back = (ImageView)findViewById(R.id.ivBack);
        Floor1= (ImageView)findViewById(R.id.ivFloor1);
        name=(TextView)findViewById(R.id.tvName);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingInfoActivity.this, ParkingListActivity.class));
            }
        });

        Floor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingInfoActivity.this, FloorInfoActivity.class));
            }
        });

        String cn = getIntent().getStringExtra("companyname");

        int numCN=Integer.parseInt(cn);

        ContentResolver contentResolver = getContentResolver();

        String defaultOrder = ModelContracts.ParkingModel.DEFAULT_SORT;
        String projections[] = ModelContracts.ParkingModel.DEFAULT_PROJECTIONS;
        String selection="company_number=?";

        Cursor cursor = contentResolver.query(ModelContracts.ParkingModel.buildContentUri(), projections,selection, ModelContracts.ParkingModel.buildDefaultSelectionArgs(numCN), defaultOrder);
        int numElements = cursor.getCount();
        String c=cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.NAME));

        name.setText(cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.NAME)));




    }
}
