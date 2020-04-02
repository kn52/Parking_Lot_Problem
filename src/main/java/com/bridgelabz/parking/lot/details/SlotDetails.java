package com.bridgelabz.parking.lot.details;

import com.bridgelabz.parking.lot.vehicle.Vehicle;

import java.time.LocalDateTime;

public class SlotDetails {

    private Enum driverType;
    private LocalDateTime parkingTime;
    private String attendantName;
    private int slot;
    private Vehicle vehicle;

    public SlotDetails(Vehicle vehicle) {
        this.vehicle=vehicle;
    }

    public SlotDetails(Vehicle vehicle, Enum driverType, int slot, String attendantName) {
        this.vehicle=vehicle;
        this.driverType = driverType;
        this.slot = slot;
        this.attendantName = attendantName;
        this.parkingTime = LocalDateTime.now();
    }

    public SlotDetails() { }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getAttendantName() {
        return attendantName;
    }

    public LocalDateTime getParkingTime() {
        return parkingTime;
    }

    public int getVehicleSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot=slot;
    }

    public void setVehicleSlot(Vehicle vehicle, int slot, String attendant) {
        this.vehicle=vehicle;
        this.slot=slot;
        this.attendantName =attendant;
    }

    public int getSlot(Vehicle vehicle) {
        if(this.vehicle.equals(vehicle))
            return slot;
        return 0;
    }
}
