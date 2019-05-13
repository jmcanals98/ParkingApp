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

    private ImageView back;
    private ImageView floor1;
    private TextView name;
    private TextView street;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_info);

        back = (ImageView)findViewById(R.id.ivBack);
        floor1= (ImageView)findViewById(R.id.ivFloor1);
        name=(TextView)findViewById(R.id.tvNameParkingInfo);
        street=(TextView)findViewById(R.id.tvStreetParkingInfo);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingInfoActivity.this, ParkingListActivity.class));
            }
        });

        floor1.setOnClickListener(new View.OnClickListener() {
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
        cursor.moveToFirst();
        name.setText(cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.NAME)));
        String locID=cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.LOCATION_ID));



        Cursor cursor2 = contentResolver.query(ModelContracts.LocationModel.buildContentUri(), ModelContracts.LocationModel.DEFAULT_PROJECTIONS,ModelContracts.LocationModel.buildIdSelection(), ModelContracts.LocationModel.buildIdSelectionArgs(Integer.parseInt(locID)), ModelContracts.LocationModel.DEFAULT_SORT);
        cursor2.moveToFirst();
        String streetString = "C/ "+cursor2.getString(cursor2.getColumnIndex(ModelContracts.LocationModel.STREET_ADDRESS));

        street.setText(streetString);





    }
}
