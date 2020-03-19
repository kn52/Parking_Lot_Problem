package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.ParkingLot;
import com.bridgelabz.parking.lot.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    ParkingLot parkingLot;
    @Before
    public void setUp() throws Exception {
        parkingLot=new ParkingLot();
    }

    @Test
    public void whenGivenVehicleParked_ShouldReturnTrue() {
        try{
            boolean result=parkingLot.park(new Vehicle("A"));
            Assert.assertTrue(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenGivenVehicleUnParked_ShouldReturnTrue() {
        try{
            boolean result=parkingLot.unPark();
            Assert.assertTrue(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
