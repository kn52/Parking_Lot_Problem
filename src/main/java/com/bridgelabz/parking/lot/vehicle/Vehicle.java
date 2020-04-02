package com.bridgelabz.parking.lot.vehicle;

public class Vehicle {
    private VehicleType vehicleType;

    public Vehicle() {  }

    public Vehicle(VehicleType vehicleType) {
        this.vehicleType=vehicleType;
    }

    public VehicleType getVehicleType(Vehicle vehicle) {
        return vehicle.vehicleType;
    }
}
