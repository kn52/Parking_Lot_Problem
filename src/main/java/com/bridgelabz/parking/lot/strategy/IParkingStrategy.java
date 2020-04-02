package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.details.SlotDetails;

import java.util.List;

public interface IParkingStrategy {
    ParkingLot getParkingLot(List<ParkingLot> parkingLots);
    int getVehicleSlot(List<SlotDetails> parkingLotList);
}
