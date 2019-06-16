package com.thoughtworks;

import java.util.*;

public class BusDriver {
    private final List<Integer> routes;
    private int currentStopIndex;
    private final int id;
    private final Set<Integer> gossips;

    public BusDriver(List<Integer> routes, int id) {
        this.routes = routes;
        this.currentStopIndex = 0;
        this.id = id;
        this.gossips = new HashSet<>(Collections.singleton(id));
    }

    public int getCurrentStop() {
        return routes.get(currentStopIndex);
    }

    public int getId() {
        return id;
    }

    public Set<Integer> getGossips() {
        return gossips;
    }

    public void receiveGossips(Set<BusDriver> others) {
        others.forEach(d -> gossips.addAll(d.gossips));
    }

    public void moveToNextStop() {
        currentStopIndex = (currentStopIndex + 1) % routes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusDriver busDriver = (BusDriver) o;
        return id == busDriver.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
