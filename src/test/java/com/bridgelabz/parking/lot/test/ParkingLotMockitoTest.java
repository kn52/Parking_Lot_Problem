package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.DriverType;
import com.bridgelabz.parking.lot.ParkingLot;
import com.bridgelabz.parking.lot.ParkingLotException;
import com.bridgelabz.parking.lot.ParkingLotStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ParkingLotMockitoTest {
    ParkingLot parkingLot;
    ParkingLotStrategy parkingLotStrategy;
    Object vehicle;

    @Before
    public void setUp() throws Exception {
        parkingLot=mock(ParkingLot.class);
        parkingLotStrategy=mock(ParkingLotStrategy.class);
        vehicle=new Object();
    }

    @Test
    public void testMockito_On_getCapacity() {
        try{
            when(parkingLot.getTotalCapacity()).thenReturn(10);
            int n = parkingLot.getTotalCapacity();
            Assert.assertEquals(10, n);
        }catch(ParkingLotException e){
            e.printStackTrace();
        }

    }

    @Test
    public void whengivenVehicleParkedThrow_Exception() {
        try{
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
            doThrow(new ParkingLotException("Parking is Empty", ParkingLotException.ExceptionType.EMPTY_PARKING_LOT))
                    .when(parkingLot).parkVehicle(any(),any(DriverType.class),anyString());
            parkingLot.parkVehicle(new Object(),DriverType.HANDICAP,"asd");
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking is Empty",e.getMessage());
        }
    }

    @Test
    public void whengivenVehicleunParked_ShouldReturn_False() {
        try{
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
            when(parkingLot.unParkVehicle(any())).thenReturn(false);
            boolean isUnParked=parkingLot.unParkVehicle(vehicle);
            Assert.assertFalse(isUnParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whengivenVehicleunParked_Throw_Exception() {
        try{
            parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
            when(parkingLot.unParkVehicle(any()))
                    .thenThrow(new ParkingLotException("Parking lot is empty",ParkingLotException.ExceptionType.EMPTY_PARKING_LOT));
            boolean isUnParked=parkingLot.unParkVehicle(vehicle);
            Assert.assertFalse(isUnParked);
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking lot is empty",e.getMessage());
        }
    }

    @Test
    public void whengivenParkingVehicle_Should_Thow_Exception() {
        parkingLot.parkVehicle(vehicle,DriverType.NORMAL,"asd");
        try {
            when(parkingLot.getSlotNumberByVehicle(any()))
                    .thenThrow(new ParkingLotException("No such slot found in parking lot", ParkingLotException.ExceptionType.NO_SLOT_FOUND));parkingLot.getSlotNumberByVehicle(vehicle);
        } catch (ParkingLotException e) {
            Assert.assertEquals("No such slot found in parking lot",e.getMessage());
        }
    }

    @Test
    public void whengivenDriver_IsHandicap_ShouldReturn_Eleven() {
        ParkingLot parkingLot1=new ParkingLot();
        parkingLot1.setCapacity(10);
        Object vehicle1=new Object();
        Object vehicle2=new Object();
        try{
            parkingLot1.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot1.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
            when(parkingLotStrategy.getVehicleSlot(DriverType.HANDICAP)).thenReturn(11);
            int slot=parkingLotStrategy.getVehicleSlot(DriverType.HANDICAP);
            Assert.assertEquals(11,slot);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }


    @Test
    public void whengivenDriver_IsHandicapWith_setParkingStatergy_ShouldReturn_Eleven() {
        ParkingLot parkingLot1=new ParkingLot();
        parkingLot1.setCapacity(10);
        Object vehicle1=new Object();
        Object vehicle2=new Object();
        try{
            parkingLot1.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot1.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
            parkingLot1.setParkingStrategy(parkingLotStrategy);
            when(parkingLotStrategy.getVehicleSlot(DriverType.HANDICAP)).thenReturn(11);
            int slot=parkingLot1.getSlotNumber(DriverType.HANDICAP, parkingLotStrategy);
            Assert.assertEquals(11,slot);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whenGettingTotal_Empty_Capacity_ShouldReturn_Wrong(){
        ParkingLot parkingLot1=new ParkingLot();
        parkingLot1.setCapacity(3);
        try{
            when(parkingLot.getEmptySlots()).thenReturn(12);
            int emptySlots=parkingLot.getEmptySlots();
            Assert.assertEquals(12,emptySlots);
        }catch(ParkingLotException e){
            e.printStackTrace();
        }
    }
}
