package com.ljj.easesim;

import com.ljj.easesim.elements.Window;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.requestBodies.EditContextFormData;
import com.ljj.easesim.services.EditContextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditContextServiceTest {

    private EditContextService editContextService;

    @BeforeEach
    void setUp() {
        editContextService = new EditContextService();
    }

    @Test
    void testProcessGetData() {
        // Mock the SmartHomeSimulator
        SmartHomeSimulator smartHomeSimulatorMock = mock(SmartHomeSimulator.class);
        when(smartHomeSimulatorMock.getHouseLayout().getRooms()).thenReturn(new ArrayList<>());


        // Call the method to be tested
        var data = editContextService.processGetData();

        // Assert that the returned data contains an empty list of rooms
        assertEquals(0, data.get("rooms").size());
    }

    @Test
    void testProcessFormData() {
        // Mock the EditContextFormData
        EditContextFormData formDataMock = mock(EditContextFormData.class);
        when(formDataMock.getSelectedRoom()).thenReturn("Test Room");
        when(formDataMock.getSelectedInhabitant()).thenReturn("Test Inhabitant");
        when(formDataMock.getSelectedWindow()).thenReturn("Window 1");

        // Mock the SmartHomeSimulator
        SmartHomeSimulator smartHomeSimulatorMock = mock(SmartHomeSimulator.class);
        Room roomMock = mock(Room.class);
        Window windowMock = mock(Window.class);
        when(roomMock.getWindows()).thenReturn((ArrayList<Window>) List.of(windowMock));
        when(smartHomeSimulatorMock.getHouseLayout().getRoom("Test Room")).thenReturn(roomMock);


        // Call the method to be tested
        editContextService.processFormData(formDataMock);

        // Verify that the method interacts correctly with its dependencies
        verify(roomMock).getWindows();
        verify(windowMock).toggleBlocked();
    }

    @Test
    void testExtractWindowNumber() {
        // Create test cases for different window strings
        assertEquals(1, editContextService.extractWindowNumber("Window 1"));
        assertEquals(2, editContextService.extractWindowNumber("Window 2"));
        assertEquals(10, editContextService.extractWindowNumber("Window 10"));
        assertEquals(123, editContextService.extractWindowNumber("Window 123"));
    }
}
