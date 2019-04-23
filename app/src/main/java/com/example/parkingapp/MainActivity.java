package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email= (EditText)findViewById(R.id.etEmail);
        Password=(EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.bLogin);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(),Password.getText().toString());
            }
        });

    }
    private void validate (String userEmail, String userPassword)
    {
        if((userEmail.equals("hola@gmail.com")) && (userPassword.equals("1234")))
        {
            Intent intent = new Intent(MainActivity.this,FirstMenu.class);
            startActivity(intent);
        }
        else
        {
           Intent intent = new Intent(MainActivity.this,LogInError.class);
           startActivity(intent);
        }
    }

}