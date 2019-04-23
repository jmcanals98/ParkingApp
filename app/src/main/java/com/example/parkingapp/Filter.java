package com.example.parkingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Filter extends AppCompatActivity {

    private Spinner Orderby;
    private Spinner Type;
    private Spinner Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter3);

        Orderby = (Spinner)findViewById(R.id.spOrder);
        Type = (Spinner)findViewById(R.id.spType);
        Price = (Spinner)findViewById(R.id.spPrice);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_order,android.R.layout.simple_spinner_item);
        Orderby.setAdapter(adapter);

        adapter=ArrayAdapter.createFromResource(this,R.array.combo_type,android.R.layout.simple_spinner_item);
        Type.setAdapter(adapter);

        adapter=ArrayAdapter.createFromResource(this,R.array.combo_price,android.R.layout.simple_spinner_item);
        Price.setAdapter(adapter);




    }
}







