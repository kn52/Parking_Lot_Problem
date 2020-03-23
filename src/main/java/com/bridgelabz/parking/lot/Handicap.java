package com.bridgelabz.parking.lot;
import java.util.List;
import java.util.Map;

public class Handicap {
    public int getVehicleSlot() {
        List<Slot> vehicle=ParkingLot.vehicles;
        int k=0;
        for(int i=0;i<vehicle.size();i++)
        {
            int number=vehicle.get(i).getVehicleSlot();
            if(k==number)
                k++;
            else
                return k;
        }
        throw new ParkingLotException("NO Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
