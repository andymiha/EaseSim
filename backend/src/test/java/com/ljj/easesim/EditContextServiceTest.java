package com.ljj.easesim;

import com.ljj.easesim.elements.Window;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.requestBodies.EditContextFormData;
import com.ljj.easesim.services.EditContextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class EditContextServiceTest {

    @Mock
    private SmartHomeSimulator smartHomeSimulator;

    private EditContextService editContextService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        editContextService = new EditContextService();
    }



    @Test
    void testProcessFormDataReturnsTrue() {
        // Mock data
        EditContextFormData formData = new EditContextFormData();
        formData.setSelectedRoom("Test Room");
        formData.setSelectedInhabitant("Test Inhabitant");
        formData.setSelectedWindow("Window 1");

        // Call the method
        boolean result = editContextService.processFormData(formData);

        // Verify that the method returns true
        assertTrue(result);
    }

    @Test
    void testToggleWindowBlockStateReturnsTrue() {
        // Mock data
        Room selectedRoom = mock(Room.class);
        Window windowMock = mock(Window.class);
        int windowNumber = 1;

        // Call the method
        boolean result = editContextService.toggleWindowBlockState(selectedRoom, windowNumber);

        // Verify that the method returns true
        assertTrue(result);
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
