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
    },
    OTHER{
        @Override
        public IParkingStrategy getParkingStrategy(){
            return new OtherDriver();
        }
    };
    public abstract IParkingStrategy getParkingStrategy();
}
