package com.thoughtworks;

import org.junit.Test;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BusDriverTest {
    @Test
    public void moveToNextStop_shouldMoveToNextStop_whenCurrentStopIsNotTheLastStop() {
        BusDriver busDriver = new BusDriver(asList(1, 2, 3, 4), 1);

        busDriver.moveToNextStop();

        assertEquals(2, busDriver.getCurrentStop());
    }

    @Test
    public void moveToNextStop_shouldMoveToNextStop_whenThereAreSameStopsInTheRoute() {
        BusDriver busDriver = new BusDriver(asList(2, 1, 2), 1);

        busDriver.moveToNextStop();
        busDriver.moveToNextStop();
        busDriver.moveToNextStop();

        assertEquals(2, busDriver.getCurrentStop());
    }

    @Test
    public void moveToNextStop_shouldMoveToTheFirstStop_whenCurrentStopIsTheLastStop() {
        BusDriver busDriver = new BusDriver(asList(1, 2, 3, 4), 1);
        busDriver.moveToNextStop();
        busDriver.moveToNextStop();
        busDriver.moveToNextStop();

        assertEquals(4, busDriver.getCurrentStop());
        busDriver.moveToNextStop();

        assertEquals(1, busDriver.getCurrentStop());
    }

    @Test
    public void receiveGossips_shouldAddAllGossipsFromOtherDriver() {
        BusDriver busDriver1 = new BusDriver(asList(1, 2, 3, 4), 1);
        BusDriver busDriver2 = new BusDriver(asList(1, 2, 3, 4), 2);
        BusDriver busDriver3 = new BusDriver(asList(1, 2, 3, 4), 3);

        busDriver1.receiveGossips(Set.of(busDriver2));
        busDriver3.receiveGossips(Set.of(busDriver1));
        busDriver2.receiveGossips(Set.of(busDriver1, busDriver3));

        assertEquals(Set.of(1, 2), busDriver1.getGossips());
        assertEquals(Set.of(1, 2, 3), busDriver3.getGossips());
        assertEquals(Set.of(1, 2, 3), busDriver2.getGossips());
    }
}