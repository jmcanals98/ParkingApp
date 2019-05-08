package com.example.parkingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;

public class MainActivity extends AppCompatActivity implements IDataReceiver {

    private EditText Email;
    private EditText Password;
    private Button Login;
    private TextView Signup;

    StringResponseListener stringListener = new StringResponseListener(this);
    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPass);
        Login = (Button) findViewById(R.id.bLogin);
        Signup = (TextView) findViewById(R.id.tvSign);

        CommManager.initializeQueu(this);
        if (!CommManager.callRequest("https://django-parking-server.herokuapp.com/endpoints/api/floor/1/", stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainSignUpActivity.class));
            }
        });

    }


    private void validate(String userEmail, String userPassword) {
        if ((userEmail.equals("hola@gmail.com")) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, LoginErrorActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onReceiveData(String s) {
        if (s != null) {
            if (s.length() > 0) {
                Toast.makeText(this, "Data received", Toast.LENGTH_SHORT).show();
                Log.d(TAG, s);
            }

        } else {
            Toast.makeText(this, "Data NOT received", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "No data to show");
        }
        /*String parseString = "{\"locations\":"+s+"}";

        Locations locations = gson.create().fromJson(parseString,Locations.class) ;
        */

        /*String parseString = "{\"slots\":"+s+"}";
        GsonBuilder gson= new GsonBuilder();
        Slots slots = gson.create().fromJson(parseString,Slots.class) ;
        */

        String parseString = "{\"floor\":"+s+"}";
        GsonBuilder gson= new GsonBuilder();
        Floor floor = gson.create().fromJson(s,Floor.class) ;


        if (s != null) {
            if (s.length() > 0) {
                Toast.makeText(this, "Data received", Toast.LENGTH_SHORT).show();
                Log.d(TAG, s);
            }
        }

    }
}
