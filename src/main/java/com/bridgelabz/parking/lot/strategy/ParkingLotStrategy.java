package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.exception.ParkingLotException;

public class ParkingLotStrategy {
    public int getVehicleSlot(DriverType driverType) {
        int slotNumber=-1;
        slotNumber=driverType.getParkingStrategy().getVehicleSlot();
        if(slotNumber!=-1)
            return slotNumber;
        throw new ParkingLotException("No Such Driver", ParkingLotException.ExceptionType.NO_SUCH_DRIVER);
    }
}
