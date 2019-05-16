package com.example.parkingapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class FloorInfoActivity extends AppCompatActivity {

    private ImageView back;
    private TextView parkingName;
    private TextView floorName;
    private TextView freeSlotsCar;
    private TextView freeSlotsElectric;
    private TextView freeSlotsMotorbike;
    private TextView freeSlotsDisabled;
    private TextView freeSlotsBicycle;
    private ImageView carFloorInfo;
    private ImageView electricFloorInfo;
    private ImageView motorbikeFloorInfo;
    private ImageView disabledFloorInfo;
    private ImageView bicycleFloorInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_info);

        back =findViewById(R.id.ivBack);
        parkingName=findViewById(R.id.tvParkingNameFloorInfo);
        floorName=findViewById(R.id.tvFloorNumFloorInfo);
        freeSlotsCar=findViewById(R.id.tvCarFloorInfo);
        freeSlotsElectric=findViewById(R.id.tvElectricFloorInfo);
        freeSlotsMotorbike=findViewById(R.id.tvMotorbikeFloorInfo);
        freeSlotsDisabled=findViewById(R.id.tvDisabledFloorInfo4);
        freeSlotsBicycle=findViewById(R.id.tvBicycleFloorInfo);

        carFloorInfo=findViewById(R.id.ivCarFloorInfo);
        electricFloorInfo=findViewById(R.id.ivElectricFloorInfo);
        motorbikeFloorInfo=findViewById(R.id.ivMotorbikeFloorInfo);
        disabledFloorInfo=findViewById(R.id.ivDisabledFloorInfo);
        bicycleFloorInfo=findViewById(R.id.ivBicycleFloorInfo);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FloorInfoActivity.this, ParkingInfoActivity.class));
            }
        });

        String parkingCompanyNum = getIntent().getStringExtra("parkingCompanyNum");
        final String floorID = getIntent().getStringExtra("floorID");

        ContentResolver contentResolver = getContentResolver();
        String defaultOrder = ModelContracts.ParkingModel.DEFAULT_SORT;
        String projections[] = ModelContracts.ParkingModel.DEFAULT_PROJECTIONS;
        String result[]={parkingCompanyNum};


        Cursor cursor = contentResolver.query(ModelContracts.ParkingModel.buildContentUri(), projections,"company_number=?",result, null);
        cursor.moveToFirst();
        parkingName.setText(cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.NAME)));

        String result2[]={floorID};
        String projections2[] = ModelContracts.FloorModel.DEFAULT_PROJECTIONS;
        cursor=contentResolver.query(ModelContracts.FloorModel.buildContentUri(),projections2,"id=?",result2,null);
        cursor.moveToFirst();
        floorName.setText(cursor.getString(cursor.getColumnIndex(ModelContracts.FloorModel.NAME)));



        cursor=contentResolver.query(ModelContracts.SlotModel.buildContentUri(),ModelContracts.SlotModel.DEFAULT_PROJECTIONS,ModelContracts.SlotModel.buildFloorStateTypeSelection(),ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorID, "FREE","COMMON" ),null);
        freeSlotsCar.setText("Free slots: "+cursor.getCount());

        cursor=contentResolver.query(ModelContracts.SlotModel.buildContentUri(),ModelContracts.SlotModel.DEFAULT_PROJECTIONS,ModelContracts.SlotModel.buildFloorStateTypeSelection(),ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorID, "FREE", "ELECTRIC"),null);
        freeSlotsElectric.setText("Free slots: "+cursor.getCount());

        cursor=contentResolver.query(ModelContracts.SlotModel.buildContentUri(),ModelContracts.SlotModel.DEFAULT_PROJECTIONS,ModelContracts.SlotModel.buildFloorStateTypeSelection(),ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorID, "FREE","MOTORBIKE" ),null);
        freeSlotsMotorbike.setText("Free slots: "+cursor.getCount());

        cursor=contentResolver.query(ModelContracts.SlotModel.buildContentUri(),ModelContracts.SlotModel.DEFAULT_PROJECTIONS,ModelContracts.SlotModel.buildFloorStateTypeSelection(),ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorID, "FREE","DISABLED" ),null);
        freeSlotsDisabled.setText("Free slots: "+cursor.getCount());

        cursor=contentResolver.query(ModelContracts.SlotModel.buildContentUri(),ModelContracts.SlotModel.DEFAULT_PROJECTIONS,ModelContracts.SlotModel.buildFloorStateTypeSelection(),ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorID, "FREE","BICYCLE" ),null);
        freeSlotsBicycle.setText("Free slots: "+cursor.getCount());

        carFloorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FloorInfoActivity.this,SlotInfoActivity.class);
                intent.putExtra("typeSlot","COMMON");
                intent.putExtra("floorID",floorID);
                startActivity(intent);
            }
        });
        electricFloorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FloorInfoActivity.this,SlotInfoActivity.class);
                intent.putExtra("typeSlot","ELECTRIC");
                intent.putExtra("floorID",floorID);
                startActivity(intent);
            }
        });

        motorbikeFloorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FloorInfoActivity.this,SlotInfoActivity.class);
                intent.putExtra("typeSlot","MOTORBIKE");
                intent.putExtra("floorID",floorID);
                startActivity(intent);
            }
        });

        disabledFloorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FloorInfoActivity.this,SlotInfoActivity.class);
                intent.putExtra("typeSlot","DISABLED");
                intent.putExtra("floorID",floorID);
                startActivity(intent);
            }
        });

        bicycleFloorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FloorInfoActivity.this,SlotInfoActivity.class);
                intent.putExtra("typeSlot","BICYCLE");
                intent.putExtra("floorID",floorID);
                startActivity(intent);
            }
        });




    }
}
