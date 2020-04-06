package com.bridgelabz.parking.lot.parkingstrategy;

import com.bridgelabz.parking.lot.parkinglotdetails.ParkingLot;

import java.util.List;

public interface IParkingStrategy {
    ParkingLot getParkingLot(List<ParkingLot> parkingLots);
    int getVehicleSlot(List<Integer> emptySlotList);

}
