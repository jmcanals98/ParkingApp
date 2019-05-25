package com.example.parkingapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.parkingapp.Activities.ChoosePaymentAddActivity;
import com.example.parkingapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class PaymentMethodsFragment extends Fragment {
    private Button addMethodsButton;
    private String param1;
    private String param2;
    private ArrayList<String> paymentItems = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private SharedPreferences paymentID;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_payment_methods, container, false);
        addMethodsButton = view.findViewById(R.id.bAddPaymentMethods);
        ListView lvPaymentMethods = view.findViewById(R.id.lvPaymentItems);
        paymentID = this.getActivity().getSharedPreferences("paymentMethods", Context.MODE_PRIVATE);


        paymentItems = getArrayList("paymentList");
        param1 = paymentID.getString("payment_ID", null);

        if (paymentItems == null) paymentItems = new ArrayList<String>();

        if (param1 != null && paymentItems.size()!=0){
            for (int i = 0; i < paymentItems.size(); i++) {
                if (!paymentItems.contains(param1)) {
                    paymentItems.add(param1);
                    saveArrayList(paymentItems, "paymentList");
                }
            }
        }
        else if(param1!=null){
            paymentItems.add(param1);
            saveArrayList(paymentItems, "paymentList");
        }

        if (paymentItems.size() != 0) {
            adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.item_payment_method_list, R.id.tvPaymentMethodID, paymentItems);
            lvPaymentMethods.setAdapter(adapter);
        }


        addMethodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChoosePaymentAddActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }


    /**
     * Save and get ArrayList in SharedPreference
     */

    public void saveArrayList(ArrayList<String> list, String key) {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("paymentMethods", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<String> getArrayList(String key) {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("paymentMethods", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
