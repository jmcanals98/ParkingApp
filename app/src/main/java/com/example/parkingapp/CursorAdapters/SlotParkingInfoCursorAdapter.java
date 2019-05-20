package com.example.parkingapp.CursorAdapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.parkingapp.R;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class SlotParkingInfoCursorAdapter extends CursorAdapter {

    private TextView slotName;
    private TextView slotColor;
    private TextView slotState;

    public SlotParkingInfoCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_slot_detailed_info, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        slotName=view.findViewById(R.id.tvSlotName1);
        slotColor=view.findViewById(R.id.tvSlotColor);
        slotState=view.findViewById(R.id.tvSlotState);

        slotName.setText(cursor.getString(cursor.getColumnIndex(ModelContracts.SlotModel.NAME)));
        slotColor.setText(cursor.getString(cursor.getColumnIndex(ModelContracts.SlotModel.SLOT_COLOR)));
        slotState.setText(cursor.getString(cursor.getColumnIndex(ModelContracts.SlotModel.SLOT_STATE)));



    }
}
