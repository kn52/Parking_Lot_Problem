package com.bridgelabz.parking.lot.parkingstrategy;

import com.bridgelabz.parking.lot.parkinglotdetails.ParkingLot;
import com.bridgelabz.parking.lot.parkinglotexception.ParkingLotException;

import java.util.List;

public class HandicapDriver implements IParkingStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots){
        return parkingLots.stream().filter(lots->lots.getEmptySlots()>0)
                .findFirst()
                .orElseThrow(()-> new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_PARKING_LOT_AVAILABLE));
    }

    @Override
    public int getVehicleSlot(List<Integer> emptySlotList) {
        return emptySlotList.get(0);
        }
}
