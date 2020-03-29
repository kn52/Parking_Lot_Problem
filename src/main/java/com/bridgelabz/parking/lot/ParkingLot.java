package com.bridgelabz.parking.lot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLot {

    public static List<SlotDetails> parkingLotList;
    private int noOfFullSlots;
    private List<ParkingLotObserver> observers;
    private int PARKING_LOT_SIZE =1;
    private ParkingLotStrategy parkingStrategy;

    public ParkingLot() {
        this.noOfFullSlots =0;
        this.observers=new ArrayList<>();
        this.parkingLotList =new ArrayList<>();
        parkingStrategy=new ParkingLotStrategy();
        this.initializeParkingLot(this.PARKING_LOT_SIZE);
    }

    public void parkVehicle(Object vehicle, DriverType type, String name) {
        if(this.noOfFullSlots == this.PARKING_LOT_SIZE) {
            observers.stream().forEach(observer->observer.capacityIsFull());
            throw new ParkingLotException("Parking is full", ParkingLotException.ExceptionType.NO_PARKING);
        }
        int slotnumber=this.getSlotNumber(type,parkingStrategy);
        SlotDetails slot=new SlotDetails(vehicle,type,slotnumber,name);
        if(slotnumber == ParkingLotOwner.slotNumber)
            observers.stream().filter(observer->observer instanceof ParkingLotOwner)
                    .map(ParkingLotOwner.class::cast).findFirst().get().slotOccupied();
        if(!this.parkingLotList.contains(slot)){
            this.parkingLotList.set(slotnumber,slot);
            this.noOfFullSlots++;
        }
    }

    public boolean unParkVehicle(Object vehicle) {
        boolean result=this.parkingLotList.stream().filter(x-> x.getVehicle() == vehicle).findFirst().isPresent();
        if(result)
        {
            this.parkingLotList.remove(vehicle);
            observers.stream().forEach(observer->observer.capacityIsEmpty());
            this.noOfFullSlots--;
            return true;
        }
        return false;
    }

    public boolean isVehicleParked(Object vehicle) {
        boolean result=this.parkingLotList.stream().filter(x-> x.getVehicle() == vehicle).findFirst().isPresent();
        if(result)
            return true;
        return false;
    }

    private void initializeParkingLot(int size) {
        IntStream.range(0,size).forEach(lot->this.parkingLotList.add(new SlotDetails(null)));
    }

    public int getSlotNumber(DriverType type, ParkingLotStrategy parkingStrategy) {
        return parkingStrategy.getVehicleSlot(type);
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.PARKING_LOT_SIZE = capacity;
        this.initializeParkingLot(this.PARKING_LOT_SIZE);
    }
    public int getTotalCapacity() {
        return this.PARKING_LOT_SIZE;
    }

    public int getSlotNumberByVehicle(Object vehicle) {
        return this.parkingLotList.stream().filter(x-> x.getVehicle() == vehicle).findFirst().get().getVehicleSlot();
    }

    public int getEmptySlots() {
        return this.PARKING_LOT_SIZE-this.noOfFullSlots;
    }

    public void setParkingStrategy(ParkingLotStrategy parkingLotStrategy) {
        this.parkingStrategy=parkingLotStrategy;
    }
}