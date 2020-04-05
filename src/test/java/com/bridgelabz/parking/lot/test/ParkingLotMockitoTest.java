package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.parkinglotdetails.MultiLevelParkingLot;
import com.bridgelabz.parking.lot.parkinglotdetails.ParkingLot;
import com.bridgelabz.parking.lot.parkinglotexception.ParkingLotException;
import com.bridgelabz.parking.lot.parkingstrategy.DriverType;
import com.bridgelabz.parking.lot.parkingstrategy.ParkingLotStrategy;
import com.bridgelabz.parking.lot.vehicledetails.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        vehicle1=new Vehicle(VehicleColor.WHITE,VehicleType.SMALL,1274, VehicleModel.BMW);
        vehicle2=new Vehicle(VehicleColor.BLUE,VehicleType.LARGE,3425,VehicleModel.TOYOTA);
        vehicle3=new Vehicle(VehicleColor.WHITE,VehicleType.LARGE,7454,VehicleModel.TOYOTA);

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

}
