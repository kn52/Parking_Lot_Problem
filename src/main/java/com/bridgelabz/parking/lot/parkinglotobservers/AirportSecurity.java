package com.bridgelabz.parking.lot.parkinglotobservers;

public class AirportSecurity implements IParkingLotObserver {

    private boolean isCapacityFull;

    @Override
    public void capacityIsFull() {
        this.isCapacityFull=true;
    }

    @Override
    public void capacityIsEmpty() {
        this.isCapacityFull=false;
    }

    public boolean isCapacityFull() { return this.isCapacityFull; }
}
