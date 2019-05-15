package com.example.parkingapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class FloorInfoActivity extends AppCompatActivity {

    private ImageView Back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_info);

        Back = (ImageView)findViewById(R.id.ivBack);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FloorInfoActivity.this, ParkingInfoActivity.class));
            }
        });

        ContentResolver contentResolver = getContentResolver();

        String defaultOrder = ModelContracts.ParkingModel.DEFAULT_SORT;
        String projections[] = ModelContracts.ParkingModel.DEFAULT_PROJECTIONS;
        String selection="company_number=?";
        String cn = getIntent().getStringExtra("id");

        int floorID=Integer.parseInt(cn);
        //Cursor cursor = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections,selection, ModelContracts.ParkingModel.buildDefaultSelectionArgs(numCN), defaultOrder);
//        cursor.moveToFirst();
  //      String parkingName=cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.NAME));

    }
}
