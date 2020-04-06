package com.bridgelabz.parking.lot.vehicledetails;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public enum VehicleType implements IVehicleStrategy,IPredicate {
    SMALL{
        @Override
        public List<Integer> getSlotList(List<SlotDetails> parkingLotList){
            List<Integer> emptySlots=new ArrayList();
            IntStream.range(0,parkingLotList.size())
                    .filter(lots->parkingLotList.get(lots).getVehicle() == null).forEach(lots->emptySlots.add(lots));
            return emptySlots;
        }
    },LARGE{
        @Override
        public List<Integer> getSlotList(List<SlotDetails> parkingLotList){
            List<Integer> emptySlots=new ArrayList();
            for(int i = 0; i< parkingLotList.size()-3; i++)
                if (parkingLotList.get(i).getVehicle() == null && parkingLotList.get(i+1).getVehicle() == null && parkingLotList.get(i+2).getVehicle() == null)
                {   emptySlots.add(i+1); }

            IntStream.range(0,parkingLotList.size())
                    .filter(lots->parkingLotList.get(lots).getVehicle() == null)
                    .forEach(lots->emptySlots.add(lots));
            return emptySlots;
        }
    };

    @Override
    public Predicate<SlotDetails> getPredicate(IPredicate ipredicate) {
        return slot -> slot.getVehicle().getVehicleType() == ipredicate;
    }
}
