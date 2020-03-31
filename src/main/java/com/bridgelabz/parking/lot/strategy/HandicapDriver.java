package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

import java.util.List;

public class HandicapDriver implements IParkingStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots){
        return parkingLots.stream().filter(lots->lots.getEmptySlots()>0)
                .findFirst()
                .orElseThrow(()-> new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_PARKING_LOT_AVAILABLE));
    }
}
