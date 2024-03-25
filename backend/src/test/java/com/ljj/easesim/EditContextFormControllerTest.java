package com.ljj.easesim;

import com.ljj.easesim.controllers.EditContextFormController;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.requestBodies.EditContextFormData;
import com.ljj.easesim.services.EditContextService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EditContextFormControllerTest {

    @Mock
    private EditContextService editContextService;

    @InjectMocks
    private EditContextFormController editContextFormController;

    public EditContextFormControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetData() {
        // Create mock data
        Map<String, List<Room>> mockData = new HashMap<>();
        List<Room> mockRooms = new ArrayList<>();
        Room mockRoom = new Room(1, "Mock Room", new ArrayList<>(), new ArrayList<>());
        mockRooms.add(mockRoom);
        mockData.put("rooms", mockRooms);

        // Mock the behavior of editContextService
        when(editContextService.processGetData()).thenReturn(mockData);

        // Call the controller method
        Map<String, List<Room>> result = editContextFormController.getData();

        // Verify that the method returns the expected data
        assertEquals(mockData, result);
    }

    @Test
    void testSubmitForm() {
        // Create mock data
        EditContextFormData formData = new EditContextFormData();
        formData.setSelectedRoom("Living Room");

        // Call the controller method
        String result = editContextFormController.submitForm(formData);

        // Verify that the method returns the expected result
        assertEquals("Form submitted successfully!", result);

        // Verify that the processFormData method of editContextService is called with the correct argument
        verify(editContextService, times(1)).processFormData(formData);
    }
}
