package com.bridgelabz.parking.lot.vehicledetails;

public class Vehicle {
    private VehicleColor vehicleColor;
    private VehicleType vehicleType;
    private int vehiclePlateNumber;
    private VehicleModel vehicleModel;

    public Vehicle(VehicleColor vehicleColor, VehicleType vehicleType, int vehiclePlateNumber, VehicleModel vehicleModel) {
        this.vehicleColor = vehicleColor;
        this.vehicleType = vehicleType;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.vehicleModel = vehicleModel;
    }

    public VehicleColor getVehicleColor() {
        return vehicleColor;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }
}
