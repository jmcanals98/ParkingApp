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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class ParkingListActivity extends AppCompatActivity implements FilterDialogFragment.ExampleDialogListener {

    private ImageView Back;



    private ImageView Filter;
    private ImageView Parking;
    private String nameType="Selection";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);

        ContentResolver contentResolver = getContentResolver();
        Back = findViewById(R.id.ivBack);
        Filter = findViewById(R.id.ivFilter);
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
        ListView lvItems = findViewById(R.id.lvParkingItems);
        ParkingCursorAdapter parkingAdapter = new ParkingCursorAdapter(this, cursor,0);
        lvItems.setAdapter(parkingAdapter);
        cursor.moveToNext();




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
        nameType=type.toUpperCase();
        String[] result = new String[]{"FREE", nameType};
        ContentResolver contentResolver=getContentResolver();
        Cursor cursor1 = contentResolver.query(ModelContracts.SlotModel.buildContentUri(),ModelContracts.SlotModel.DEFAULT_PROJECTIONS,"slot_state=? AND slot_type=?",result,null );

        int numElem=cursor1.getCount();
        cursor1.moveToFirst();
        Set<String> parkingIDs = new HashSet<>();
        for (int i=0; i<numElem; i++){

            result = new String[]{cursor1.getString(cursor1.getColumnIndex(ModelContracts.SlotModel.FLOOR_ID))};
            Cursor cursor2 = contentResolver.query(ModelContracts.FloorModel.buildContentUri(),ModelContracts.FloorModel.DEFAULT_PROJECTIONS,"id=?",result,null );
            cursor2.moveToFirst();
            for(int j=0; j<cursor2.getCount(); j++){
                parkingIDs.add(cursor2.getString(cursor2.getColumnIndex(ModelContracts.FloorModel.PARKING_ID)));
                cursor2.moveToNext();
            }
            cursor1.moveToNext();
        }

        String[] parkingsIdArray = new String[parkingIDs.size()];
        parkingIDs.toArray(parkingsIdArray);

        Cursor cursor = contentResolver.query(ModelContracts.ParkingModel.buildContentUri(), ModelContracts.ParkingModel.DEFAULT_PROJECTIONS,"company_number=? OR company_number=? OR company_number=?", parkingsIdArray, null);
        cursor.moveToFirst();
        ListView lvItems = findViewById(R.id.lvParkingItems);
        ParkingCursorAdapter parkingAdapter = new ParkingCursorAdapter(this, cursor,0);
        lvItems.setAdapter(parkingAdapter);
        cursor.moveToNext();


    }
}

