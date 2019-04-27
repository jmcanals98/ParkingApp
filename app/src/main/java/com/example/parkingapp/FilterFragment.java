package com.example.parkingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class FilterFragment extends Fragment {
    private Spinner Orderby;
    private Spinner Type;
    private Spinner Price;

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.filter_dialog,container,false);
        Orderby = (Spinner)view.findViewById(R.id.spOrder);
        Type = (Spinner)view.findViewById(R.id.spType);
        Price = (Spinner)view.findViewById(R.id.spPrice);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.combo_order,android.R.layout.simple_spinner_item);
        Orderby.setAdapter(adapter);

        adapter=ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.combo_type,android.R.layout.simple_spinner_item);
        Type.setAdapter(adapter);

        adapter=ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.combo_price,android.R.layout.simple_spinner_item);
        Price.setAdapter(adapter);

        return view;
    }
}
