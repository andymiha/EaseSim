package com.ljj.easesim.commands;

import com.ljj.easesim.commands.ToggleLightCommand;
import com.ljj.easesim.elements.Light;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ToggleLightCommandTest {

    private ToggleLightCommand toggleLightCommand;
    private Light mockedLight;

    @BeforeEach
    void setUp() {
        mockedLight = mock(Light.class);
        toggleLightCommand = new ToggleLightCommand(mockedLight);
    }

    @Test
    void testExecute() {
        // Call the execute method
        toggleLightCommand.execute();

        // Verify that the toggle method of the light object is called once
        verify(mockedLight, times(1)).toggle();
    }
}
