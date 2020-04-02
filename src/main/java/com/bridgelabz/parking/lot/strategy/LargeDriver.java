package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.details.SlotDetails;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class LargeDriver implements IParkingStrategy {
    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream().filter(lots->lots.getEmptySlots()>0)
                .sorted(Comparator.comparing(lots->lots.getEmptySlots(),Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(()-> new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_PARKING_LOT_AVAILABLE));
    }

    @Override
    public int getVehicleSlot(List<SlotDetails> parkingLotList) {
        for(int i = 0; i< parkingLotList.size()-3; i++)
            if (parkingLotList.get(i).getVehicle() == null && parkingLotList.get(i+1).getVehicle() == null && parkingLotList.get(i+2).getVehicle() == null )
                return i+1;
        return IntStream.range(0,parkingLotList.size()).filter(lots->parkingLotList.get(lots).getVehicle() == null)
                .findFirst()
                .orElseThrow(()-> new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE));
    }
}
