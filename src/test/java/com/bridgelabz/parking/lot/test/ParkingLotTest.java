package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.AirportSecurity;
import com.bridgelabz.parking.lot.ParkingLot;
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
            boolean result=parkingLot.park("A");
            Assert.assertTrue(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenGivenVehicleUnParked_ShouldReturnTrue() {
        try{
            parkingLot.park("A");
            boolean result=parkingLot.unPark("A");
            Assert.assertTrue(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenParkingLot_IsFull_ShouldReturnTrue() {
        try{
            parkingLot.park("A");
            parkingLot.park("B");
            parkingLot.park("C");
            boolean result=parkingLot.parkingLotIsFull();
            Assert.assertTrue(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenParkingLot_IsFull_InformedToAirportSecurity_ShouldReturnTrue() {
        try{
            parkingLot.park("A");
            parkingLot.park("B");
            parkingLot.park("C");
            boolean result=parkingLot.getAirportSecurityInformed();
            Assert.assertTrue(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
