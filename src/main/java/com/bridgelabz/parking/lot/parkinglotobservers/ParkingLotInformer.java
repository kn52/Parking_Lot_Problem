package com.bridgelabz.parking.lot.parkinglotobservers;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotInformer {

    List<IParkingLotObserver> observerList;

    public ParkingLotInformer() {
        this.observerList=new ArrayList<>();
    }

    public void addObserver(IParkingLotObserver observer) {
        this.observerList.add(observer);
    }

    public void InformObserversEmptySlotsAvailable() {
        this.observerList.stream().forEach(observer->observer.capacityIsEmpty());
    }

    public void InformOwner() {
        this.observerList.stream().filter(observer->observer instanceof ParkingLotOwner)
                .map(ParkingLotOwner.class::cast).findFirst().get().slotOccupied();
    }

    public void InformObserversNoEmptySlotsAvailable() {
        this.observerList.stream().forEach(observer->observer.capacityIsFull());
    }
}
