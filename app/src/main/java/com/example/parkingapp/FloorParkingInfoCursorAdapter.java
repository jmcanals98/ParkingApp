package com.example.parkingapp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class FloorParkingInfoCursorAdapter extends CursorAdapter {

    public FloorParkingInfoCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_floor_parking_info, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView emptySlots = (TextView) view.findViewById(R.id.tvFreeSlotsParkingInfo);
        ImageView floorNumber = (ImageView) view.findViewById(R.id.ivFloorNumParkingInfo);


        ContentResolver contentResolver = context.getContentResolver();
        String locID=cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.LOCATION_ID));
        Cursor cursor2 = contentResolver.query(ModelContracts.LocationModel.buildContentUri(), ModelContracts.LocationModel.DEFAULT_PROJECTIONS,ModelContracts.LocationModel.buildIdSelection(), ModelContracts.LocationModel.buildIdSelectionArgs(Integer.parseInt(locID)), ModelContracts.LocationModel.DEFAULT_SORT);
        cursor2.moveToFirst();


        emptySlots.setText("prueba");
        floorNumber.setImageResource(R.drawable.number1);
    }
}
