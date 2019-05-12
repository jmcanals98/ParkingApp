package com.example.parkingapp;

import java.util.ArrayList;
import java.util.List;

public class Parkings {

    List<Parking> parkings;

    public Parkings() {
        parkings=new ArrayList<>();
    }

    public List<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }

}
