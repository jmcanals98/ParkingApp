package com.example.parkingapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
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

        String name =cursor.getString(cursor.getColumnIndexOrThrow(ModelContracts.ParkingModel.NAME));
        String cn =cursor.getString(cursor.getColumnIndexOrThrow(ModelContracts.ParkingModel.COMPANY_NUMBER));
        view.setTag(cn);

        tvParkingName.setText(name);
    }
}
