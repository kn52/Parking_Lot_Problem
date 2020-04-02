package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.details.SlotDetails;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

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
    public int getVehicleSlot(List<SlotDetails> parkingLotList) {
        for(int i = parkingLotList.size()-1; i >= 0; i++)
            if (parkingLotList.get(i).getVehicle() == null){
                return i;
            }
        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
