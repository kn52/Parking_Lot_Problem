package com.bridgelabz.parking.lot.vehicle;

public class VehicleDetails {
    private int vehicleSlot;
    private int vehiclePlateNumber;
    private String attendantName;
    public VehicleDetails(int vehicleSlot, int vehiclePlateNumber, String attendantName) {
        this.vehicleSlot=vehicleSlot;
        this.vehiclePlateNumber=vehiclePlateNumber;
        this.attendantName=attendantName;
    }
}
