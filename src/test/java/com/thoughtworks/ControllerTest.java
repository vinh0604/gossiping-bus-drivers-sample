package com.thoughtworks;

import org.junit.Test;

import java.util.Set;

import static com.thoughtworks.Controller.NOT_FOUND;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class ControllerTest {

    @Test
    public void start_shouldReturnNumberOfStopsRequiredToExchangeAllGossips() {
        BusNetwork busNetwork = new BusNetwork(Set.of(
                new BusDriver(asList(3, 1, 2, 3), 1),
                new BusDriver(asList(3, 2, 3, 1), 2),
                new BusDriver(asList(4, 2, 3, 4, 5), 3)
        ));
        Controller controller = new Controller(busNetwork);

        int noOfStops = controller.start();

        assertEquals(5, noOfStops);
    }

    @Test
    public void start_shouldReturnNotFound_whenSomeBusDriverDidNotReceivedAllGossipsAfterADay() {
        BusNetwork busNetwork = new BusNetwork(Set.of(
                new BusDriver(asList(2, 1, 2), 1),
                new BusDriver(asList(5, 2, 8), 2)
        ));
        Controller controller = new Controller(busNetwork);

        int noOfStops = controller.start();

        assertEquals(NOT_FOUND, noOfStops);
    }
}