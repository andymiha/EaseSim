package com.ljj.easesim.commands;

import com.ljj.easesim.commands.ToggleIsAutoDoorCommand;
import com.ljj.easesim.commands.ToggleIsAutoLightCommand;
import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Light;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToggleIsAutoDoorCommandTest {

    private ToggleIsAutoDoorCommand toggleIsAutoDoorCommand;

    private Door mockedDoor;


    @BeforeEach
    void setUp() {
        mockedDoor = mock(Door.class);
        toggleIsAutoDoorCommand = new ToggleIsAutoDoorCommand(mockedDoor);

    }

    @Test
    void testExecuteToggleOn() {
        // Mock the initial state of isAutoState as false
        when(mockedDoor.getIsAutoState()).thenReturn(false);

        // Call the execute method
        toggleIsAutoDoorCommand.execute();

        // Verify that setIsAutoState is called with true
        verify(mockedDoor, times(1)).setIsAutoState(true);
    }

    @Test
    void testExecuteToggleOff() {
        // Mock the initial state of isAutoState as true
        when(mockedDoor.getIsAutoState()).thenReturn(true);

        // Call the execute method
        toggleIsAutoDoorCommand.execute();

        // Verify that setIsAutoState is called with false
        verify(mockedDoor, times(1)).setIsAutoState(false);
    }

}