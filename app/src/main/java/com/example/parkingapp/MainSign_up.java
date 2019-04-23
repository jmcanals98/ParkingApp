package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainSign_up extends Activity {

    private EditText Email;
    private EditText Password;
    private EditText RepeatPassword;
    private EditText Location;
    private Button Submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

        Email= (EditText)findViewById(R.id.etEmail);
        Password=(EditText)findViewById(R.id.etPass);
        RepeatPassword = (EditText) findViewById(R.id.etRepeat);
        Location = (EditText) findViewById(R.id.etLocation);
        Submit = (Button)findViewById(R.id.bSubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateSign(Email.getText().toString(),Password.getText().toString(),RepeatPassword.getText().toString(),Location.getText().toString());
            }
        });
    }

    private void validateSign(String userEmail,String userPassword,String userRepeatPassword,String userLocation)
    {
        if((!userEmail.isEmpty()) && (userPassword.equals(userRepeatPassword)) && (!userLocation.isEmpty()))
        {
            Intent intent = new Intent(MainSign_up.this,FirstMenu.class);
            startActivity(intent);
        }
    }

}
