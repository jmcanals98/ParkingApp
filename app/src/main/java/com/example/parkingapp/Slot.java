package com.example.parkingapp;

public class Slot {
    int id, company_number;
    String slot_type, slot_color, slot_state, name, state_change_date, floor_id;

    public Slot(int id, int company_number, String slot_type, String slot_color, String slot_state, String name, String state_change_date, String floor_id) {
        this.id = id;
        this.company_number = company_number;
        this.slot_type = slot_type;
        this.slot_color = slot_color;
        this.slot_state = slot_state;
        this.name = name;
        this.state_change_date = state_change_date;
        this.floor_id = floor_id;
    }
}
