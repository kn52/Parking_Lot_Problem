package com.bridgelabz.parking.lot.parkingstrategy;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;
import com.bridgelabz.parking.lot.parkinglotexception.ParkingLotException;
import com.bridgelabz.parking.lot.vehicledetails.IVehicleStrategy;
import com.bridgelabz.parking.lot.vehicledetails.Vehicle;
import com.bridgelabz.parking.lot.vehicledetails.VehicleType;

import java.util.List;

public class ParkingLotStrategy {
    public int getVehicleSlot(Vehicle vehicle, DriverType driverType, List<SlotDetails> parkingLotList) {
        VehicleType vehicleType=vehicle.getVehicleType();
        IVehicleStrategy iVehicleStrategy=vehicleType.getVehicleStrategy();
        List<Integer> emptySlotList = iVehicleStrategy.getSlotList(parkingLotList);

        if(emptySlotList.size()>0) {
            IParkingStrategy iParkingStrategy = driverType.getParkingStrategy();
            int slotNumber = iParkingStrategy.getVehicleSlot(emptySlotList);
            return slotNumber;
        }
        throw new ParkingLotException("No Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
