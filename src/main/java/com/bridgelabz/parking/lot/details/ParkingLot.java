package com.bridgelabz.parking.lot.details;

import com.bridgelabz.parking.lot.exception.ParkingLotException;
import com.bridgelabz.parking.lot.observer.ParkingLotInformer;
import com.bridgelabz.parking.lot.observer.IParkingLotObserver;
import com.bridgelabz.parking.lot.observer.ParkingLotOwner;
import com.bridgelabz.parking.lot.strategy.DriverType;
import com.bridgelabz.parking.lot.strategy.ParkingLotStrategy;
import com.bridgelabz.parking.lot.vehicle.Vehicle;
import com.bridgelabz.parking.lot.vehicle.VehicleDetails;
import com.bridgelabz.parking.lot.vehicle.VehicleType;
import sun.jvm.hotspot.oops.Array;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class ParkingLot {

    private List<SlotDetails> parkingLotList;
    private int noOfFullSlots;
    private ParkingLotInformer observers;
    private int PARKING_LOT_SIZE = 2;
    private ParkingLotStrategy parkingStrategy;

    public ParkingLot() {
        this.noOfFullSlots = 0;
        this.observers=new ParkingLotInformer();
        this.parkingLotList = new ArrayList<>();
        this.parkingStrategy = new ParkingLotStrategy();
        this.initializeParkingLot();
    }

    public void parkVehicle(Vehicle vehicle, DriverType type, String name) {
        this.getEmptySlots();
        if(this.isVehicleParked(vehicle))
            throw new ParkingLotException("Already Parked", ParkingLotException.ExceptionType.ALREADY_PARKED);
        int slotnumber=this.getSlotNumber(vehicle,type,parkingStrategy);
        SlotDetails slot=new SlotDetails(vehicle,type,slotnumber,name);
        this.parkingLotList.set(slotnumber,slot);
        this.noOfFullSlots++;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        if(this.parkingLotList.stream().filter(x-> x.getVehicle() == vehicle).findFirst().isPresent())
        {
            this.parkingLotList.set(this.getSlotNumberByVehicle(vehicle),null);
            observers.InformObserversEmptySlotsAvailable();
            this.noOfFullSlots--;
            return true;
        }
        return false;
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        boolean value=parkingLotList.stream().filter(x-> x.getVehicle() == vehicle).findFirst().isPresent();
        return value;
    }

    private void initializeParkingLot() {
        int size=this.PARKING_LOT_SIZE;
        this.parkingLotList.clear();
        IntStream.range(0,size).forEach(lot->parkingLotList.add(new SlotDetails(null)));
    }

    public int getSlotNumber(Vehicle vehicle, DriverType type, ParkingLotStrategy parkingStrategy) {
        int slotnumber=parkingStrategy.getVehicleSlot(vehicle,type,this.parkingLotList);
        if(slotnumber == ParkingLotOwner.slotNumber)
            observers.InformOwner();
        return slotnumber;
    }

    public void registerObserver(IParkingLotObserver observer) {
        observers.addObserver(observer);
    }

    public void setCapacity(int capacity) {
        this.PARKING_LOT_SIZE = capacity;
        this.initializeParkingLot();
    }
    public int getTotalCapacity() {
        return this.PARKING_LOT_SIZE;
    }

    public int getSlotNumberByVehicle(Vehicle vehicle) {
        return this.parkingLotList.stream().filter(x-> x.getVehicle() == vehicle).findFirst().get().getVehicleSlot();
    }

    public int getEmptySlots() {
        if((this.PARKING_LOT_SIZE-this.noOfFullSlots)==0)
            observers.InformObserversNoEmptySlotsAvailable();
        return this.PARKING_LOT_SIZE-this.noOfFullSlots;
    }

    public void setParkingStrategy(ParkingLotStrategy parkingLotStrategy) {
        this.parkingStrategy=parkingLotStrategy;
    }

    public List<Integer> getVehicleDetailsByVehicleColor(String vehicleColor) {
        List<Integer> vehicleList=new ArrayList<>();
        for(int i=0;i<this.PARKING_LOT_SIZE;i++){
            SlotDetails v=this.parkingLotList.get(i);
            if( v.getVehicle()!= null && v.getVehicle().getVehicleColor().equals(vehicleColor))
                vehicleList.add(v.getVehicleSlot());
        }
        return vehicleList;
    }

    public List<VehicleDetails> getVehicleDetailsByVehicleModelAndColor(String vehicleModel, String vehicleColor) {
        List<VehicleDetails> details=new ArrayList<>();
        for(int i=0;i<this.PARKING_LOT_SIZE;i++) {
            SlotDetails v = this.parkingLotList.get(i);
            if (v.getVehicle()!=null && v.getVehicle().getVehicleModel().equals(vehicleModel) && v.getVehicle().getVehicleColor().equals(vehicleColor))
                details.add(new VehicleDetails(v.getVehicleSlot(), v.getVehicle().getVehiclePlateNumber(), v.getAttendantName()));
        }
        return details;
    }

    public List<Integer> getVehicleDetailsByModelColor(String vehicleModel) {
        List<Integer> vehicleList=new ArrayList<>();
        for(int i=0;i<this.PARKING_LOT_SIZE;i++){
            SlotDetails v=this.parkingLotList.get(i);
            if( v.getVehicle()!= null && v.getVehicle().getVehicleModel().equals(vehicleModel))
                vehicleList.add(v.getVehicleSlot());
        }
        return vehicleList;
    }

    public List<Vehicle> getVehicleDetailsByTime(LocalDateTime localDateTime) {
        List<Vehicle> vehicleList=new ArrayList<>();
        for(int i=0;i<this.PARKING_LOT_SIZE;i++){
            SlotDetails v=this.parkingLotList.get(i);
            if( v.getVehicle()!= null && localDateTime.getMinute()-v.getParkingTime().getMinute()<30)
                vehicleList.add(v.getVehicle());
        }
        return vehicleList;
    }

    public List<Integer> getVehicleHandiCapSlotDetails() {
        List<Integer> handicapSlotNumbers=new ArrayList<>(Arrays.asList(1,2,3,4));
        return handicapSlotNumbers;
    }

    public List<Vehicle> getAllVehicleDetails() {
        List<Vehicle> vehicleList=new ArrayList<>();
        for(int i=0;i<this.PARKING_LOT_SIZE;i++){
            SlotDetails v=this.parkingLotList.get(i);
            if( v.getVehicle()!= null)
                vehicleList.add(v.getVehicle());
        }
        return vehicleList;
    }
}