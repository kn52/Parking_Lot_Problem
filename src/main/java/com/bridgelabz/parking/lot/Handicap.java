package com.bridgelabz.parking.lot;
import java.util.Map;

public class Handicap {
    public int getVehicleSlot() {
        Map<Integer,Object> map=ParkingLot.slots;
        for(int i=0;i<3;i++){
            if(map.get(i)==null){
                return i;
            }
        }
        throw new ParkingLotException("NO Slot Available", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE);
    }
}
