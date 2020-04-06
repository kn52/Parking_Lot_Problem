package com.bridgelabz.parking.lot.parkinglotdetails;

import com.bridgelabz.parking.lot.parkinglotexception.ParkingLotException;
import com.bridgelabz.parking.lot.parkinglotobservers.ParkingLotInformer;
import com.bridgelabz.parking.lot.parkinglotobservers.IParkingLotObserver;
import com.bridgelabz.parking.lot.parkinglotobservers.ParkingLotOwner;
import com.bridgelabz.parking.lot.parkingstrategy.DriverType;
import com.bridgelabz.parking.lot.parkingstrategy.ParkingLotStrategy;
import com.bridgelabz.parking.lot.vehicledetails.IPredicate;
import com.bridgelabz.parking.lot.vehicledetails.Vehicle;
import com.bridgelabz.parking.lot.vehicledetails.VehicleDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
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
        if(this.parkingLotList.stream().filter(x->x.getVehicle() == vehicle).findFirst().isPresent())
        {
            this.parkingLotList.set(this.getSlotNumberByVehicle(vehicle),new SlotDetails(null));
            observers.InformObserversEmptySlotsAvailable();
            this.noOfFullSlots--;
            return true;
        }
        return false;
    }

    public boolean isVehicleParked(Vehicle vehicle) {
        boolean value=parkingLotList.stream().filter(x->x.getVehicle() == vehicle).findFirst().isPresent();
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
        return this.PARKING_LOT_SIZE-this.noOfFullSlots;
    }

    public void setParkingStrategy(ParkingLotStrategy parkingLotStrategy) {
        this.parkingStrategy=parkingLotStrategy;
    }

    public List<Vehicle> getVehicleDetailsByTime(LocalDateTime localDateTime) {
        List<Vehicle> vehicleList=this.parkingLotList.stream()
                .filter(lots->lots.getVehicle()!= null)
                .filter(lots->localDateTime.getHour()-lots.getParkingTime().getHour()<1 && localDateTime.getMinute()-lots.getParkingTime().getMinute()<30)
                .map(lots->lots.getVehicle()).collect(Collectors.toList());
        return vehicleList;
    }

    public List<SlotDetails> getVehicleDetailsBy(IPredicate... ipredicates) {
        Predicate<SlotDetails> predicate = ipredicates[0].getPredicate(ipredicates[0]);
        IntStream.range(1,ipredicates.length)
                    .forEach(p-> predicate.or(ipredicates[p].getPredicate(ipredicates[p])));
        List<SlotDetails> slotsList = new ArrayList<>();
        this.parkingLotList.stream()
                .filter(slot->slot.getVehicle() != null)
                .filter(predicate)
                .forEach(slot -> slotsList.add(slot));
        return slotsList;
    }

    public List<VehicleDetails> getVehicleDetails(IPredicate ... pre) {
        List<VehicleDetails> vehicleList=new ArrayList<>();
        getVehicleDetailsBy(pre).stream()
                .forEach(slot -> vehicleList.add(new VehicleDetails(slot)));
        return vehicleList;
    }

    public List<Integer> getSlotDetails(IPredicate... pre) {
        List<Integer> slotsList = new ArrayList<>();
        getVehicleDetailsBy(pre).stream()
                .forEach(slot -> slotsList.add(slot.getVehicleSlot()));
        return slotsList;
    }
}