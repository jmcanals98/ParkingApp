package com.example.parkingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class PaymentMethodsFragment extends Fragment {
    private Button addMethodsButton;
    private String param1;
    private String param2;
    private ArrayList<String> paymentItems=new ArrayList<String>();
    private ArrayAdapter<String> adapter;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_payment_methods, container, false);
        addMethodsButton = view.findViewById(R.id.bAddPaymentMethods);
        ListView lvPaymentMethods = view.findViewById(R.id.lvPaymentItems);


        if (getArguments() != null) {
            param1 = getArguments().getString("payment method");
            param2 = getArguments().getString("id");
            if (param2 != null) {
                paymentItems.add(param2);
            }
        }

        if(paymentItems.size()!=0)
        adapter=new ArrayAdapter<String>(this.getActivity(),R.layout.item_payment_method_list,R.id.tvPaymentMethodID,paymentItems);
        lvPaymentMethods.setAdapter(adapter);

        addMethodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), navmenu_choosepayment.class);
                startActivity(intent);
            }
        });

        return view;

    }


}
