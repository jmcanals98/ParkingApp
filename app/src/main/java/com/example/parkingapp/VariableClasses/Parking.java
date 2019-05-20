package com.example.parkingapp.VariableClasses;

import com.example.parkingapp.VariableClasses.Floor;
import com.example.parkingapp.VariableClasses.Location;

import java.util.List;

public class Parking {
    int id;
    String name;
    int company_number;

    List<Floor> floors;
    Location location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompany_number() {
        return company_number;
    }

    public void setCompany_number(int company_number) {
        this.company_number = company_number;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
