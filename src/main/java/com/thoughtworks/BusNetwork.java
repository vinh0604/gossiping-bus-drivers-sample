package com.thoughtworks;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class BusNetwork {
    private final Set<BusDriver> busDrivers;
    private final Set<Integer> availableGossips;

    public BusNetwork(Set<BusDriver> busDrivers) {
        this.busDrivers = busDrivers;
        this.availableGossips = busDrivers
                .stream()
                .map(BusDriver::getId)
                .collect(toCollection(HashSet::new));
    }

    public void moveAllToNextStop() {
        busDrivers.forEach(BusDriver::moveToNextStop);
    }

    public void exchangeGossips() {
        Map<Integer, Set<BusDriver>> stopBusDrivers = busDrivers.stream()
                .collect(groupingBy(BusDriver::getCurrentStop, toSet()));

        stopBusDrivers.forEach((stop, drivers) -> exchangeWithEachOther(drivers));
    }

    private void exchangeWithEachOther(Set<BusDriver> drivers) {
        drivers.forEach(d -> d.receiveGossips(drivers));
    }

    public boolean allGossipsShared() {
        return busDrivers.stream()
                .allMatch(d -> availableGossips.equals(d.getGossips()));
    }
}
