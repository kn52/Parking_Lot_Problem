package com.bridgelabz.parking.lot.vehicledetails;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;

public class VehicleDetails {
    private int vehicleSlot;
    private int vehiclePlateNumber;
    private String attendantName;

    public VehicleDetails(SlotDetails slot) {
        this.vehicleSlot=slot.getVehicleSlot();
        this.vehiclePlateNumber=slot.getVehicle().getVehiclePlateNumber();
        this.attendantName=slot.getAttendantName();
    }

    public int getVehicleSlot() {
        return vehicleSlot;
    }

    public int getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public String getAttendantName() {
        return attendantName;
    }
}
