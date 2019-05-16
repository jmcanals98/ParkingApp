package com.example.parkingapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class SlotInfoActivity extends AppCompatActivity {

    private ImageView imageSlotType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_detailed_info);

        String typeSlot = getIntent().getStringExtra("typeSlot");
        String floorID = getIntent().getStringExtra("floorID");

        imageSlotType = findViewById(R.id.ivSlotType);

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
}
