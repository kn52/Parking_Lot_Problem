package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.ParkingLot;
import com.bridgelabz.parking.lot.ParkingLotException;
import com.bridgelabz.parking.lot.ParkingLotOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    ParkingLot parkingLot;
    Object vehicle;
    @Before
    public void setUp() throws Exception {
        parkingLot=new ParkingLot(1);
        vehicle=new Object();
    }

    @Test
    public void WhengivenVehicleParked_ShouldReturn_True(){
        try{
            parkingLot.park(vehicle);
            boolean result=parkingLot.isVehicleParked(vehicle);
            Assert.assertTrue(result);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void WhengivenVehicle_AlreadyParked_ShouldReturn_False(){
        try{
            parkingLot.park(vehicle);
            parkingLot.park(new Object());

        }catch (ParkingLotException e){
            Assert.assertEquals("Parking is full",e.getMessage());
        }
    }

    @Test
    public void WhengivenVehicleunParked_ShouldReturn_True() {
        try {
            parkingLot.park(vehicle);
            boolean result = parkingLot.unPark(vehicle);
            Assert.assertTrue(result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void WhengivenParkingLot_IsFull_ShouldReturn_InFormOwner() {
        ParkingLotOwner owner=new ParkingLotOwner();
        parkingLot.registerOwner(owner);
        try {
            parkingLot.park(vehicle);
            parkingLot.unPark(new Object());
        } catch (ParkingLotException e) {
            boolean result=owner.isCapacityFull();
            Assert.assertTrue(result);
        }
    }

}
