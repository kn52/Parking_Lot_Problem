package com.bridgelabz.parking.lot.details;

import com.bridgelabz.parking.lot.exception.ParkingLotException;
import com.bridgelabz.parking.lot.strategy.DriverType;
import com.bridgelabz.parking.lot.vehicle.Vehicle;
import com.bridgelabz.parking.lot.vehicle.VehicleDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MultiLevelParkingLot {

    private int lotCapacity=1;
    public List<ParkingLot> parkingLots;

    public MultiLevelParkingLot(int lotCapacity) {
        this.lotCapacity = lotCapacity;
        this.parkingLots = new ArrayList<>();
    }

    public MultiLevelParkingLot() {
        this.parkingLots = new ArrayList<>();
    }

    public void addLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public boolean isLotAdded(ParkingLot parkingLot) {
        if (this.parkingLots.contains(parkingLot))
            return true;
        return false;
    }

    public void parkVehicle(Vehicle vehicle, DriverType driverType, String name) throws ParkingLotException {
        ParkingLot parkingLot=this.getParkingLot(driverType);
        parkingLot.parkVehicle(vehicle, driverType, name);
    }

    private ParkingLot getParkingLot(DriverType driverType) {
        return driverType.getParkingStrategy().getParkingLot(this.parkingLots);
    }

    public boolean isVehiclePark(Vehicle vehicle) {
        for (int i = 0; i < this.parkingLots.size(); i++) {
            if (this.parkingLots.get(i).isVehicleParked(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean unParkVehicle(Vehicle vehicle) throws ParkingLotException {
        for (int parkingLot = 0; parkingLot < this.parkingLots.size(); parkingLot++) {
            if (this.parkingLots.get(parkingLot).unParkVehicle(vehicle)) {
                return true;
            }
        }
        throw new ParkingLotException("No such vehicle found", ParkingLotException.ExceptionType.NO_SUCH_VEHICLE);
    }

    public void setCapacity(int lotcapacity) {
        this.lotCapacity=lotcapacity;
    }

    public int getVehicleSlot(Vehicle vehicle) {
        int slot=-1;
        for (int i=0;i<parkingLots.size();i++){
            slot=this.parkingLots.get(i).getSlotNumberByVehicle(vehicle);
        }
        return slot;
    }

    public List<Integer> getVehicleDetailsByVehicleColor(String vehicleColor) {
        List<Integer> vehicleDetail=new ArrayList<>();
        for(int i=0;i<parkingLots.size();i++){
            vehicleDetail.addAll(this.parkingLots.get(i).getVehicleDetailsByVehicleColor(vehicleColor));
        }
        return vehicleDetail;
    }

    public List<VehicleDetails> getVehicleDetailsByVehicleModelAndColor(String vehicleModel,String vehicleColor) {
        List<VehicleDetails> vehicleDetail=new ArrayList<>();
        for(int i=0;i<parkingLots.size();i++){
            vehicleDetail.addAll(this.parkingLots.get(i).getVehicleDetailsByVehicleModelAndColor(vehicleModel,vehicleColor));
        }
        return vehicleDetail;
    }

    public List<Integer> getVehicleDetailsByVehicleModel(String vehicleModel) {
        List<Integer> vehicleDetail=new ArrayList<>();
        for(int i=0;i<parkingLots.size();i++){
            vehicleDetail.addAll(this.parkingLots.get(i).getVehicleDetailsByModelColor(vehicleModel));
        }
        return vehicleDetail;
    }


    public List<Vehicle> getVehicleDetailsByTime(LocalDateTime localDateTime) {
        List<Vehicle> vehicleDetail=new ArrayList<>();
        for(int i=0;i<parkingLots.size();i++){
            vehicleDetail.addAll(this.parkingLots.get(i).getVehicleDetailsByTime(localDateTime));
        }
        return vehicleDetail;
    }

    public List<Integer> getVehicleHandiCapSlotDetails() {
        List<Integer> vehicleDetail=new ArrayList<>();
        for(int i=0;i<parkingLots.size();i++){
            vehicleDetail.addAll(this.parkingLots.get(i).getVehicleHandiCapSlotDetails());
        }
        return vehicleDetail;
    }

    public List<Vehicle> getAllVehicleDetails() {
        List<Vehicle> vehicleDetail=new ArrayList<>();
        for(int i=0;i<parkingLots.size();i++){
            vehicleDetail.addAll(this.parkingLots.get(i).getAllVehicleDetails());
        }
        return vehicleDetail;
    }
}
