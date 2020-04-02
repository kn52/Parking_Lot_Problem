package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.SlotDetails;

import java.util.List;

public class ParkingLotStrategy {
    public int getVehicleSlot(DriverType driverType,List<SlotDetails> parkingLotList) {
        IParkingStrategy iParkingStrategy= driverType.getParkingStrategy();
        int slotNumber=iParkingStrategy.getVehicleSlot(parkingLotList);
        return slotNumber;
//        for(int i = 0; i< parkingLotList.size(); i++)
//            if (parkingLotList.get(i).getVehicle() == null){
//                return i;
//            }
//        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
