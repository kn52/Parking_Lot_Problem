package com.bridgelabz.parking.lot.vehicledetails;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;

import java.util.function.Predicate;

public enum VehicleColor implements IPredicate {
    WHITE{
        @Override
        public Predicate<SlotDetails> getPredicate() {
            return slot -> slot.getVehicle().getVehicleColor().equals(WHITE);
        }
    }, BLUE {
        @Override
        public Predicate<SlotDetails> getPredicate() {
            return slot -> slot.getVehicle().getVehicleColor().equals(BLUE);
        }
    }
}
