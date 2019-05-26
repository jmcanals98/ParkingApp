package com.example.parkingapp.Activities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parkingapp.R;
import com.example.parkingapp.VariableClasses.Floors;
import com.example.parkingapp.VariableClasses.Locations;
import com.example.parkingapp.VariableClasses.Parkings;
import com.example.parkingapp.VariableClasses.Slots;
import com.google.gson.GsonBuilder;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;

public class MainActivity extends AppCompatActivity implements IDataReceiver {

    private EditText email;
    private EditText password;
    private Button login;
    private TextView signup;
    private Locations locations;

    StringResponseListener stringListener = new StringResponseListener(this);
    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.etEmail);
        password = (EditText) findViewById(R.id.etPass);
        login = (Button) findViewById(R.id.bLogin);
        signup = (TextView) findViewById(R.id.tvSign);

        CommManager.initializeQueu(this);
        if (!CommManager.callRequest(AppURL.LOCATIOM_URL, stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(), password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainSignUpActivity.class));
            }
        });

    }


    private void validate(String userEmail, String userPassword) {
       // if ((userEmail.equals("hola@gmail.com")) && (userPassword.equals("1234"))) {
            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(intent);
        //} else {
        //    Intent intent = new Intent(MainActivity.this, LoginErrorActivity.class);
        //    startActivity(intent);
    //    }
    }

    @Override
    public void onReceiveData(String s) {
        if (s != null) {
            if (s.length() > 0) {
                Log.d(TAG, s);
                downloadLocations(s);
            }

        } else {
            Toast.makeText(this, "Data NOT received", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "No data to show");
        }

        createBaseData();

    }


    private void downloadLocations(String data) {
        String parseString = "{\"locations\":" + data + "}";
        GsonBuilder gson = new GsonBuilder();
        locations = gson.create().fromJson(parseString, Locations.class);
    }

    protected void createBaseData() {
        ContentResolver contentResolver = this.getContentResolver();

        ContentValues cv = new ContentValues();
        for(int i=0; i<locations.getLocations().size(); i++){
            cv.put(ModelContracts.LocationContract.ID,locations.getLocations().get(i).getId());
            cv.put(ModelContracts.LocationContract.NAME,locations.getLocations().get(i).getCity());
            cv.put(ModelContracts.LocationContract.POSTAL_CODE,locations.getLocations().get(i).getPostal_code());
            cv.put(ModelContracts.LocationContract.STREET_ADDRESS, locations.getLocations().get(i).getStreet_address());
            cv.put(ModelContracts.LocationContract.STATE_PROVINCE, locations.getLocations().get(i).getState_province());
            cv.put(ModelContracts.LocationContract.LATITUDE, locations.getLocations().get(i).getLatitude());
            cv.put(ModelContracts.LocationContract.LONGITUDE, locations.getLocations().get(i).getLongitude());

            String selection = ModelContracts.LocationModel.buildIdSelection();
            String selectionArgs[] = ModelContracts.LocationModel.buildIdSelectionArgs(locations.getLocations().get(i).getId());

            int numRows = getContentResolver().update(ModelContracts.LocationModel.buildContentUri(),cv,selection,selectionArgs);

            if (numRows <1 ) {

                Uri insertUri = contentResolver.insert(ModelContracts.LocationModel.buildContentUri(), cv);
                Log.d(TAG, String.format("Location inserted DB: %s", insertUri.toString()));
            }else{
                Log.d(TAG, String.format("Location updated"));
            }

        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
