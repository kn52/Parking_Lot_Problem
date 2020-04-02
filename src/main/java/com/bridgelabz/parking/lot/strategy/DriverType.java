package com.bridgelabz.parking.lot.strategy;

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
    },
    LARGE{
        @Override
        public IParkingStrategy getParkingStrategy(){
            return new LargeDriver();
        }
    };
    public abstract IParkingStrategy getParkingStrategy();
}
