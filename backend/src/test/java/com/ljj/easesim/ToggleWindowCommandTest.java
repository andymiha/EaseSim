package com.ljj.easesim;

import com.ljj.easesim.commands.ToggleLightCommand;
import com.ljj.easesim.commands.ToggleWindowCommand;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToggleWindowCommandTest {
    private ToggleWindowCommand toggleWindowCommand;
    private Window mockedWindow;

    @BeforeEach
    void setUp() {
        mockedWindow = mock(Window.class);
        toggleWindowCommand = new ToggleWindowCommand(mockedWindow);
    }

    @Test
    void testExecute() {
        // Call the execute method
        toggleWindowCommand.execute();

        // Verify that the toggle method of the light object is called once
        verify(mockedWindow, times(1)).toggle();
    }

}