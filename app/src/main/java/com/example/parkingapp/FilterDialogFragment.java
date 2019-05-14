package com.example.parkingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FilterDialogFragment extends AppCompatDialogFragment {
    private Spinner Orderby;
    private Spinner Type;
    private Spinner Price;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.filter_dialog, null);
        Orderby = (Spinner) view.findViewById(R.id.spOrder);
        Type = (Spinner) view.findViewById(R.id.spType);
        Price = (Spinner) view.findViewById(R.id.spPrice);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.combo_order, android.R.layout.simple_spinner_item);
        Orderby.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.combo_type, android.R.layout.simple_spinner_item);
        Type.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.combo_price, android.R.layout.simple_spinner_item);
        Price.setAdapter(adapter);



        builder.setView(view)
                .setTitle("Do your filter")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            String type = Type.getSelectedItem().toString();
                            listener.applyTexts(type);
                    }
                });
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyTexts(String type);
    }
}
