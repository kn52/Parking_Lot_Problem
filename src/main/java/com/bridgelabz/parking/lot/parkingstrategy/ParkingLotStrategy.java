package com.bridgelabz.parking.lot.parkingstrategy;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;
import com.bridgelabz.parking.lot.parkinglotexception.ParkingLotException;
import com.bridgelabz.parking.lot.vehicledetails.Vehicle;

import java.util.List;

public class ParkingLotStrategy {
    public int getVehicleSlot(Vehicle vehicle, DriverType driverType, List<SlotDetails> parkingLotList) {
        List<Integer> emptySlotList = vehicle.getVehicleType().getSlotList(parkingLotList);
        if(emptySlotList.size()>0) {
            int slotNumber = driverType.getVehicleSlot(emptySlotList);
            return slotNumber;
        }
        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
