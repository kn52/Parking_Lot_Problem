package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.parkingstrategy.DriverType;
import com.bridgelabz.parking.lot.parkinglotdetails.ParkingLot;
import com.bridgelabz.parking.lot.parkinglotdetails.SlotDetails;
import com.bridgelabz.parking.lot.parkinglotdetails.MultiLevelParkingLot;
import com.bridgelabz.parking.lot.vehicledetails.Vehicle;
import com.bridgelabz.parking.lot.vehicledetails.VehicleColor;
import com.bridgelabz.parking.lot.vehicledetails.VehicleModel;
import com.bridgelabz.parking.lot.vehicledetails.VehicleType;
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
       vehicle=new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,1223,VehicleModel.BMW);
    }

    @Test
    public void whengiven_VehicleParked_InSlot() {
        slot.setVehicleSlot(vehicle,1,"ASD");
        int i=slot.getSlot(vehicle);
        Assert.assertEquals(1,i);
    }

    @Test
    public void testMethod() {
        ParkingLot p1=new ParkingLot();
        ParkingLot p2=new ParkingLot();
        p1.setCapacity(10);
        p2.setCapacity(20);
        pt.addLot(p1);
        pt.addLot(p2);
        pt.parkVehicle(vehicle, DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,3456,VehicleModel.TOYOTA), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,1123,VehicleModel.BMW), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,8363,VehicleModel.BMW), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,3456,VehicleModel.TOYOTA), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,6753,VehicleModel.TOYOTA), DriverType.NORMAL,"asd");
        pt.parkVehicle(new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,5743, VehicleModel.BMW), DriverType.NORMAL,"asd");
    }
}
