package com.example.parkingapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class ParkingListActivity extends AppCompatActivity implements FilterDialogFragment.ExampleDialogListener {

    private ImageView Back;



    private ImageView Filter;
    private ImageView Parking;
    private String nameType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);

        ContentResolver contentResolver = getContentResolver();
        Back = (ImageView)findViewById(R.id.ivBack);
        Filter = (ImageView)findViewById(R.id.ivFilter);
        //Parking = (ImageView)findViewById(R.id.ivP1);


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingListActivity.this, NavigationActivity.class));
            }
        });

        /*Parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingListActivity.this, ParkingInfoActivity.class));
            }
        });
        */
        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


        // Query for items from the database and get a cursor back
        Cursor cursor = contentResolver.query(ModelContracts.ParkingModel.buildContentUri(), ModelContracts.ParkingModel.DEFAULT_PROJECTIONS,null, null, ModelContracts.ParkingModel.DEFAULT_SORT);

        cursor.moveToFirst();
        ListView lvItems = (ListView) findViewById(R.id.lvParkingItems);
        for (int i=0;i<cursor.getCount();i++){
            ParkingCursorAdapter parkingAdapter = new ParkingCursorAdapter(this, cursor,0);
            lvItems.setAdapter(parkingAdapter);
            cursor.moveToNext();
        }



        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ParkingListActivity.this,ParkingInfoActivity.class);

                String cv = (String) view.getTag();
                intent.putExtra("companyname",cv);

                startActivity(intent);
            }
        });

        



    }
    public void openDialog() {
        FilterDialogFragment dialog = new FilterDialogFragment();
        dialog.show(getSupportFragmentManager(), "example dialog");


    }
    @Override
    public void applyTexts(String type) {
        nameType=type;
    }
}

