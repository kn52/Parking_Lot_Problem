package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    private ParkingLot parkingLot;
    Object vehicle;

    @Before
    public void setUp() {
        parkingLot =new ParkingLot();
        vehicle=new Object();
    }

    @Test
    public void WhengivenVehicleParked_ShouldReturn_True(){
        try{
            parkingLot.park(vehicle,DriverType.NORMAL,"asd");
            boolean result= parkingLot.isVehicleParked(vehicle);
            Assert.assertTrue(result);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void WhengivenVehicle_AlreadyParked_ShouldReturn_False(){
        try{
            parkingLot.park(vehicle,DriverType.NORMAL,"asd");
            parkingLot.park(vehicle,DriverType.NORMAL,"asd");
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking is full",e.getMessage());
        }
    }

    @Test
    public void WhengivenVehicleunParked_ShouldReturn_True() {
        try {
            parkingLot.park(vehicle,DriverType.NORMAL,"asd");
            boolean result = parkingLot.unPark(vehicle);
            Assert.assertTrue(result);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void WhengivenParkingLot_IsFull_Should_InformFormOwner() {
        ParkingLotOwner owner=new ParkingLotOwner();
        parkingLot.registerObserver(owner);
        try {
            parkingLot.park(vehicle,DriverType.NORMAL,"asd");
            parkingLot.park(new Object(),DriverType.NORMAL,"asd");
        } catch (ParkingLotException e) {
            boolean result=owner.isCapacityFull();
            Assert.assertTrue(result);
        }
    }

    @Test
    public void WhengivenParkingLot_IsFull_Should_InformAirportSecurity() {
        AirportSecurity airportSecurity=new AirportSecurity();
        parkingLot.registerObserver(airportSecurity);
        try {
            parkingLot.park(vehicle,DriverType.NORMAL,"asd");
            parkingLot.park(new Object(),DriverType.NORMAL,"asd");
        } catch (ParkingLotException e) {
            boolean result=airportSecurity.isCapacityFull();
            Assert.assertTrue(result);
        }
    }

    @Test
    public void WhengivenParkingLot_IsFull_AgainAvailable_Should_InformOwner_ReturnFalse() {
        ParkingLotOwner owner=new ParkingLotOwner();
        parkingLot.registerObserver(owner);
        try {
            parkingLot.park(vehicle,DriverType.NORMAL,"asd");
            parkingLot.unPark(vehicle);
            boolean result=owner.isCapacityFull();
            Assert.assertFalse(result);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenParkingLot_ShouldParked_InSlot() {
        parkingLot.setCapacity(2);
        try {
            parkingLot.park(vehicle,DriverType.NORMAL,"asd");
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenParkingVehicle_ShouldReturn_ParkingSlot() {
        parkingLot.park(vehicle,DriverType.NORMAL,"asd");
        try {
            int slotNumber= parkingLot.getVehicleSlot(vehicle);
            Assert.assertEquals(1,slotNumber);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenVehicleParkedONOwnersSlot_ShouldReturn_True() {
        ParkingLotOwner owner=new ParkingLotOwner();
        parkingLot.registerObserver(owner);
        owner.setSlot(1);
        parkingLot.park(vehicle,DriverType.NORMAL,"asd");
        try {
            boolean result=owner.getSlotStatus();
            Assert.assertTrue(result);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }


    @Test
    public void whengivenVehicleParked_ShouldEvenlyDistributed() {
        parkingLot.setCapacity(5);
        int number = parkingLot.getTotalCapacity();
        Assert.assertEquals(5 , number);
    }

    @Test
    public void givenVehicleWithHandicappedDriver_ShouldParkVehicleAtNearestFreeSpace() {
        parkingLot.setCapacity(3);
        Object vehicle1=new Object();
        ParkingLotStrategy parkingLotStrategy=new ParkingLotStrategy();
        try {
            parkingLot.park(vehicle1,DriverType.NORMAL,"asd");
            int slotNumber=parkingLotStrategy.getVehicleSlot(DriverType.HANDICAP);
            Assert.assertEquals(1,slotNumber);
        } catch (ParkingLotException e) {
        }
    }
}
