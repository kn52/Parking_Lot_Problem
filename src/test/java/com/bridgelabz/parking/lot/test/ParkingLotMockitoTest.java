package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.details.MultiLevelParkingLot;
import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.exception.ParkingLotException;
import com.bridgelabz.parking.lot.strategy.DriverType;
import com.bridgelabz.parking.lot.strategy.ParkingLotStrategy;
import com.bridgelabz.parking.lot.vehicle.Vehicle;
import com.bridgelabz.parking.lot.vehicle.VehicleType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ParkingLotMockitoTest {

    ParkingLot parkingLot;
    ParkingLotStrategy parkingLotStrategy;
    MultiLevelParkingLot multiLevelParkingLot;
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;

    @Before
    public void setUp() {
        multiLevelParkingLot=new MultiLevelParkingLot();
        parkingLot=new ParkingLot();
        parkingLotStrategy=mock(ParkingLotStrategy.class);
        vehicle1=new Vehicle("BLACK",VehicleType.SMALL,1274,"BMW");
        vehicle2=new Vehicle("BLUE",VehicleType.LARGE,3425,"TOYOTA");
        vehicle3=new Vehicle("WHITE",VehicleType.LARGE,7454,"TOYOTA");
    }

    @Test
    public void whengivenVehicle_Parked_ShouldReturnFalse() {
        ParkingLot parkingLot1=mock(ParkingLot.class);
        try{
            multiLevelParkingLot.parkVehicle(vehicle1, DriverType.NORMAL,"asd");
            when(parkingLot1.isVehicleParked(any())).thenReturn(false);
            boolean isParked=multiLevelParkingLot.isVehiclePark(vehicle1);
            Assert.assertFalse(isParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whengivenDriver_IsHandicap_ShouldReturn_Eleven() {
        ParkingLot parkingLot1=mock(ParkingLot.class);
        parkingLot1.setCapacity(10);
        multiLevelParkingLot.addLot(parkingLot1);
        try{
            multiLevelParkingLot.parkVehicle(vehicle1, DriverType.NORMAL,"asd");
            multiLevelParkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
            when(parkingLot1.getSlotNumberByVehicle(any())).thenReturn(5);
            int slot=multiLevelParkingLot.getVehicleSlot(vehicle1);
            Assert.assertEquals(5,slot);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whengivenDriver_IsHandicapWith_setParkingStrategy_ShouldReturn_Nine() {
        parkingLot.setCapacity(10);
        multiLevelParkingLot.addLot(parkingLot);
        try{
            multiLevelParkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            multiLevelParkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
            parkingLot.setParkingStrategy(parkingLotStrategy);
            when(parkingLotStrategy.getVehicleSlot(any(), any(),anyList())).thenReturn(9);
            int slot=parkingLot.getSlotNumber(vehicle3, DriverType.HANDICAP, parkingLotStrategy);
            Assert.assertEquals(9,slot);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void whengivenDriver_IsHandicapWith_setParkingStrategy_ThrowException() {
        parkingLot.setCapacity(10);
        try {
            multiLevelParkingLot.parkVehicle(vehicle1, DriverType.NORMAL, "asd");
            multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "asd");
            parkingLot.setParkingStrategy(parkingLotStrategy);
            when(parkingLotStrategy.getVehicleSlot(any(), any(),anyList()))
                    .thenThrow(new ParkingLotException("No parking lot found", ParkingLotException.ExceptionType.NO_SLOT_AVAILABLE));
        } catch (Exception e) {
            parkingLot.getSlotNumber(vehicle3, DriverType.HANDICAP, parkingLotStrategy);
            Assert.assertEquals("No parking lot found", e.getMessage());
        }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_getVehicleDetailsByVehicleColor() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        multiLevelParkingLot.addLot(parkingLot1);
        List<Integer>  list=new ArrayList(Arrays.asList(1,2,3,4,1));
        try {
            when(parkingLot1.getVehicleDetailsByVehicleColor(anyString())).thenReturn(list);
            List<Integer> vehicleDetailsSize = multiLevelParkingLot.getVehicleDetailsByVehicleColor("WHITE");
            Assert.assertEquals(list.size(), vehicleDetailsSize.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleIsParked_getVehicleDetailsByVehicleColor_ThrowException() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        multiLevelParkingLot.addLot(parkingLot1);
        List<Integer>  list=new ArrayList(Arrays.asList(1,2,3,4,1));
        try {
            when(parkingLot1.getVehicleDetailsByVehicleColor(anyString()))
                    .thenThrow(IllegalArgumentException.class);
            multiLevelParkingLot.getVehicleDetailsByVehicleColor("WHITE");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_AllVehicleDetails() {
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        multiLevelParkingLot.addLot(parkingLot1);
        Vehicle vehicle7=new Vehicle("WHITE",VehicleType.SMALL,8064,"BMW");
        Vehicle vehicle8=new Vehicle("BLUE",VehicleType.SMALL,0011,"BMW");
        Vehicle vehicle9=new Vehicle("WHITE",VehicleType.LARGE,1079,"BMW");
        Vehicle vehicle10=new Vehicle("BLUE",VehicleType.SMALL,7324,"TOYOTA");
        List<Vehicle>  list=new ArrayList(Arrays.asList(vehicle7,vehicle8,vehicle9,vehicle10));
        try {
            when(parkingLot1.getAllVehicleDetails()).thenReturn(list);
            List<Vehicle> vehicleDetailsSize = multiLevelParkingLot.getAllVehicleDetails();
            Assert.assertEquals(list.size(), vehicleDetailsSize.size());
            Assert.assertEquals(list.get(2), vehicleDetailsSize.get(2));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
