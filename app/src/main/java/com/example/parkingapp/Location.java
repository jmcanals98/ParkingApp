package com.example.parkingapp;

public class Location {
    int id, postal_code;
    String street_address, city, state_province, latitude, longitude;

    public Location(int id, int postal_code, String street_adress, String city, String state_province, String latitude, String longitude) {
        this.id = id;
        this.postal_code = postal_code;
        this.street_address = street_adress;
        this.city = city;
        this.state_province = state_province;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
