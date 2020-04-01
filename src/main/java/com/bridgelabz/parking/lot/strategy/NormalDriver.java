package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NormalDriver implements IParkingStrategy{

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots){
        ParkingLot parkingLot=parkingLots.stream().filter(lots->lots.getEmptySlots()>0)
                .sorted(Comparator.comparing(lots->lots.getEmptySlots()))
                .collect(Collectors.toList()).get(0);
        return parkingLot;
//        return parkingLots.stream().filter(lots->lots.getEmptySlots()>0).findFirst()
//                .orElseThrow(()-> new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_PARKING_LOT_AVAILABLE));
    }
}
