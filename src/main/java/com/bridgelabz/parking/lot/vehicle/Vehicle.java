package com.bridgelabz.parking.lot.vehicle;

public class Vehicle {
    private String color;
    private VehicleType vehicleType;

    public Vehicle(String  color,VehicleType vehicleType) {
        this.color=color;
        this.vehicleType=vehicleType;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }

    public String getVehicleColor() {
        return this.color;
    }
}
