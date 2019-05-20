package com.example.parkingapp;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.GsonBuilder;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;

import static android.content.ContentValues.TAG;


public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback, IDataReceiver {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Locations locations;
    private Parkings parkings;
    StringResponseListener stringListener = new StringResponseListener(this);
    private static final String TAG2 = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        getMapAsync(this);

        CommManager.initializeQueu(this.getContext());
        if (!CommManager.callRequest(AppURL.PARKING_URL, stringListener))
            Toast.makeText(this.getContext(), "Call error", Toast.LENGTH_SHORT).show();

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = map.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            getActivity(), R.raw.mapstyle));

            if (!success) {
                Log.e("FirstMenu", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Content", "Can't find style. Error: ", e);
        }
        // Posicionar el mapa en una localización y con un nivel de zoom
        LatLng tgn = new LatLng(41.118660, 1.245330);

        // Un zoom mayor que 13 hace que el emulador falle, pero un valor deseado para
        // callejero es 17 aprox.
        float zoom = 13;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(tgn, zoom));

        // Colocar markers en el mapa.p
        queryBaseData(map);

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            map.setMyLocationEnabled(true);
        }
    }

    protected void queryBaseData(GoogleMap map)
    {
        ContentResolver contentResolver = getActivity().getContentResolver();


        String defaultOrder = ModelContracts.LocationModel.DEFAULT_SORT;
        String projections[] = ModelContracts.LocationModel.DEFAULT_PROJECTIONS;
        //String selection = ModelContracts.SlotModel.buildFloorStateTypeSelection();
        //String selectionArgs[] = ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(null,"FREE",null);

        Cursor cursor = contentResolver.query(ModelContracts.LocationModel.buildContentUri(),projections,null,null,defaultOrder);
        int numElements = cursor.getCount();

        cursor.moveToFirst();
        for (int i=0;i<numElements;i++){
            String lat  = cursor.getString(cursor.getColumnIndex(ModelContracts.LocationModel.LATITUDE));
            String lon  = cursor.getString(cursor.getColumnIndex(ModelContracts.LocationModel.LONGITUDE));


            LatLng marker = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
            map.addMarker(new MarkerOptions().position(marker).title(cursor.getString(cursor.getColumnIndex(ModelContracts.LocationModel.NAME))));
            cursor.moveToNext();
        }

        Log.d(TAG,String.format("Number of elements obtained with query: %d", numElements));
    }


    @Override
    public void onReceiveData(String s) {
        if (s != null) {
            if (s.length() > 0) {
                Toast.makeText(this.getContext(), "Data received", Toast.LENGTH_SHORT).show();
                Log.d(TAG, s);
                downloadParkings(s);
            }

        } else {
            Toast.makeText(this.getContext(), "Data NOT received", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "No data to show");
        }

        createBaseData();
    }

    private void downloadParkings(String data) {
        String parseString = "{\"parkings\":" + data + "}";
        GsonBuilder gson = new GsonBuilder();
        parkings = gson.create().fromJson(parseString, Parkings.class);
    }
    protected void createBaseData() {
        ContentResolver contentResolver = getActivity().getContentResolver();

        ContentValues cv = new ContentValues();
        for (int i = 0; i < parkings.getParkings().size(); i++) {
            cv.put(ModelContracts.ParkingContract.ID, parkings.getParkings().get(i).getId());
            cv.put(ModelContracts.ParkingContract.NAME, parkings.getParkings().get(i).getName());
            cv.put(ModelContracts.ParkingContract.COMPANY_NUMBER, parkings.getParkings().get(i).getCompany_number());
            cv.put(ModelContracts.ParkingContract.LOCATION_ID, parkings.getParkings().get(i).getLocation().getId());

            String selection = ModelContracts.ParkingModel.buildIdSelection();
            String selectionArgs[] = ModelContracts.ParkingModel.buildIdSelectionArgs(parkings.getParkings().get(i).getId());

            int numRows = getActivity().getContentResolver().update(ModelContracts.ParkingModel.buildContentUri(),cv,selection,selectionArgs);

            if (numRows <1 ) {
                Uri insertUri = contentResolver.insert(ModelContracts.ParkingModel.buildContentUri(), cv);
                Log.d(TAG, String.format("Parking inserted DB: %s", insertUri.toString()));

            }else{
                Log.d(TAG, String.format("Parking updated"));
            }

            ContentResolver contentResolver2 = getActivity().getContentResolver();
            ContentValues cv2 = new ContentValues();
            for (int j = 0; j < parkings.getParkings().get(i).getFloors().size(); j++) {
                cv2.put(ModelContracts.FloorContract.ID, parkings.getParkings().get(i).getFloors().get(j).getId());
                cv2.put(ModelContracts.FloorContract.COMPANY_NUMBER, parkings.getParkings().get(i).getFloors().get(j).getCompany_number());
                cv2.put(ModelContracts.FloorContract.NAME, parkings.getParkings().get(i).getFloors().get(j).getName());
                cv2.put(ModelContracts.FloorContract.PARKING_ID, parkings.getParkings().get(i).getCompany_number());


                selection = ModelContracts.FloorModel.buildDefaultSelection();
                String selectionArgs2[] = ModelContracts.FloorModel.buildIdSelectionArgs(parkings.getParkings().get(i).getFloors().get(j).getCompany_number());

                numRows = getActivity().getContentResolver().update(ModelContracts.FloorModel.buildContentUri(),cv2,selection,selectionArgs2);

                if (numRows <1 ) {
                    Uri insertUri2 = contentResolver2.insert(ModelContracts.FloorModel.buildContentUri(), cv2);
                    Log.d(TAG, String.format("Floor inserted DB: %s", insertUri2.toString()));

                }else{
                    Log.d(TAG, String.format("Floor updated"));
                }


                ContentResolver contentResolver3 = getActivity().getContentResolver();
                ContentValues cv3 = new ContentValues();
                for (int k = 0; k < parkings.getParkings().get(i).getFloors().get(j).getSlots().size(); k++) {
                    cv3.put(ModelContracts.SlotContract.ID, parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getId());
                    cv3.put(ModelContracts.SlotContract.COMPANY_NUMBER, parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getCompany_number());
                    cv3.put(ModelContracts.SlotContract.FLOOR_ID, parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getFloor_id());
                    cv3.put(ModelContracts.SlotContract.NAME, parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getName());
                    cv3.put(ModelContracts.SlotContract.SLOT_TYPE, parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getSlot_type());
                    cv3.put(ModelContracts.SlotContract.SLOT_COLOR, parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getSlot_color());
                    cv3.put(ModelContracts.SlotContract.SLOT_STATE, parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getSlot_state());
                    cv3.put(ModelContracts.SlotContract.STATE_CHANGE_DATE, parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getState_change_date());

                    selection = ModelContracts.SlotModel.buildDefaultSelection();
                    String selectionArgs3[] = ModelContracts.SlotModel.buildDefaultSelectionArgs(parkings.getParkings().get(i).getFloors().get(j).getSlots().get(k).getCompany_number());

                    numRows = getActivity().getContentResolver().update(ModelContracts.SlotModel.buildContentUri(),cv3,selection,selectionArgs3);

                    if (numRows <1 ) {
                        Uri insertUri3 = contentResolver3.insert(ModelContracts.SlotModel.buildContentUri(), cv3);
                        Log.d(TAG, String.format("Slot inserted DB: %s", insertUri3.toString()));

                    }else{
                        Log.d(TAG, String.format("Slot updated"));
                    }

                }

            }

        }


    }
}

