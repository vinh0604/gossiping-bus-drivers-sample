package com.thoughtworks;

public class Controller {
    public static final int NOT_FOUND = -1;
    private static final int OPERATING_ROUND = 480;
    private final BusNetwork busNetwork;

    public Controller(BusNetwork busNetwork) {
        this.busNetwork = busNetwork;
    }

    public int start() {
        int round = 1;

        busNetwork.exchangeGossips();
        while (!busNetwork.allGossipsShared()) {
            round++;
            if (round > OPERATING_ROUND) return NOT_FOUND;

            busNetwork.moveAllToNextStop();
            busNetwork.exchangeGossips();
        }

        return round;
    }
}
