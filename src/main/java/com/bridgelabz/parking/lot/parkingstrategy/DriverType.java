package com.bridgelabz.parking.lot.parkingstrategy;

import com.bridgelabz.parking.lot.parkinglotdetails.ParkingLot;
import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;
import com.bridgelabz.parking.lot.parkinglotexception.ParkingLotException;
import com.bridgelabz.parking.lot.vehicledetails.IPredicate;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public enum DriverType implements IParkingStrategy, IPredicate {

    HANDICAP {
        @Override
        public ParkingLot getParkingLot(List<ParkingLot> parkingLots){
            ParkingLot parkingLot=parkingLots.stream().filter(lots->lots.getEmptySlots()>0)
                    .sorted(Comparator.comparing(lots->lots.getEmptySlots()))
                    .findFirst()
                    .orElseThrow(()-> new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_PARKING_LOT_AVAILABLE));
            return parkingLot;
        }

        @Override
        public int getVehicleSlot(List<Integer> emptySlotList) {
            return emptySlotList.get(emptySlotList.size()-1);
        }
    }
    ,NORMAL{
        @Override
        public ParkingLot getParkingLot(List<ParkingLot> parkingLots){
            return parkingLots.stream().filter(lots->lots.getEmptySlots()>0)
                    .findFirst()
                    .orElseThrow(()-> new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_PARKING_LOT_AVAILABLE));
        }

        @Override
        public int getVehicleSlot(List<Integer> emptySlotList) {
            return emptySlotList.get(0);
        }
    };

    @Override
    public Predicate<SlotDetails> getPredicate(IPredicate ipredicate) {
        return slot -> slot.getDriverType() == ipredicate;
    }
}
