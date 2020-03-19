package com.bridgelabz.parking.lot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    Vehicle vehicle;
    public ParkingLot() {
        vehicle=new Vehicle();
    }

    public boolean park(Vehicle vehicle) {
        vehicle.addVehicle(vehicle);
        return true;
    }

    public boolean unPark() {
        return true;
    }
}
