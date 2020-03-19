package com.bridgelabz.parking.lot;

public class AirportSecurity {

    public int informed=0;

    public boolean getInformed() {
        if(this.informed == 1)
            return true;
        return false;
    }

    public void setinformed() {
        informed=1;
    }
}
