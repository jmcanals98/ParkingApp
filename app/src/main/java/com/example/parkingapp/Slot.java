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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_number() {
        return company_number;
    }

    public void setCompany_number(int company_number) {
        this.company_number = company_number;
    }

    public String getSlot_type() {
        return slot_type;
    }

    public void setSlot_type(String slot_type) {
        this.slot_type = slot_type;
    }

    public String getSlot_color() {
        return slot_color;
    }

    public void setSlot_color(String slot_color) {
        this.slot_color = slot_color;
    }

    public String getSlot_state() {
        return slot_state;
    }

    public void setSlot_state(String slot_state) {
        this.slot_state = slot_state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState_change_date() {
        return state_change_date;
    }

    public void setState_change_date(String state_change_date) {
        this.state_change_date = state_change_date;
    }

    public String getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(String floor_id) {
        this.floor_id = floor_id;
    }
}
