package com.example.parkingapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.parkingapp.R;

public class AddCreditCardActivity extends AppCompatActivity {


    private ImageView save;
    private EditText cardHolder;
    private EditText cardNumber;
    private EditText yearMonth;
    private EditText cvv;

    private SharedPreferences paymentPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card);

        save = (ImageView) findViewById(R.id.ivSave);
        cardHolder = (EditText) findViewById(R.id.etCardHolder);
        cardNumber = (EditText) findViewById(R.id.etNumber);
        yearMonth = (EditText) findViewById(R.id.etYearMonth);
        cvv = (EditText) findViewById(R.id.etCVV);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCredit(cardHolder.getText().toString(), cardNumber.getText().toString(), yearMonth.getText().toString(), cvv.getText().toString());
            }
        });

    }

    private void validateCredit(String userCardHolder, String userCardNumber, String userYearMonth, String userCVV) {
        if ((!userCardHolder.isEmpty()) && (!userCardNumber.isEmpty()) && (!userYearMonth.isEmpty()) && (!userCVV.isEmpty())) {
            paymentPreferences = getSharedPreferences("paymentMethods", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = paymentPreferences.edit();
            editor.putString("payment_ID", userCardNumber);
            editor.commit();

            Intent intent = new Intent(getBaseContext(), NavigationActivity.class);
            intent.putExtra("saveClick",true);
            startActivity(intent);

        }
    }
}
