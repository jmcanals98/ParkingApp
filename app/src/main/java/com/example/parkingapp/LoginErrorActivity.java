package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginErrorActivity extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_error);

        Email= (EditText)findViewById(R.id.etEmail);
        Password=(EditText)findViewById(R.id.etPass);
        Login = (Button)findViewById(R.id.bLogin);
        Signup = (TextView)findViewById(R.id.tvSign);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(),Password.getText().toString());
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginErrorActivity.this, MainSignUpActivity.class));
            }
        });

    }
    private void validate (String userEmail, String userPassword)
    {
        if((userEmail.equals("hola@gmail.com")) && (userPassword.equals("1234")))
        {
            Intent intent = new Intent(LoginErrorActivity.this, NavigationActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(LoginErrorActivity.this, LoginErrorActivity.class);
            startActivity(intent);
        }
    }
    }
