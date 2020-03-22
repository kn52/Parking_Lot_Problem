package com.bridgelabz.parking.lot;

public class Slot {
    private int totalSlot;
    private int slot;
    private Object vehicle;

    public Slot() { }

    public void setSlot(int totalSlot) {
        this.totalSlot=totalSlot;
    }


    public void setVehicleSlot(Object vehicle, int slot) {
        this.vehicle=vehicle;
        this.slot=slot;
    }

    public int getVehicleSlot(Object vehicle) {
        return slot;
    }
}
