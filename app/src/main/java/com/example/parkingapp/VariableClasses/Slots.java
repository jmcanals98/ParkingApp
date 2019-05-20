package com.example.parkingapp.VariableClasses;

import com.example.parkingapp.VariableClasses.Slot;

import java.util.ArrayList;
import java.util.List;

public class Slots {

    List<Slot> slots;

    public Slots() {
        slots=new ArrayList<>();
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
