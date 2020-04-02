package com.bridgelabz.parking.lot.vehicle;

import com.bridgelabz.parking.lot.details.SlotDetails;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

import java.util.ArrayList;
import java.util.List;

public class LargeDriver implements IVehicleStrategy {

    @Override
    public List<Integer> getSlotList(List<SlotDetails> parkingLotList) {
        List<Integer> emptySlots=new ArrayList();
        for(int i = 0; i< parkingLotList.size()-3; i++)
            if (parkingLotList.get(i).getVehicle() == null && parkingLotList.get(i+1).getVehicle() == null && parkingLotList.get(i+2).getVehicle() == null){
                emptySlots.add(i+1);
            }

        if(emptySlots.size()==0)
            for (int i=0;i<parkingLotList.size();i++)
                if(parkingLotList.get(i).getVehicle() == null)
                    emptySlots.add(i);

        if(emptySlots.size()>0)
            return emptySlots;

        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
