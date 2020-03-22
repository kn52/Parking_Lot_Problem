package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.ParkingLot;
import com.bridgelabz.parking.lot.ParkingLotException;
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
    public void WhengivenVehicleunParked_ShouldReturn_True(){
        try{
            parkingLot.park(vehicle);
            boolean result=parkingLot.unPark(vehicle);
            Assert.assertTrue(result);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }
}
