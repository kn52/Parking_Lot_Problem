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
        vehicle1=new Vehicle("BLACK",VehicleType.SMALL,4563,"TOYOTA");
        vehicle2=new Vehicle("BLACK",VehicleType.SMALL,7854,"BMW");
        vehicle3=new Vehicle("BLACK",VehicleType.SMALL,7953,"TOYOTA");
    }

    @Test
    public void whengivenDriver_IsHandicap_ShouldReturn_Eleven() {
        parkingLot.setCapacity(10);
        multiLevelParkingLot.addLot(parkingLot);
        try{
            multiLevelParkingLot.parkVehicle(vehicle1, DriverType.NORMAL,"asd");
            multiLevelParkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
            when(parkingLotStrategy.getVehicleSlot(any(), any(),anyList())).thenReturn(11);
            int slot=multiLevelParkingLot.getVehicleSlot(vehicle1);
            Assert.assertEquals(11,slot);
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
