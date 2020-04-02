package com.bridgelabz.parking.lot.vehicle;

import com.bridgelabz.parking.lot.details.SlotDetails;

import java.util.List;

public interface IVehicleStrategy {
    List<Integer> getSlotList(List<SlotDetails> parkingLotList);
}
