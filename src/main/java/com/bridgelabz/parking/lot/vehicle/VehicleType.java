package com.bridgelabz.parking.lot.vehicle;

public enum VehicleType {
    SMALL{
        @Override
        public IVehicleStrategy getVehicleStrategy(){
            return new SmallDriver();
        }
    },LARGE{
        @Override
        public IVehicleStrategy getVehicleStrategy(){
            return new LargeDriver();
        }
    };

    public abstract IVehicleStrategy getVehicleStrategy();
}
