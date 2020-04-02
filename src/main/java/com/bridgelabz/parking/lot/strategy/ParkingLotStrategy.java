package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.details.SlotDetails;
import com.bridgelabz.parking.lot.exception.ParkingLotException;
import com.bridgelabz.parking.lot.vehicle.IVehicleStrategy;
import com.bridgelabz.parking.lot.vehicle.Vehicle;
import com.bridgelabz.parking.lot.vehicle.VehicleType;

import java.util.List;

public class ParkingLotStrategy {
    public int getVehicleSlot(Vehicle vehicle, DriverType driverType, List<SlotDetails> parkingLotList) {
        VehicleType vehicleType=new Vehicle().getVehicleType(vehicle);
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
