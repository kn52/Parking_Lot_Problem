package com.bridgelabz.parking.lot.observer;

public class ParkingLotOwner implements IParkingLotObserver {
    private boolean isCapacityFull;
    public static int slotNumber=-1;
    private boolean slotIsFull=false;

    @Override
    public void capacityIsFull() {
        this.isCapacityFull=true;
    }

    @Override
    public void capacityIsEmpty() {
        this.isCapacityFull=false;
    }

    public void slotOccupied() { this.slotIsFull=true; }

    public boolean isCapacityFull() {
        return this.isCapacityFull;
    }

    public void setOwnerSlot(int slotNumber) { this.slotNumber=slotNumber; }

    public boolean getOwnerSlotStatus() {
        return slotIsFull;
    }

    public boolean getSlotStatus() { return this.isCapacityFull; }
}
