package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class navmenu_choosepayment extends AppCompatActivity {

    private ImageView Back;
    private ImageView Credit;
    private ImageView Paypal;
    private TextView choosePaypal;
    private TextView chooseCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navmenu_choosepayment);

        Back = (ImageView)findViewById(R.id.ivBack);
        Credit = (ImageView) findViewById(R.id.ivCredit);
        Paypal = (ImageView) findViewById(R.id.ivPaypal);
        chooseCredit = (TextView) findViewById(R.id.tvCreditCar);
        choosePaypal=(TextView) findViewById(R.id.tvPaypal);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(navmenu_choosepayment.this,FirstMenu.class));
            }
        });

        Credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(navmenu_choosepayment.this,AddCreditCardActivity.class);
                intent.putExtra("text",chooseCredit.getText().toString());
                startActivity(intent);
            }
        });

        Paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(navmenu_choosepayment.this,AddPaypalActivity.class);
                intent.putExtra("text",choosePaypal.getText().toString());
                startActivity(intent);
            }
        });
    }
}
