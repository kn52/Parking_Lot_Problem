package com.bridgelabz.parking.lot.test;

import com.bridgelabz.parking.lot.Slot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlotTest {
    Slot slot;
    Object vehicle;
    @Before
    public void setUp() throws Exception {
       slot=new Slot();
       slot.setSlot(1);
       vehicle=new Object();
    }

    @Test
    public void whengiven_VehicleParked_InSlot() {
        slot.setVehicleSlot(vehicle,1);
        int i=slot.getVehicleSlot(vehicle);
        Assert.assertEquals(1,i);
    }


}
