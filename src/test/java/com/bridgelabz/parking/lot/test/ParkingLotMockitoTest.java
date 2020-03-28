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
    public void setUp() {
        parkingLot=new ParkingLot();
        parkingLotStrategy=mock(ParkingLotStrategy.class);
        vehicle=new Object();
    }

    @Test
    public void whengivenDriver_IsHandicap_ShouldReturn_Eleven() {
        parkingLot.setCapacity(10);
        Object vehicle1=new Object();
        Object vehicle2=new Object();
        try{
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
            when(parkingLotStrategy.getVehicleSlot(DriverType.HANDICAP)).thenReturn(9);
            int slot=parkingLotStrategy.getVehicleSlot(DriverType.HANDICAP);
            Assert.assertEquals(9,slot);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whengivenDriver_IsHandicapWith_setParkingStatergy_ShouldReturn_Nine() {
        parkingLot.setCapacity(10);
        Object vehicle1=new Object();
        Object vehicle2=new Object();
        try{
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
            parkingLot.setParkingStrategy(parkingLotStrategy);
            when(parkingLotStrategy.getVehicleSlot(DriverType.HANDICAP)).thenReturn(9);
            int slot=parkingLot.getSlotNumber(DriverType.HANDICAP, parkingLotStrategy);
            Assert.assertEquals(9,slot);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whengivenDriver_IsHandicapWith_setParkingStatergy_ThrowException() {
        parkingLot.setCapacity(10);
        Object vehicle1=new Object();
        Object vehicle2=new Object();
        try{
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
            parkingLot.setParkingStrategy(parkingLotStrategy);
            when(parkingLotStrategy.getVehicleSlot(DriverType.HANDICAP))
                    .thenThrow(new ParkingLotException("No empty slot found", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE));
        }catch(Exception e){
            parkingLot.getSlotNumber(DriverType.HANDICAP, parkingLotStrategy);
            Assert.assertEquals("No empty slot found",e.getMessage());
        }
    }
}
