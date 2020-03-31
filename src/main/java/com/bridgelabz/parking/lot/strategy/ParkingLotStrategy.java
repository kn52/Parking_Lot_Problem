package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.exception.ParkingLotException;

public class ParkingLotStrategy {
    public int getVehicleSlot(DriverType driverType) {
        int slot=-1;
        slot=driverType.getParkingStrategy().getVehicleSlot();
        if(slot!=-1)
            return slot;
        throw new ParkingLotException("No Such Driver", ParkingLotException.ExceptionType.NO_SUCH_DRIVER);
    }
}
