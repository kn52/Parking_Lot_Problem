package com.bridgelabz.parking.lot.parkingstrategy;

public enum DriverType {

    HANDICAP {
        @Override
        public IParkingStrategy getParkingStrategy(){
            return new HandicapDriver();
        }
    }
    ,NORMAL{
        @Override
        public IParkingStrategy getParkingStrategy(){
            return new NormalDriver();
        }
    };
    public abstract IParkingStrategy getParkingStrategy();
}
