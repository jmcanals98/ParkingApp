package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCreditCardActivity extends AppCompatActivity {

    private ImageView Back;
    private  ImageView Save;
    private EditText CardHolder;
    private EditText CardNumber;
    private EditText YearMonth;
    private EditText CVV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card);
        String chooseCreditCar=getIntent().getStringExtra("text");
        Back = (ImageView)findViewById(R.id.ivBack);
        Save = (ImageView)findViewById(R.id.ivSave);
        CardHolder = (EditText) findViewById(R.id.etCardHolder);
        CardNumber = (EditText) findViewById(R.id.etNumber);
        YearMonth = (EditText) findViewById(R.id.etYearMonth);
        CVV = (EditText) findViewById(R.id.etCVV);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddCreditCardActivity.this,navmenu_choosepayment.class));
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCredit(CardHolder.getText().toString(),CardNumber.getText().toString(),YearMonth.getText().toString(),CVV.getText().toString());
            }
        });

    }
    private void validateCredit(String userCardHolder,String userCardNumber,String userYearMonth,String userCVV)
    {
        if((!userCardHolder.isEmpty()) && (!userCardNumber.isEmpty()) && (!userYearMonth.isEmpty()) && (!userCVV.isEmpty()))
        {
            Intent intent = new Intent(AddCreditCardActivity.this, PaymentMethodsFragment.class);
            startActivity(intent);
        }
    }
}
