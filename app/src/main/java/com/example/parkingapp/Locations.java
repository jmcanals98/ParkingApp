package com.example.parkingapp;

import java.util.ArrayList;
import java.util.List;

public class Locations {
    List<Location> locations;

    public Locations() {
        locations = new ArrayList<>();
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }


}
