package com.bridgelabz.parking.lot;

public class ParkingLotOwner implements ParkingLotObserver {
    private boolean isCapacityFull;
    public int slotNumber;
    private boolean slotIsFull=false;

    @Override
    public void capacityIsFull() {
        this.isCapacityFull=true;
    }

    @Override
    public void capacityIsEmpty() {
        this.isCapacityFull=false;
    }

    public void slotOccupied() {
        this.slotIsFull=true;
    }

    public boolean isCapacityFull() {
        return this.isCapacityFull;
    }

    public void setSlot(int slotNumber) {
        this.slotNumber=slotNumber;
        slotIsFull=true;
    }

    public boolean getSlotStatus() {
        return slotIsFull;
    }
}
