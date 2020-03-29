package com.bridgelabz.parking.lot;

public class Normal {
    public int getVehicleSlot() {
        for(int i=0;i<ParkingLot.parkingLotList.size();i++) {
            Object vehicle = ParkingLot.parkingLotList.get(i).getVehicle();
            if (vehicle == null)
                return i;
        }
        throw new ParkingLotException("NO Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
