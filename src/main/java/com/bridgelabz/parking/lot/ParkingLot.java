package com.bridgelabz.parking.lot;

public class ParkingLot {
    private static final int FULL_SIZE = 3;
    Vehicle vehicleData;
    AirportSecurity airportSecurity;

    public ParkingLot() {
        vehicleData =new Vehicle();
        airportSecurity=new AirportSecurity();
    }

    public boolean park(String name) {
        vehicleData.addVehicle(name);
        return true;
    }

    public boolean unPark(String name) {
        if(vehicleData.get(name)) {
            vehicleData.remove(name);
            return true;
        }
        return false;
    }

    public boolean parkingLotIsFull() {
        if(vehicleData.getSize()==FULL_SIZE){
            airportSecurity.setinformed();
            return true;
        }
        return false;
    }
}
