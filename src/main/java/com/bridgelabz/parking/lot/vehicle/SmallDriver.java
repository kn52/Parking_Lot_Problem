package com.bridgelabz.parking.lot.vehicle;

import com.bridgelabz.parking.lot.details.SlotDetails;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SmallDriver implements IVehicleStrategy{

    @Override
    public List<Integer> getSlotList(List<SlotDetails> parkingLotList) {
        List<Integer> emptySlots=new ArrayList();
        IntStream.range(0,parkingLotList.size())
            .filter(lots->parkingLotList.get(lots).getVehicle() == null).forEach(lots->emptySlots.add(lots));

        if(emptySlots.size()>0)
            return emptySlots;

        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
