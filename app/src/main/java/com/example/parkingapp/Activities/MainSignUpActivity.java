package com.example.parkingapp.Activities;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.parkingapp.R;

public class MainSignUpActivity extends Activity {

    private EditText email;
    private EditText password;
    private EditText repeatPassword;
    private EditText location;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

        email= (EditText)findViewById(R.id.etEmail);
        password=(EditText)findViewById(R.id.etPass);
        repeatPassword = (EditText) findViewById(R.id.etRepeat);
        location = (EditText) findViewById(R.id.etLocation);
        submit = (Button)findViewById(R.id.bSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateSign(email.getText().toString(),password.getText().toString(),repeatPassword.getText().toString(),location.getText().toString());
            }
        });
    }

    private void validateSign(String userEmail,String userPassword,String userRepeatPassword,String userLocation)
    {
        if((!userEmail.isEmpty()) && (userPassword.equals(userRepeatPassword)) && (!userLocation.isEmpty()))
        {
            Intent intent = new Intent(MainSignUpActivity.this, NavigationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
