package com.example.parkingapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.parkingapp.R;

public class LoginErrorActivity extends AppCompatActivity {


    private EditText email;
    private EditText password;
    private Button login;
    private TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_error);

        email= (EditText)findViewById(R.id.etEmail);
        password=(EditText)findViewById(R.id.etPass);
        login = (Button)findViewById(R.id.bLogin);
        signup = (TextView)findViewById(R.id.tvSign);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(),password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
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

