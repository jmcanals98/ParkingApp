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

    private ImageView back;
    private ImageView credit;
    private ImageView paypal;
    private TextView choosePaypal;
    private TextView chooseCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navmenu_choosepayment);

        back = (ImageView)findViewById(R.id.ivBack);
        credit = (ImageView) findViewById(R.id.ivCredit);
        paypal = (ImageView) findViewById(R.id.ivPaypal);
        chooseCredit = (TextView) findViewById(R.id.tvCreditCar);
        choosePaypal=(TextView) findViewById(R.id.tvPaypal);
/*
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosePaymentAddActivity.this, PaymentMethodsFragment.class));
            }
        });
*/
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ChoosePaymentAddActivity.this,AddCreditCardActivity.class);
                intent.putExtra("text",chooseCredit.getText().toString());
                startActivity(intent);
            }
        });

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePaymentAddActivity.this,AddPaypalActivity.class);
                intent.putExtra("text",choosePaypal.getText().toString());
                startActivity(intent);
            }
        });
    }
}
