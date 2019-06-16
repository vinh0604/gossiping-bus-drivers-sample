package com.thoughtworks;

import org.junit.Test;

import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class InputParserTest {
    @Test
    public void shouldParseInputFileToSetOfBusDrivers() {
        InputParser parser = new InputParser();

        Set<BusDriver> busDrivers = parser.parse(getClass().getResourceAsStream("/input-1.txt"));

        assertEquals(
                Set.of(
                        new BusDriver(asList(3, 1, 2, 3), 1),
                        new BusDriver(asList(3, 2, 3, 1), 2),
                        new BusDriver(asList(4, 2, 3, 4, 5), 3)
                ),
                busDrivers
        );
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowException_ifInputStreamNotExists() {
        InputParser parser = new InputParser();

        parser.parse(getClass().getResourceAsStream("/input-3.txt"));
    }
}