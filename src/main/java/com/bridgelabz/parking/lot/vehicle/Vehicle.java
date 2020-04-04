package com.bridgelabz.parking.lot.vehicle;

import java.util.Objects;

public class Vehicle {
    private String VehicleColor;
    private VehicleType vehicleType;
    private int vehiclePlateNumber;
    private String vehicleModel;

    public Vehicle(String vehicleColor, VehicleType vehicleType, int vehiclePlateNumber, String vehicleModel) {
        VehicleColor = vehicleColor;
        this.vehicleType = vehicleType;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleColor() {
        return VehicleColor;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }
}
