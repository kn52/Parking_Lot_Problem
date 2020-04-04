package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.details.MultiLevelParkingLot;
import com.bridgelabz.parking.lot.details.ParkingLot;
import com.bridgelabz.parking.lot.exception.ParkingLotException;
import com.bridgelabz.parking.lot.observer.AirportSecurity;
import com.bridgelabz.parking.lot.observer.ParkingLotOwner;
import com.bridgelabz.parking.lot.strategy.DriverType;
import com.bridgelabz.parking.lot.vehicle.Vehicle;
import com.bridgelabz.parking.lot.vehicle.VehicleDetails;
import com.bridgelabz.parking.lot.vehicle.VehicleType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingLotTest {

    private ParkingLot parkingLot;
    MultiLevelParkingLot multiLevelParkingLot;
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    Vehicle vehicle4;
    Vehicle vehicle5;
    Vehicle vehicle6;
    Vehicle vehicle7;
    Vehicle vehicle8;
    Vehicle vehicle9;
    Vehicle vehicle10;

    @Before
    public void setUp() {
        parkingLot =new ParkingLot();
        multiLevelParkingLot=new MultiLevelParkingLot();
        vehicle1=new Vehicle("BLACK",VehicleType.SMALL,1274,"BMW");
        vehicle2=new Vehicle("BLUE",VehicleType.LARGE,3425,"TOYOTA");
        vehicle3=new Vehicle("WHITE",VehicleType.LARGE,7454,"TOYOTA");
        vehicle4=new Vehicle("BLUE",VehicleType.SMALL,9769,"TOYOTA");
        vehicle5=new Vehicle("BLUE",VehicleType.LARGE,7064,"BMW");
        vehicle6=new Vehicle("WHITE",VehicleType.SMALL,2534,"TOYOTA");
        vehicle7=new Vehicle("WHITE",VehicleType.SMALL,8064,"BMW");
        vehicle8=new Vehicle("BLUE",VehicleType.SMALL,0011,"BMW");
        vehicle9=new Vehicle("WHITE",VehicleType.LARGE,1079,"BMW");
        vehicle10=new Vehicle("BLUE",VehicleType.SMALL,7324,"TOYOTA");
    }

    @Test
    public void WhengivenVehicle_ShouldReturn_True(){
        try{
            parkingLot.parkVehicle(vehicle1, DriverType.NORMAL,"asd");
            boolean isParked= parkingLot.isVehicleParked(vehicle1);
            Assert.assertTrue(isParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void WhengivenVehicleParked_ShouldReturn_True(){
        try{
            parkingLot.parkVehicle(vehicle1, DriverType.NORMAL,"asd");
            boolean isParked= parkingLot.isVehicleParked(vehicle1);
            Assert.assertTrue(isParked);
        }catch (ParkingLotException e){
            e.printStackTrace();
        }
    }

    @Test
    public void WhengivenVehicle_AlreadyParked_ShouldReturn_False(){
        try{
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
        }catch (ParkingLotException e){
            Assert.assertEquals("Already Parked",e.getMessage());
        }
    }

    @Test
    public void WhengivenVehicleunParked_ShouldReturn_True() {
        try {
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            boolean unParked = parkingLot.unParkVehicle(vehicle1);
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
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
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
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
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
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
            parkingLot.unParkVehicle(vehicle1);
            boolean isAvailable=owner.getSlotStatus();
            Assert.assertFalse(isAvailable);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenParkingLot_ShouldParked_InSlot() {
        parkingLot.setCapacity(2);
        try {
            parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenParkingVehicle_ShouldReturn_ParkingSlot() {
        parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
        try {
            int slotNumber= parkingLot.getSlotNumberByVehicle(vehicle1);
            Assert.assertEquals(1,slotNumber);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }

    @Test
    public void whengivenVehicleParkedOnOwnersSlot_ShouldReturn_True() {
        ParkingLotOwner owner=new ParkingLotOwner();
        parkingLot.registerObserver(owner);
        owner.setOwnerSlot(0);
        parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
        parkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
        try {
            boolean slotStatus=owner.getOwnerSlotStatus();
            Assert.assertTrue(slotStatus);
        } catch (ParkingLotException e) { e.printStackTrace();  }
    }


    @Test
    public void whengivenVehicleParked_ShouldEvenlyDistributed() {
        parkingLot.setCapacity(5);
	    parkingLot.parkVehicle(vehicle1,DriverType.NORMAL,"asd");
	    parkingLot.parkVehicle(vehicle2,DriverType.NORMAL,"asd");
        int totalCapacity = parkingLot.getTotalCapacity();
        Assert.assertEquals(5 , totalCapacity);
    }

    @Test
    public void givenVehicleWithHandicappedDriver_ShouldParkVehicleAtNearestFreeSpace() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1=new ParkingLot();
        ParkingLot parkingLot2=new ParkingLot();

        parkingLot1.setCapacity(20);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(2);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL,"asd");
        try {
            boolean isParked= multiLevelParkingLot.isVehiclePark(vehicle5);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {  }

    }

    @Test
    public void givenVehicleIs_Large_ShouldBe_ParkedAt_LargestSpaceLot() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1=new ParkingLot();
        ParkingLot parkingLot2=new ParkingLot();

        parkingLot1.setCapacity(20);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(2);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL,"asd");

        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL,"asd");
        try {
            boolean isParked= multiLevelParkingLot.isVehiclePark(vehicle5);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) { e.printStackTrace(); }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_NumberOfSlotsByColor (){
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1=new ParkingLot();
        ParkingLot parkingLot2=new ParkingLot();
        parkingLot1.setCapacity(4);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(2);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL,"asd");
        try {
            List<Integer> list1=parkingLot1.getVehicleDetailsByVehicleColor("WHITE");
            List<Integer> list2=parkingLot2.getVehicleDetailsByVehicleColor("WHITE");
            List<List<Integer>> vehicleDetails=multiLevelParkingLot.getVehicleDetailsByVehicleColor("WHITE");
            Assert.assertEquals(list1,vehicleDetails.get(0));
            Assert.assertEquals(list2,vehicleDetails.get(1));
        } catch (ParkingLotException e) { e.printStackTrace(); }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_VehicleInformationByModelAndColor (){
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1=new ParkingLot();
        ParkingLot parkingLot2=new ParkingLot();
        parkingLot1.setCapacity(7);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(8);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle7, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle8, DriverType.NORMAL,"asd");
        multiLevelParkingLot.parkVehicle(vehicle9, DriverType.HANDICAP,"asd");
        multiLevelParkingLot.parkVehicle(vehicle10, DriverType.NORMAL,"asd");
        try {
            List<VehicleDetails> list1=parkingLot1.getVehicleDetailsByVehicleModelAndColor("TOYOTA","BLUE");
            List<VehicleDetails> list2=parkingLot2.getVehicleDetailsByVehicleModelAndColor("TOYOTA","BLUE");
            List<VehicleDetails> vehicleDetails=multiLevelParkingLot.getVehicleDetailsByVehicleModelAndColor("TOYOTA","BLUE");
            Assert.assertEquals(list1.get(0).getVehicleSlot(),vehicleDetails.get(0).getVehicleSlot());
            Assert.assertEquals(list2.get(0).getVehicleSlot(),vehicleDetails.get(1).getVehicleSlot());
        } catch (ParkingLotException e) { e.printStackTrace(); }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_SlotsOfBMW() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(7);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(8);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle7, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle8, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle9, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle10, DriverType.NORMAL, "asd");
        try {
            int vehicleDetailsSize = 0;
            vehicleDetailsSize = multiLevelParkingLot.getVehicleDetailsByVehicleModel("BMW").size();
            Assert.assertEquals(5, vehicleDetailsSize);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_VehicleDetailsOnTime() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(7);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(8);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle7, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle8, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle9, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle10, DriverType.NORMAL, "asd");
        try {
            int vehicleDetailsSize = 0;
            vehicleDetailsSize = multiLevelParkingLot.getVehicleDetailsByTime(LocalDateTime.now()).size();
            Assert.assertEquals(10, vehicleDetailsSize);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_HandicapVehicleLotDetails() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(7);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(8);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle7, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle8, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle9, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle10, DriverType.NORMAL, "asd");
        try {
            int vehicleDetailsSize = 0;
            vehicleDetailsSize = multiLevelParkingLot.getVehicleHandiCapSlotDetails().size();
            Assert.assertEquals(8, vehicleDetailsSize);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_AllVehicleLotDetails() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(7);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(8);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle7, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle8, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle9, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle10, DriverType.NORMAL, "asd");
        try {
            int vehicleDetailsSize = 0;
            vehicleDetailsSize = multiLevelParkingLot.getAllVehicleDetails().size();
            Assert.assertEquals(10, vehicleDetailsSize);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleunParked_ShouldReturn_True() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(7);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(8);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle7, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle8, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle9, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle10, DriverType.NORMAL, "asd");
        try {
            boolean isParked=multiLevelParkingLot.unParkVehicle(vehicle6);
            Assert.assertEquals(true,isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gienVehicleunParked_ShouldReturn_False() {
        multiLevelParkingLot.setCapacity(2);
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setCapacity(7);
        multiLevelParkingLot.addLot(parkingLot1);

        parkingLot2.setCapacity(8);
        multiLevelParkingLot.addLot(parkingLot2);

        multiLevelParkingLot.parkVehicle(vehicle1, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle2, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle3, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle4, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle5, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle6, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle7, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle8, DriverType.NORMAL, "asd");
        multiLevelParkingLot.parkVehicle(vehicle9, DriverType.HANDICAP, "asd");
        multiLevelParkingLot.parkVehicle(vehicle10, DriverType.NORMAL, "asd");
        try {
            multiLevelParkingLot.unParkVehicle(vehicle1);
            boolean isParked=multiLevelParkingLot.isVehiclePark(vehicle1);
            Assert.assertFalse(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
