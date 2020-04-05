package com.bridgelabz.parking.lot.vehicledetails;
import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;

import java.util.function.Predicate;
public interface IPredicate {
    Predicate<SlotDetails> getPredicate();
}
