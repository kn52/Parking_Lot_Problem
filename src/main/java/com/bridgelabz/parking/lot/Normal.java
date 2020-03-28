package com.bridgelabz.parking.lot;

public class Normal {
    public int getVehicleSlot() {
        int slotNumber=-1;
        slotNumber=ParkingLot.parkingLotList.stream().filter(x-> x.getVehicle() == null).findFirst().get().getVehicleSlot();
        if(slotNumber!=-1)
            return slotNumber;
        throw new ParkingLotException("NO Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
