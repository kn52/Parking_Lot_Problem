package com.bridgelabz.parking.lot.vehicledetails;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;
import java.util.function.Predicate;

public enum VehicleModel implements IPredicate {
    BMW,TOYOTA;
    @Override
    public Predicate<SlotDetails> getPredicate(IPredicate ipredicate) {
        return slot -> slot.getVehicle().getVehicleModel() == ipredicate;
    }
}
