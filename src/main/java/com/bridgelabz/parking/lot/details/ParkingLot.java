package com.bridgelabz.parking.lot.details;

import com.bridgelabz.parking.lot.exception.ParkingLotException;
import com.bridgelabz.parking.lot.observer.ParkingLotInformer;
import com.bridgelabz.parking.lot.observer.IParkingLotObserver;
import com.bridgelabz.parking.lot.observer.ParkingLotOwner;
import com.bridgelabz.parking.lot.strategy.DriverType;
import com.bridgelabz.parking.lot.strategy.ParkingLotStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLot {

    public static List<SlotDetails> parkingLotList;
    private int noOfFullSlots;
    private ParkingLotInformer observers;
    private int PARKING_LOT_SIZE =1;
    private ParkingLotStrategy parkingStrategy;

    public ParkingLot() {
        this.noOfFullSlots =0;
        observers=new ParkingLotInformer();
        this.parkingLotList =new ArrayList<>();
        parkingStrategy=new ParkingLotStrategy();
        this.initializeParkingLot(this.PARKING_LOT_SIZE);
    }

    public void parkVehicle(Object vehicle, DriverType type, String name) {
        this.getEmptySlots();
        if(this.isVehicleParked(vehicle))
            throw new ParkingLotException("Already Parked", ParkingLotException.ExceptionType.ALREADY_PARKED);
        int slotnumber=this.getSlotNumber(parkingStrategy);
        SlotDetails slot=new SlotDetails(vehicle,type,slotnumber,name);
        this.parkingLotList.set(slotnumber,slot);
        this.noOfFullSlots++;
    }

    public boolean unParkVehicle(Object vehicle) {
        if(this.parkingLotList.stream().filter(x-> x.getVehicle() == vehicle).findFirst().isPresent())
        {
            this.parkingLotList.remove(vehicle);
            observers.InformObserversEmptySlotsAvailable();
            this.noOfFullSlots--;
            return true;
        }
        return false;
    }

    public boolean isVehicleParked(Object vehicle) {
        return this.parkingLotList.stream().filter(x-> x.getVehicle() == vehicle).findFirst().isPresent();
    }

    private void initializeParkingLot(int size) {
        IntStream.range(0,size).forEach(lot->this.parkingLotList.add(new SlotDetails(null)));
    }

    public int getSlotNumber(ParkingLotStrategy parkingStrategy) {
        int slotnumber=parkingStrategy.getVehicleSlot();
        if(slotnumber == ParkingLotOwner.slotNumber)
            observers.InformOwner();
        return slotnumber;
    }

    public void registerObserver(IParkingLotObserver observer) {
        observers.addObserver(observer);
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
        if((this.PARKING_LOT_SIZE-this.noOfFullSlots)==0)
            observers.InformObserversNoEmptySlotsAvailable();
        return this.PARKING_LOT_SIZE-this.noOfFullSlots;
    }

    public void setParkingStrategy(ParkingLotStrategy parkingLotStrategy) {
        this.parkingStrategy=parkingLotStrategy;
    }
}