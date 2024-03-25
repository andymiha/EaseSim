package com.ljj.easesim;

import com.ljj.easesim.commands.ToggleDoorCommand;
import com.ljj.easesim.commands.ToggleLightCommand;
import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Light;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToggleDoorCommandTest {
    private ToggleDoorCommand toggleDoorCommand;
    private Door mockedDoor;

    @BeforeEach
    void setUp() {
        mockedDoor = mock(Door.class);
        toggleDoorCommand = new ToggleDoorCommand(mockedDoor);
    }

    @Test
    void testExecute() {
        // Call the execute method
        toggleDoorCommand.execute();

        // Verify that the toggle method of the light object is called once
        verify(mockedDoor, times(1)).toggle();
    }

}