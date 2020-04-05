package com.bridgelabz.parking.lot.parkingstrategy;

import com.bridgelabz.parking.lot.parkinglotdetails.ParkingLot;
import java.util.List;

public class OtherDriver implements IParkingStrategy {
    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) {
        return null;
    }

    @Override
    public int getVehicleSlot(List<Integer> emptySlotList) {
        return 0;
    }
}
