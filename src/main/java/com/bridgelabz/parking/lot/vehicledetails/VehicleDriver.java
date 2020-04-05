package com.bridgelabz.parking.lot.vehicledetails;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;
import com.bridgelabz.parking.lot.parkingstrategy.DriverType;

import java.util.function.Predicate;

public enum VehicleDriver implements IPredicate {
    HANDICAP_SMALL{
        @Override
        public Predicate<SlotDetails> getPredicate() {
            return slot -> slot.getVehicle().getVehicleType() == VehicleType.SMALL && slot.getDriverType() == DriverType.HANDICAP;
        }
    }
}
