package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

public class Normal {
    public int getVehicleSlot() {
//        return IntStream.range(0,ParkingLot.parkingLotList.size()).filter(x->ParkingLot.parkingLotList.get(x)==null)
//                .findFirst().orElse(-1);
        for(int i = 0; i< ParkingLot.parkingLotList.size(); i++) {
            Object vehicle = ParkingLot.parkingLotList.get(i).getVehicle();
            if (vehicle == null)
                return i;
        }
        throw new ParkingLotException("NO Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
