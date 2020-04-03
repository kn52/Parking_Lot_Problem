package com.bridgelabz.parking.lot.vehicle;

import com.bridgelabz.parking.lot.details.SlotDetails;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LargeDriver implements IVehicleStrategy {

    @Override
    public List<Integer> getSlotList(List<SlotDetails> parkingLotList) {
        List<Integer> emptySlots=this.getEmptySlotList(parkingLotList);
        for(int i = 0; i< parkingLotList.size()-3; i++)
            if (parkingLotList.get(i).getVehicle() == null && parkingLotList.get(i+1).getVehicle() == null && parkingLotList.get(i+2).getVehicle() == null)
                emptySlots.add(i+1);
        if(emptySlots.size()==0)
            this.getEmptySlotList(parkingLotList);
        if(emptySlots.size()>0)
            return emptySlots;
        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }

    private List<Integer> getEmptySlotList(List<SlotDetails> parkingLotList) {
        List<Integer> slots=new ArrayList<>();
         IntStream.range(0,parkingLotList.size())
                .filter(lots->parkingLotList.get(lots).getVehicle() == null)
                .forEach(lots->slots.add(lots));
        return slots;
    }
}
