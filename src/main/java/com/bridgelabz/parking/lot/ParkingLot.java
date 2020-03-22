package com.bridgelabz.parking.lot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int currentCapacity;
    private int actualCapacity;
    private List vehicle;
    private  List<ParkingLotObserver> observers;
    private ParkingLotOwner owner;

    public ParkingLot() {
    }
    
    public ParkingLot(int capacity)
    {
        this.observers=new ArrayList();
        this.vehicle=new ArrayList();
        this.currentCapacity=0;
        this.actualCapacity=capacity;
    }

    public void park(Object vehicle) {
        if(!this.vehicle.contains(vehicle))
            this.vehicle.add(vehicle);
        if(this.currentCapacity==this.actualCapacity) {
            for(ParkingLotObserver observer : observers)
            {
                observer.capacityIsFull();
            }
            owner.capacityIsFull();
            throw new ParkingLotException("Parking is full", ParkingLotException.ExceptionType.NO_PARKING);
        }
        this.currentCapacity++;
    }

    public boolean unPark(Object vehicle) {
        if(this.vehicle == null)
            return false;
        if(this.vehicle.contains(vehicle)){
            this.vehicle.remove(vehicle);
            for(ParkingLotObserver observer : observers)
            {
                observer.capacityIsEmpty();
            }
            return true;
        }
        return false;
    }



    public boolean isVehicleParked(Object vehicle) {
        if(this.vehicle.contains(vehicle))
            return  true;
        return false;
    }

    public void registerObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    public void setCapacity(int capacity) {
        this.actualCapacity=capacity;
    }

    public void registerOwner(ParkingLotOwner owner) {
        this.owner=owner;
    }
}
