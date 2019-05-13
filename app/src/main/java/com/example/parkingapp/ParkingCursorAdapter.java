package com.example.parkingapp;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class ParkingCursorAdapter extends CursorAdapter {

    public ParkingCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_parking_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvParkingName = (TextView) view.findViewById(R.id.tvParkingName);
        TextView parkingAddress =(TextView) view.findViewById(R.id.tvAddressParkingList);
        ImageView parkingPhoto=(ImageView)view.findViewById(R.id.ivParkingListPhoto);

        String name =cursor.getString(cursor.getColumnIndexOrThrow(ModelContracts.ParkingModel.NAME));
        switch (name){
            case "Parking Catalunya":
                parkingPhoto.setImageResource(R.drawable.parkingcatalunya);
                break;
            case "Parking Sescelades":
                parkingPhoto.setImageResource(R.drawable.parkingsescelades);
                break;
            case "Parking Reus FEE":
                parkingPhoto.setImageResource(R.drawable.parkingreus);
                break;
        }

        ContentResolver contentResolver = context.getContentResolver();
        String locID=cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.LOCATION_ID));
        Cursor cursor2 = contentResolver.query(ModelContracts.LocationModel.buildContentUri(), ModelContracts.LocationModel.DEFAULT_PROJECTIONS,ModelContracts.LocationModel.buildIdSelection(), ModelContracts.LocationModel.buildIdSelectionArgs(Integer.parseInt(locID)), ModelContracts.LocationModel.DEFAULT_SORT);
        cursor2.moveToFirst();
        String streetString = "C/ "+cursor2.getString(cursor2.getColumnIndex(ModelContracts.LocationModel.STREET_ADDRESS));

        parkingAddress.setText(streetString);

        String cn =cursor.getString(cursor.getColumnIndexOrThrow(ModelContracts.ParkingModel.COMPANY_NUMBER));

        view.setTag(cn);

        tvParkingName.setText(name);
    }

}
