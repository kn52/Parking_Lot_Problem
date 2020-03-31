package com.bridgelabz.parking.lot.details;

import com.bridgelabz.parking.lot.exception.ParkingLotException;
import com.bridgelabz.parking.lot.strategy.DriverType;

import java.util.ArrayList;
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

    public void parkVehicle(Object vehicle, DriverType driverType, String name) throws ParkingLotException {
        ParkingLot parkingLot=this.getParkingLot(driverType);
        parkingLot.parkVehicle(vehicle, driverType, name);
    }

    private ParkingLot getParkingLot(DriverType driverType) {
        return driverType.getParkingStrategy().getParkingLot(this.parkingLots);
    }

    public boolean isVehiclePark(Object vehicle) {
        for (int i = 0; i < this.parkingLots.size(); i++) {
            if (this.parkingLots.get(i).isVehicleParked(vehicle)) {
                return true;
            }
        }
        return false;
    }

    public boolean unParkVehicle(Object vehicle) throws ParkingLotException {
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

    public int getvehicleSlot(Object vehicle) {
        int slot=-1;
        for (int i=0;i<parkingLots.size();i++){
            slot=this.parkingLots.get(i).getSlotNumberByVehicle(vehicle);
        }
        return slot;
    }
}
