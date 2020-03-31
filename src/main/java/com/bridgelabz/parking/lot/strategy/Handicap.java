package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.exception.ParkingLotException;

public class Handicap {
    public int getVehicleSlot() {
      for(int i = 0; i< ParkingLot.parkingLotList.size(); i++)
            if (ParkingLot.parkingLotList.get(i).getVehicle() == null)
                return i;
        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
