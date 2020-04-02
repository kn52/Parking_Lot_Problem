package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.strategy.DriverType;
import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.details.SlotDetails;
import com.bridgelabz.parking.lot.details.MultiLevelParkingLot;
import com.bridgelabz.parking.lot.vehicle.Vehicle;
import com.bridgelabz.parking.lot.vehicle.VehicleType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlotTest {
    SlotDetails slot;
    MultiLevelParkingLot pt;
    Vehicle vehicle;
    @Before
    public void setUp() throws Exception {
       slot=new SlotDetails();
       pt=new MultiLevelParkingLot();
       vehicle=new Vehicle("BLACK",VehicleType.SMALL,1223,"BMW");
    }

    @Test
    public void whengiven_VehicleParked_InSlot() {
        slot.setVehicleSlot(vehicle,1,"ASD");
        int i=slot.getSlot(vehicle);
        Assert.assertEquals(1,i);
    }

    @Test
    public void testMethod() {
        pt.setCapacity(2);
        ParkingLot p1=new ParkingLot();
        ParkingLot p2=new ParkingLot();
        p1.setCapacity(10);
        p2.setCapacity(20);
        pt.addLot(p1);
        pt.addLot(p2);
        pt.parkVehicle(vehicle, DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle("BLACK",VehicleType.SMALL,3456,"TOYOTA"), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle("BLACK",VehicleType.SMALL,1123,"BMW"), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle("BLACK",VehicleType.SMALL,8363,"BMW"), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle("BLACK",VehicleType.SMALL,3456,"TOYOTA"), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle("BLACK",VehicleType.SMALL,6753,"TOYOTA"), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle("BLACK",VehicleType.SMALL,5743,"BMW"), DriverType.NORMAL,"asd");
    }
}
