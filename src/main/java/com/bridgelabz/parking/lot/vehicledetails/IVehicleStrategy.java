package com.bridgelabz.parking.lot.vehicledetails;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;

import java.util.List;

public interface IVehicleStrategy {
    List<Integer> getSlotList(List<SlotDetails> parkingLotList);
}
