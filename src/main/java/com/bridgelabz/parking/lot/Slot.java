package com.bridgelabz.parking.lot;

import java.time.LocalDateTime;

public class Slot {
    private Enum driverType;
    private LocalDateTime parkingTime;
    private String attendantName;
    private int slot;
    private Object vehicle;

    public Slot(Object vehicle) {
        this.vehicle=vehicle;
    }

    public Slot(Object vehicle, Enum driverType, int slot, String attendantName) {
        this.vehicle=vehicle;
        this.driverType = driverType;
        this.slot=slot;
        this.attendantName = attendantName;
        this.parkingTime = LocalDateTime.now();
    }

    public Slot() { }

    public Object getVehicle() {
        return vehicle;
    }

    public int getVehicleSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot=slot;
    }

    public void setVehicleSlot(Object vehicle, int slot, String attendant) {
        this.vehicle=vehicle;
        this.slot=slot;
        this.attendantName =attendant;
    }

    public int getSlot(Object vehicle) {
        if(this.vehicle.equals(vehicle))
            return slot;
        return 0;
    }
}
