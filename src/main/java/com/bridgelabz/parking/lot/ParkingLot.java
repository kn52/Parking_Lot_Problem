package com.bridgelabz.parking.lot;

public class ParkingLot {
    private static final int FULL_SIZE = 100;
    Vehicle vehicleData;
    public ParkingLot() {
        vehicleData =new Vehicle();
    }

    public boolean park(String name) {
        vehicleData.addVehicle(name);
        return true;
    }

    public boolean unPark(String name) {
        if(vehicleData.get(name)) {
            return true;
        }
        return false;
    }

    public boolean parkingLotIsFull() {
        if(vehicleData.getSize()==FULL_SIZE)
            return true;
        return false;
    }
}
