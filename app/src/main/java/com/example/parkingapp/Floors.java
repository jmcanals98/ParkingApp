package com.example.parkingapp;

import java.util.ArrayList;
import java.util.List;

public class Floors {
    List<Floor> floors;

    public Floors() {
        floors=new ArrayList<>();
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}
