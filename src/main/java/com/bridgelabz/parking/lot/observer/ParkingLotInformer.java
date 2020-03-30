package com.bridgelabz.parking.lot.observer;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotInformer {
    List<ParkingLotObserver> observerList;

    public ParkingLotInformer() {
        this.observerList=new ArrayList<>();
    }

    public void addObserver(ParkingLotObserver observer) {
        this.observerList.add(observer);
    }

    public void InformObserversEmptySlotsAvailable() {
        observerList.stream().forEach(observer->observer.capacityIsEmpty());
    }

    public void InformOwner() {
        observerList.stream().filter(observer->observer instanceof ParkingLotOwner)
                .map(ParkingLotOwner.class::cast).findFirst().get().slotOccupied();
    }

    public void InformObserversNoEmptySlotsAvailable() {
        observerList.stream().forEach(observer->observer.capacityIsFull());
    }
}
