package com.example.parkingapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class SlotInfoActivity extends AppCompatActivity {

    private ImageView imageSlotType;
    private ImageView back;
    private String typeSlot;
    private String floorID;
    private String parkingID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_detailed_info);

        typeSlot = getIntent().getStringExtra("typeSlot");
        floorID = getIntent().getStringExtra("floorID");
        parkingID = getIntent().getStringExtra("parkingID");

        imageSlotType = findViewById(R.id.ivSlotType);
        back=findViewById(R.id.ivBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SlotInfoActivity.this,FloorInfoActivity.class);
                intent.putExtra("parkingCompanyNum",parkingID);
                intent.putExtra("floorID",floorID);
                startActivity(intent);
            }
        });

        switch (typeSlot) {
            case "COMMON":
                imageSlotType.setImageResource(R.drawable.car);
                break;
            case "ELECTRIC":
                imageSlotType.setImageResource(R.drawable.electric);
                break;
            case "MOTORBIKE":
                imageSlotType.setImageResource(R.drawable.motorbike);
                break;

            case "DISABLED":
                imageSlotType.setImageResource(R.drawable.wheelchair);
                break;

            case "BICYCLE":
                imageSlotType.setImageResource(R.drawable.bike);
                break;

        }

        ContentResolver contentResolver = getContentResolver();
        String result[]={floorID,typeSlot};
        Cursor cursor = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), ModelContracts.SlotModel.DEFAULT_PROJECTIONS,"floor_id=? AND slot_type=?", result, null);
        cursor.moveToFirst();

        ListView lvItems = findViewById(R.id.lvDetailedSlotInfo);
        SlotParkingInfoCursorAdapter slotAdapter = new SlotParkingInfoCursorAdapter(this, cursor, 0);
        lvItems.setAdapter(slotAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this,FloorInfoActivity.class);
        intent.putExtra("parkingCompanyNum",parkingID);
        intent.putExtra("floorID",floorID);
        startActivity(intent);
        finish();

    }
}
