package com.bridgelabz.parking.lot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLot {

    public static List<Slot> vehicles;
    private int capacity;
    private ParkingLotOwner owner;
    private List<ParkingLotObserver> observers;
    public int FULL_SIZE=1;

    public ParkingLot() {
        this.capacity=0;
        owner=new ParkingLotOwner();
        this.observers=new ArrayList<>();
        vehicles=new ArrayList<>();
    }

    public void park(Object vehicle, DriverType type, String name) {
        int slotnumber=this.getSlotNumber();
        Slot slot=new Slot(vehicle,type,1,name);
        for (ParkingLotObserver observer : observers) {
            if(observer instanceof ParkingLotOwner)
                ((ParkingLotOwner) observer).slotOccupied();
        }
        if(this.capacity == FULL_SIZE) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking is full", ParkingLotException.ExceptionType.NO_PARKING);
        }
        if(!this.vehicles.contains(slot)){
            this.vehicles.add(slot);
            this.capacity++;
        }
    }

    private int getSlotNumber() {
        int size=this.getTotalCapacity();
        this.initialize(size);
        for(int i=0;i<size;i++){
            if(this.vehicles.get(i)== null)
                return i;
        }
        return 1;
    }

    private void initialize(int size){
        IntStream.range(0,size).forEach(lot->this.vehicles.add(new Slot(null)));
    }

    public boolean isVehicleParked(Object vehicle) {
        boolean result=this.vehicles.stream()
                .filter(x-> x.getVehicle() == vehicle).findFirst().isPresent();
        if(result)
            return true;
        return false;
    }

    public boolean unPark(Object vehicle) {
        boolean result=this.vehicles.stream()
                .filter(x-> x.getVehicle() == vehicle).findFirst().isPresent();
        if(result)
        {
            this.vehicles.remove(vehicle);
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsEmpty();
            }
            this.capacity--;
            return true;
        }
        return false;
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.FULL_SIZE=capacity;
    }
    public int getTotalCapacity() {
        return this.FULL_SIZE;
    }

    public int getVehicleSlot(Object vehicle) {
        int slotNumber=this.vehicles.stream()
                .filter(x-> x.getVehicle() == vehicle).findFirst().get().getVehicleSlot();
        return slotNumber;
    }
}
