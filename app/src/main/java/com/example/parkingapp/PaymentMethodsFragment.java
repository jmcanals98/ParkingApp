package com.example.parkingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;



public class PaymentMethodsFragment extends Fragment {
    ImageView deleteImage;
    private Button addMethods;
    Activity contex;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_payment_methods, container, false);
        //deleteImage = (ImageView) view.findViewById(R.id.deleteButton);
        addMethods=(Button) view.findViewById(R.id.bAddPaymentMethods);
        /*deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setMessage("Sure you want to delete?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"OK", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"OK", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

            }
        });
        */

        addMethods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),navmenu_choosepayment.class);
                startActivity(intent);
            }
        });


        return view;


    }


}
