package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class navmenu_choosepayment extends AppCompatActivity {

    private ImageView Back;
    private ImageView Credit;
    private ImageView Paypal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navmenu_choosepayment);

        Back = (ImageView)findViewById(R.id.ivBack);
        Credit = (ImageView) findViewById(R.id.ivCredit);
        Paypal = (ImageView) findViewById(R.id.ivPaypal);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(navmenu_choosepayment.this,FirstMenu.class));
            }
        });

        Credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(navmenu_choosepayment.this, AddCreditCardActivity.class));
            }
        });

        Paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(navmenu_choosepayment.this, AddPaypalActivity.class));
            }
        });
    }
}
