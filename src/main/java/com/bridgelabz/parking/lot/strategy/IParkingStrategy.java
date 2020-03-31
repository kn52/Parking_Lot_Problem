package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;

import java.util.List;

public interface IParkingStrategy {
    int getVehicleSlot();
    ParkingLot getParkingLot(List<ParkingLot> parkingLots);
}
