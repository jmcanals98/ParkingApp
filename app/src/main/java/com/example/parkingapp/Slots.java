package com.example.parkingapp;

import java.util.ArrayList;
import java.util.List;

public class Slots {

    List<Floor> slots;

    public Slots() {
        slots=new ArrayList<>();
    }

    public List<Floor> getSlots() {
        return slots;
    }

    public void setSlots(List<Floor> slots) {
        this.slots = slots;
    }
}
