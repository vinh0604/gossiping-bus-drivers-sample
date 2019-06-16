package com.thoughtworks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {
    @Mock
    private PrintStream out;

    @Test
    public void shouldReturnTheNumberOfStopsRequired_whenAllBusDriversCanMeetEachOther() {
        Application.run(getClass().getResourceAsStream("/input-1.txt"), out);
        verify(out).println(5);
    }

    @Test
    public void shouldReturnNever_whenSomeBusDriversCanNotMeet() {
        Application.run(getClass().getResourceAsStream("/input-2.txt"), out);
        verify(out).println("never");
    }
}