package com.bridgelabz.parking.lot.strategy;

public enum DriverType {
    HANDICAP {
        @Override
        public IParkingStrategy getParkingStrategy(){
            return new Handicap();
        }
    }
    ,NORMAL{
        @Override
        public IParkingStrategy getParkingStrategy(){
            return new Handicap();
        }
    };
    public abstract IParkingStrategy getParkingStrategy();
}
