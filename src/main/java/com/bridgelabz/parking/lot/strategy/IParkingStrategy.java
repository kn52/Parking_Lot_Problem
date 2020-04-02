package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;

import java.util.List;

public interface IParkingStrategy {
    ParkingLot getParkingLot(List<ParkingLot> parkingLots);
    int getVehicleSlot(List<Integer> emptySlotList);
}
