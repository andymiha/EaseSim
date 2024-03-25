package com.ljj.easesim.commands;

import com.ljj.easesim.commands.ToggleIsAutoLightCommand;
import com.ljj.easesim.commands.ToggleLightCommand;
import com.ljj.easesim.elements.Light;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ToggleLightIsAutoCommandTest {


    private ToggleIsAutoLightCommand toggleIsAutoLightCommand;

    private Light mockedLight;


    @BeforeEach
    void setUp() {
        mockedLight = mock(Light.class);
        toggleIsAutoLightCommand = new ToggleIsAutoLightCommand(mockedLight);

    }

    @Test
    void testExecuteToggleOn() {
        // Mock the initial state of isAutoState as false
        when(mockedLight.getIsAutoState()).thenReturn(false);

        // Call the execute method
        toggleIsAutoLightCommand.execute();

        // Verify that setIsAutoState is called with true
        verify(mockedLight, times(1)).setIsAutoState(true);
    }

    @Test
    void testExecuteToggleOff() {
        // Mock the initial state of isAutoState as true
        when(mockedLight.getIsAutoState()).thenReturn(true);

        // Call the execute method
        toggleIsAutoLightCommand.execute();

        // Verify that setIsAutoState is called with false
        verify(mockedLight, times(1)).setIsAutoState(false);
    }
}


