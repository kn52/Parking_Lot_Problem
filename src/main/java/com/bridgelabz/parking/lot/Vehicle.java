package com.bridgelabz.parking.lot;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String vehicle = null;
    public List vehicleData=new ArrayList();
    public Vehicle() {
    }
    public Vehicle(String vehicle) {
        this.vehicle=vehicle;
    }


    public void addVehicle(Vehicle vehicle) {
        vehicleData.add(vehicle);
    }
}
