package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class add_paypal extends AppCompatActivity {

    private ImageView Back;
    private  ImageView Save;
    private EditText Name;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paypal);

        Back = (ImageView)findViewById(R.id.ivBack);
        Save = (ImageView)findViewById(R.id.ivSave1);
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword3);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(add_paypal.this,navmenu_choosepayment.class));
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatePaypal(Name.getText().toString(),Password.getText().toString());
            }
        });
    }

    private void validatePaypal(String userName,String userPassword)
    {
        if((!userName.isEmpty()) && (!userPassword.isEmpty()))
        {
            Intent intent = new Intent(add_paypal.this,navigation.class);
            startActivity(intent);
        }
    }
}
