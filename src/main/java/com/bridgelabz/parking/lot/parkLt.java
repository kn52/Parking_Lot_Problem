package com.bridgelabz.parking.lot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class parkLt {

    private int lotCapacity=1;
    private List<ParkingLot> parkingLots;

    public parkLt(int lotCapacity) {
        this.lotCapacity = lotCapacity;
        this.parkingLots = new ArrayList<>();
    }

    public parkLt() {
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

    public void parkVehicle(Object vehicle, DriverType type, String name) throws ParkingLotException {
        ParkingLot parkingLot=this.getParkingLot();
        parkingLot.parkVehicle(vehicle, type, name);
    }

    public ParkingLot getParkingLot() {
        for (int i=0;i<parkingLots.size();i++){
            ParkingLot object=this.parkingLots.get(i);
            for(int j=0;j<object.getTotalCapacity();j++){
                if(object.getEmptySlots()>0)
                    return object;
            }
        }
        return null;
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
}
