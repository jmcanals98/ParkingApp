package com.example.parkingapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parkingapp.Fragments.PaymentMethodsFragment;
import com.example.parkingapp.R;

public class ChoosePaymentAddActivity extends AppCompatActivity {


    private ImageView credit;
    private ImageView paypal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navmenu_choosepayment);


        credit = (ImageView) findViewById(R.id.ivCredit);
        paypal = (ImageView) findViewById(R.id.ivPaypal);



        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoosePaymentAddActivity.this,AddCreditCardActivity.class);
                startActivity(intent);
            }
        });

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePaymentAddActivity.this,AddPaypalActivity.class);
                startActivity(intent);
            }
        });
    }
}
