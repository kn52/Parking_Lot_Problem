package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.parkinglotdetails.MultiLevelParkingLot;
import com.bridgelabz.parking.lot.parkinglotdetails.ParkingLot;
import com.bridgelabz.parking.lot.parkinglotexception.ParkingLotException;
import com.bridgelabz.parking.lot.parkinglotobservers.AirportSecurity;
import com.bridgelabz.parking.lot.parkinglotobservers.ParkingLotOwner;
import com.bridgelabz.parking.lot.parkingstrategy.DriverType;
import com.bridgelabz.parking.lot.vehicledetails.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingLotTest {

    private ParkingLot parkingLot;
    MultiLevelParkingLot multiLevelParkingLot;
    Vehicle vehicle0;
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    Vehicle vehicle4;
    Vehicle vehicle5;
    Vehicle vehicle6;
    Vehicle vehicle7;
    Vehicle vehicle8;
    Vehicle vehicle9;

    @Before
    public void setUp() {
        parkingLot =new ParkingLot();
        multiLevelParkingLot=new MultiLevelParkingLot();
        vehicle1=new Vehicle(VehicleColor.WHITE,VehicleType.SMALL,1274, VehicleModel.BMW);
        vehicle2=new Vehicle(VehicleColor.BLUE,VehicleType.LARGE,3425,VehicleModel.TOYOTA);
        vehicle3=new Vehicle(VehicleColor.WHITE,VehicleType.LARGE,7454,VehicleModel.TOYOTA);
        vehicle4=new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,9769,VehicleModel.TOYOTA);
        vehicle5=new Vehicle(VehicleColor.BLUE,VehicleType.LARGE,7064,VehicleModel.BMW);
        vehicle6=new Vehicle(VehicleColor.WHITE,VehicleType.SMALL,2534,VehicleModel.TOYOTA);
        vehicle7=new Vehicle(VehicleColor.WHITE,VehicleType.SMALL,8064,VehicleModel.BMW);
        vehicle8=new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,0011,VehicleModel.BMW);
        vehicle9=new Vehicle(VehicleColor.WHITE,VehicleType.LARGE,1079,VehicleModel.BMW);
        vehicle0 =new Vehicle(VehicleColor.BLUE,VehicleType.SMALL,7324,VehicleModel.TOYOTA);
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
            Assert.assertEquals(0,slotNumber);
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
    public void givenVehleIsParked_ShouldReturn_NumberOfSlotsByColor (){
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
            List<Integer> list1=parkingLot1.getSlotDetails(VehicleColor.WHITE);
            List<Integer> list2=parkingLot2.getSlotDetails(VehicleColor.WHITE);
            List<List<Integer>> vehicleDetails=multiLevelParkingLot.getVehicleSlotDetails(VehicleColor.WHITE);
            Assert.assertEquals(list1,vehicleDetails.get(0));
            Assert.assertEquals(list2,vehicleDetails.get(1));
        } catch (ParkingLotException e) { e.printStackTrace(); }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_VehicleInformationByModelAndColor (){
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
        multiLevelParkingLot.parkVehicle(vehicle0, DriverType.NORMAL,"asd");
        try {
            List<VehicleDetails> list1=parkingLot1.getVehicleDetails(VehicleModel.TOYOTA,VehicleColor.WHITE);
            List<VehicleDetails> vehicleDetails=multiLevelParkingLot.getVehicleDetails(VehicleModel.TOYOTA,VehicleColor.WHITE);
            System.out.println(vehicleDetails.get(0));
            Assert.assertEquals(list1.get(0).getVehicleSlot(),vehicleDetails.get(0).getVehicleSlot());
        } catch (ParkingLotException e) { e.printStackTrace(); }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_SlotsOfBMW() {
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
        multiLevelParkingLot.parkVehicle(vehicle0, DriverType.NORMAL, "asd");
        try {
            List<Integer> list1=parkingLot1.getSlotDetails(VehicleModel.BMW);
            List<Integer> list2=parkingLot2.getSlotDetails(VehicleModel.BMW);
            List<List<Integer>> vehicleDetails=multiLevelParkingLot.getVehicleSlotDetails(VehicleModel.BMW);
            Assert.assertEquals(list1,vehicleDetails.get(0));
            Assert.assertEquals(list2,vehicleDetails.get(1));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_VehicleDetailsOnTime() {
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
        multiLevelParkingLot.parkVehicle(vehicle0, DriverType.NORMAL, "asd");
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
        multiLevelParkingLot.parkVehicle(vehicle0, DriverType.NORMAL, "asd");
        try {
            List<Integer> list1=parkingLot1.getSlotDetails(DriverType.HANDICAP,VehicleType.SMALL);
            List<Integer> list2=parkingLot2.getSlotDetails(DriverType.HANDICAP,VehicleType.SMALL);
            List<List<Integer>> vehicleDetails = multiLevelParkingLot.getVehicleSlotDetails(DriverType.HANDICAP,VehicleType.SMALL);
            Assert.assertEquals(list1, vehicleDetails.get(0));
            Assert.assertEquals(list2, vehicleDetails.get(1));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleIsParked_ShouldReturn_AllVehicleLotDetails() {
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
        multiLevelParkingLot.parkVehicle(vehicle0, DriverType.NORMAL, "asd");
        try {
            List<VehicleDetails> vehicleDetails = multiLevelParkingLot.getVehicleDetails(VehicleModel.TOYOTA,VehicleModel.BMW);
            Assert.assertEquals(9769, vehicleDetails.get(0).getVehiclePlateNumber());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleunParked_ShouldReturn_True() {
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
        multiLevelParkingLot.parkVehicle(vehicle0, DriverType.NORMAL, "asd");
        try {
            boolean isParked=multiLevelParkingLot.unParkVehicle(vehicle6);
            Assert.assertEquals(true,isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void gienVehicleunParked_ShouldReturn_False() {
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
        multiLevelParkingLot.parkVehicle(vehicle0, DriverType.NORMAL, "asd");
        try {
            multiLevelParkingLot.unParkVehicle(vehicle1);
            boolean isParked=multiLevelParkingLot.isVehiclePark(vehicle1);
            Assert.assertFalse(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
