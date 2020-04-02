package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.details.SlotDetails;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

import java.util.List;

public class HandicapDriver implements IParkingStrategy {

    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots){
        return parkingLots.stream().filter(lots->lots.getEmptySlots()>0)
                .findFirst()
                .orElseThrow(()-> new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_PARKING_LOT_AVAILABLE));
    }

    @Override
    public int getVehicleSlot(List<SlotDetails> parkingLotList) {
        for(int i = 0; i< parkingLotList.size(); i++)
            if (parkingLotList.get(i).getVehicle() == null){
                return i;
            }
        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
