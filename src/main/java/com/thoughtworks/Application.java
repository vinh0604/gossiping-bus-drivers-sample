package com.thoughtworks;

import java.io.InputStream;
import java.io.PrintStream;

import static com.thoughtworks.Controller.NOT_FOUND;

public class Application {
    public static void run(InputStream inputStream, PrintStream out) {
        InputParser parser = new InputParser();
        BusNetwork busNetwork = new BusNetwork(parser.parse(inputStream));
        Controller controller = new Controller(busNetwork);

        int noOfStops = controller.start();

        if (noOfStops == NOT_FOUND) {
            out.println("never");
        } else {
            out.println(noOfStops);
        }
    }
}
