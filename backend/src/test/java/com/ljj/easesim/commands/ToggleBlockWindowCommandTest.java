package com.ljj.easesim.commands;

import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToggleBlockWindowCommandTest {
    private ToggleBlockWindowCommand toggleBlockWindowCommand;

    private Window mockedWindow;


    @BeforeEach
    void setUp() {
        mockedWindow = mock(Window.class);
        toggleBlockWindowCommand = new ToggleBlockWindowCommand(mockedWindow);

    }

    @Test
    void testExecuteToggleBlocked() {
        // Mock the initial state of isAutoState as false
        when(mockedWindow.getBlockedState()).thenReturn(false);

        // Call the execute method
        toggleBlockWindowCommand.execute();

        // Verify that setIsAutoState is called with true
        verify(mockedWindow, times(1)).toggleBlocked();
    }

    @Test
    void testExecuteToggleUnblocked() {
        // Mock the initial state of isAutoState as true
        when(mockedWindow.getBlockedState()).thenReturn(true);

        // Call the execute method
        toggleBlockWindowCommand.execute();

        // Verify that setIsAutoState is called with false
        verify(mockedWindow, times(1)).toggleBlocked();
    }

}