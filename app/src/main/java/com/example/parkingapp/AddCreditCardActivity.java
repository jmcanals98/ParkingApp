package com.example.parkingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCreditCardActivity extends AppCompatActivity {

    private ImageView Back;
    private  ImageView Save;
    private EditText cardHolder;
    private EditText cardNumber;
    private EditText yearMonth;
    private EditText cvv;
    private String chooseCreditCar;
    private SharedPreferences paymentPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card);
        chooseCreditCar=getIntent().getStringExtra("text");
        Back = (ImageView)findViewById(R.id.ivBack);
        Save = (ImageView)findViewById(R.id.ivSave);
        cardHolder = (EditText) findViewById(R.id.etCardHolder);
        cardNumber = (EditText) findViewById(R.id.etNumber);
        yearMonth = (EditText) findViewById(R.id.etYearMonth);
        cvv = (EditText) findViewById(R.id.etCVV);




        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCreditCardActivity.this,navmenu_choosepayment.class));
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCredit(cardHolder.getText().toString(),cardNumber.getText().toString(),yearMonth.getText().toString(),cvv.getText().toString());
            }
        });

    }
    private void validateCredit(String userCardHolder,String userCardNumber,String userYearMonth,String userCVV)
    {
        if((!userCardHolder.isEmpty()) && (!userCardNumber.isEmpty()) && (!userYearMonth.isEmpty()) && (!userCVV.isEmpty()))
        {
            paymentPreferences= getSharedPreferences("paymentMethods", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor=paymentPreferences.edit();
            editor.putString("payment_ID",userCardNumber);
            editor.commit();

            Intent intent = new Intent(getBaseContext(), NavigationActivity.class);
            startActivity(intent);

        }
    }
}
