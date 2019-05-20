package com.example.parkingapp.VariableClasses;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getCity() {
        return city;
    }

    public String getState_province() {
        return state_province;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
