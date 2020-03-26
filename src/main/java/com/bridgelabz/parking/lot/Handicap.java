package com.bridgelabz.parking.lot;

public class Handicap {
    public int getVehicleSlot() {
        int slotNumber=-1;
        slotNumber=ParkingLot.vehicles.stream().filter(x-> x.getVehicle() == null).findFirst().get().getVehicleSlot();
        if(slotNumber!=0)
            return slotNumber;
        throw new ParkingLotException("NO Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
