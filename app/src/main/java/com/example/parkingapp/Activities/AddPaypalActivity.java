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

public class AddPaypalActivity extends AppCompatActivity {

    private ImageView back;
    private  ImageView save;
    private EditText name;
    private EditText password;
    private String choosePaypal;
    private SharedPreferences paymentPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paypal);

        choosePaypal=getIntent().getStringExtra("text");
        back = (ImageView)findViewById(R.id.ivBack);
        save = (ImageView)findViewById(R.id.ivSave1);
        name = (EditText) findViewById(R.id.etName);
        password = (EditText) findViewById(R.id.etPassword3);

/*
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPaypalActivity.this, ChoosePaymentAddActivity.class));
            }
        });
*/
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePaypal(name.getText().toString(),password.getText().toString());
            }
        });
    }

    private void validatePaypal(String userName,String userPassword)
    {
        if((!userName.isEmpty()) && (!userPassword.isEmpty()))
        {
            paymentPreferences= getSharedPreferences("paymentMethods", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor=paymentPreferences.edit();
            editor.putString("payment_ID",userName);
            editor.commit();

            // set MyFragment Arguments
            Intent intent = new Intent(getBaseContext(), NavigationActivity.class);
            startActivity(intent);
        }
    }
}
