package com.thoughtworks;

import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BusNetworkTest {
    private Set<BusDriver> busDrivers;
    private BusNetwork busNetwork;

    @Before
    public void setUp() {
        busDrivers = Set.of(
                new BusDriver(asList(1, 2, 3, 4), 1),
                new BusDriver(asList(2, 3, 4, 5), 2),
                new BusDriver(asList(4, 3, 2, 1), 3)
        );
        busNetwork = new BusNetwork(busDrivers);
    }

    @Test
    public void moveAllToNextStop_shouldMoveAllBusDriversToNextStop() {
        busNetwork.moveAllToNextStop();

        assertEquals(2, getBusDriver(1).getCurrentStop());
        assertEquals(3, getBusDriver(2).getCurrentStop());
        assertEquals(3, getBusDriver(3).getCurrentStop());
    }

    @Test
    public void exchangeGossips_shouldLetDriversAtTheSameStopExchangeGossips() {
        busNetwork.moveAllToNextStop();
        busNetwork.exchangeGossips();

        assertEquals(Set.of(2, 3), getBusDriver(2).getGossips());
        assertEquals(Set.of(2, 3), getBusDriver(3).getGossips());
    }

    @Test
    public void allGossipsShared_shouldReturnFalse_ifNoDriverReceivedAllGossips() {
        assertFalse(busNetwork.allGossipsShared());
    }

    @Test
    public void allGossipsShared_shouldReturnFalse_ifSomeDriversDidNotReceiveAllGossips() {
        getBusDriver(1).receiveGossips(Set.of(getBusDriver(2), getBusDriver(3)));

        assertFalse(busNetwork.allGossipsShared());
    }

    @Test
    public void allGossipsShared_shouldReturnTrue_ifAllDriversReceivedAllGossips() {
        getBusDriver(1).receiveGossips(Set.of(getBusDriver(2), getBusDriver(3)));
        getBusDriver(2).receiveGossips(Set.of(getBusDriver(1), getBusDriver(3)));
        getBusDriver(3).receiveGossips(Set.of(getBusDriver(1), getBusDriver(2)));

        assertTrue(busNetwork.allGossipsShared());
    }

    private BusDriver getBusDriver(int id) {
        return busDrivers.stream().filter(d -> d.getId() == id).findFirst().get();
    }
}