package com.bridgelabz.parking.lot;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    public String name = null;
    public List<String> vehicle =new ArrayList();

    public void addVehicle(String name)
    {
        vehicle.add(name);
    }

    public boolean get(String name) {
        if(vehicle.contains(name))
            return true;
        return false;
    }

    public int getSize() {
        return vehicle.size();
    }
}
