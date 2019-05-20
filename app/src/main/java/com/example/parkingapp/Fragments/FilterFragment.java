package com.example.parkingapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.parkingapp.R;


public class FilterFragment extends Fragment {
    private Spinner orderby;
    private Spinner type;
    private Spinner price;

    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.filter_dialog,container,false);
        orderby = (Spinner)view.findViewById(R.id.spOrder);
        type = (Spinner)view.findViewById(R.id.spType);
        price = (Spinner)view.findViewById(R.id.spPrice);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.combo_order,android.R.layout.simple_spinner_item);
        orderby.setAdapter(adapter);

        adapter=ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.combo_type,android.R.layout.simple_spinner_item);
        type.setAdapter(adapter);

        adapter=ArrayAdapter.createFromResource(getActivity().getApplicationContext(),R.array.combo_price,android.R.layout.simple_spinner_item);
        price.setAdapter(adapter);

        return view;
    }
}
