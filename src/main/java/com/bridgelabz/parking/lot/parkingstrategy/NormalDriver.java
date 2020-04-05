package com.bridgelabz.parking.lot.parkingstrategy;

import com.bridgelabz.parking.lot.parkinglotdetails.ParkingLot;
import com.bridgelabz.parking.lot.parkinglotexception.ParkingLotException;

import java.util.Comparator;
import java.util.List;

public class NormalDriver implements IParkingStrategy{

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots){
        ParkingLot parkingLot=parkingLots.stream().filter(lots->lots.getEmptySlots()>0)
                .sorted(Comparator.comparing(lots->lots.getEmptySlots()))
                .findFirst()
                .orElseThrow(()-> new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_PARKING_LOT_AVAILABLE));
        return parkingLot;
    }

    @Override
    public int getVehicleSlot(List<Integer> emptySlotList) {
        return emptySlotList.get(emptySlotList.size()-1);
    }
}
