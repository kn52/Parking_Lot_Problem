package com.bridgelabz.parking.lot.vehicledetails;

import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;

import java.util.function.Predicate;

public enum VehicleModel implements IPredicate {
    BMW {
        @Override
        public Predicate<SlotDetails> getPredicate() {
            return slot -> slot.getVehicle().getVehicleModel().equals(BMW);
        }
    }, TOYOTA {
        @Override
        public Predicate<SlotDetails> getPredicate() {
            return slot -> slot.getVehicle().getVehicleModel().equals(TOYOTA);
        }
    },
    OTHER {
        @Override
        public Predicate<SlotDetails> getPredicate() {
            return slot -> slot.getVehicle().getVehicleModel() == VehicleModel.TOYOTA || slot.getVehicle().getVehicleModel() == VehicleModel.BMW;
        }
    }
}
