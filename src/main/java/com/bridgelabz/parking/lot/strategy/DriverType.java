package com.bridgelabz.parking.lot.strategy;

import com.bridgelabz.parking.lot.vehicle.LargeDriver;

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
