package com.bridgelabz.parking.lot;

public class ParkingLotStrategy {
    public int getVehicleSlot(DriverType type) {
        if(type.equals(DriverType.HANDICAP))
            return new Handicap().getVehicleSlot();
        throw new ParkingLotException("No Such Driver", ParkingLotException.ExceptionType.NO_SUCH_DRIVER);
    }
}
