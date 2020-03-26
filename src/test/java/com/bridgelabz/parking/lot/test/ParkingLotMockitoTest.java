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
    public void whengivenVehicleParkedThow_Exception() {
        try{
            doThrow(new ParkingLotException("Parking is full", ParkingLotException.ExceptionType.NO_PARKING))
                    .when(parkingLot).parkVehicle(any(),any(DriverType.class),anyString());
            parkingLot.parkVehicle(new Object(),DriverType.NORMAL,"asd");
        }catch (ParkingLotException e){
            Assert.assertEquals("Parking is full",e.getMessage());
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
