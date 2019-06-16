package com.thoughtworks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class InputParser {
    public Set<BusDriver> parse(InputStream in) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            Set<BusDriver> busDrivers = new HashSet<>();
            int currentLine = 0;
            while(reader.ready()) {
                String line = reader.readLine();
                currentLine++;
                busDrivers.add(new BusDriver(parseRoute(line), currentLine));
            }

            return busDrivers;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            doClose(in);
        }
    }

    private List<Integer> parseRoute(String line) {
        return Arrays.stream(line.split("\\s")).mapToInt(Integer::parseInt).boxed().collect(toList());
    }

    private void doClose(InputStream in) {
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
