package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddPaypalActivity extends AppCompatActivity {

    private ImageView back;
    private  ImageView save;
    private EditText name;
    private EditText password;
    private String choosePaypal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paypal);

        choosePaypal=getIntent().getStringExtra("text");
        back = (ImageView)findViewById(R.id.ivBack);
        save = (ImageView)findViewById(R.id.ivSave1);
        name = (EditText) findViewById(R.id.etName);
        password = (EditText) findViewById(R.id.etPassword3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddPaypalActivity.this,navmenu_choosepayment.class));
            }
        });

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
            // set MyFragment Arguments
            Intent intent = new Intent(getBaseContext(), NavigationActivity.class);
            intent.putExtra("payment method",choosePaypal);
            intent.putExtra("id",userName);
            startActivity(intent);
        }
    }
}
