package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.details.MultiLevelParkingLot;
import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.exception.ParkingLotException;
import com.bridgelabz.parking.lot.observer.AirportSecurity;
import com.bridgelabz.parking.lot.observer.ParkingLotOwner;
import com.bridgelabz.parking.lot.strategy.DriverType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    private ParkingLot parkingLot;
    MultiLevelParkingLot multiLevelParkingLot;
    Object vehicle;

    @Before
    public void setUp() {
        parkingLot =new ParkingLot();
        multiLevelParkingLot =new MultiLevelParkingLot();
        vehicle=new Object();
    }

    @Test
    public void WhengivenVehicleParked_ShouldReturn_True(){
        try{
            parkingLot.parkVehicle(vehicle, DriverType.NORMAL,"asd");
            boolean isParked= parkingLot.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void WhengivenVehicle_AlreadyParked_ShouldReturn_False(){
        try{
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking is full",e.getMessage());
        }
    }

    @Test
    public void WhengivenVehicleunParked_ShouldReturn_True() {
        try {
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
            boolean unParked = parkingLot.unParkVehicle(vehicle);
            Assert.assertTrue(unParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void WhengivenParkingLot_IsFull_Should_InformFormOwner() {
        ParkingLotOwner owner=new ParkingLotOwner();
        parkingLot.registerObserver(owner);
        try {
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(new Object(),DriverType.NORMAL,"asd");
        } catch (ParkingLotException e) {
            boolean isFull=owner.isCapacityFull();
            Assert.assertTrue(isFull);
        }
    }

    @Test
    public void WhengivenParkingLot_IsFull_Should_InformAirportSecurity() {
        AirportSecurity airportSecurity=new AirportSecurity();
        parkingLot.registerObserver(airportSecurity);
        try {
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(new Object(),DriverType.NORMAL,"asd");
        } catch (ParkingLotException e) {
            boolean isFull=airportSecurity.isCapacityFull();
            Assert.assertTrue(isFull);
        }
    }

    @Test
    public void WhengivenParkingLot_IsFull_AgainAvailable_Should_InformOwner_ReturnFalse() {
        ParkingLotOwner owner=new ParkingLotOwner();
        parkingLot.registerObserver(owner);
        try {
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
            parkingLot.unParkVehicle(vehicle);
            boolean isAvailable=owner.getSlotStatus();
            Assert.assertFalse(isAvailable);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenParkingLot_ShouldParked_InSlot() {
        parkingLot.setCapacity(2);
        try {
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenParkingVehicle_ShouldReturn_ParkingSlot() {
        parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
        try {
            int slotNumber= parkingLot.getSlotNumberByVehicle(vehicle);
            Assert.assertEquals(0,slotNumber);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenVehicleParkedOnOwnersSlot_ShouldReturn_True() {
        ParkingLotOwner owner=new ParkingLotOwner();
        parkingLot.registerObserver(owner);
        owner.setOwnerSlot(0);
        parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
        try {
            boolean slotStatus=owner.getOwnerSlotStatus();
            Assert.assertTrue(slotStatus);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }


    @Test
    public void whengivenVehicleParked_ShouldEvenlyDistributed() {
        parkingLot.setCapacity(5);
        Object vehicle1=new Object();
	    parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
	    parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
        int totalCapacity = parkingLot.getTotalCapacity();
        Assert.assertEquals(5 , totalCapacity);
    }

    @Test
    public void givenVehicleWithHandicappedDriver_ShouldParkVehicleAtNearestFreeSpace() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1=new ParkingLot();
        parkingLot1.setCapacity(10);
        ParkingLot parkingLot2=new ParkingLot();
        parkingLot2.setCapacity(20);
        Object vehicle1=new Object();
        multiLevelParkingLot.addLot(parkingLot1);
        multiLevelParkingLot.addLot(parkingLot2);
        multiLevelParkingLot.parkVehicle(vehicle, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(new Object(), DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(new Object(), DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(new Object(), DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(new Object(), DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.NORMAL,"asd");
        try {
            boolean isParked= multiLevelParkingLot.isVehiclePark(vehicle1);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {  }
    }
}
