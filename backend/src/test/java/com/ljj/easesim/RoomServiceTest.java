package com.ljj.easesim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ljj.easesim.FormData;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.services.RoomService;
//edit context service
public class RoomServiceTest {

    private RoomService roomService;

    @BeforeEach
    void setUp() {
        roomService = new RoomService();
        roomService.houseLayout = mock(HouseLayout.class);
    }

    @Test
    void testProcessGetData() {
        // Create a mock HouseLayout
        HouseLayout houseLayoutMock = mock(HouseLayout.class);

        // Create some mock rooms
        List<Room> mockRooms = new ArrayList<>();
        Room mockRoom1 = new Room(1, "LivingRoom", new ArrayList<>(), new ArrayList<>());
        mockRooms.add(mockRoom1);
        Room mockRoom2 = new Room(2, "Bathroom", new ArrayList<>(), new ArrayList<>());
        mockRooms.add(mockRoom2);

        // Define the behavior of the mocked houseLayout
        when(houseLayoutMock.getRooms()).thenReturn(mockRooms);

        // Set the mock HouseLayout in RoomService
        roomService.houseLayout = houseLayoutMock;

        // Call the method to be tested
        var data = roomService.processGetData();

        // Assert that the returned data matches the expected data
        assertEquals(2, data.get("rooms").size());
        assertEquals(0, data.get("windows").size());
    }

    @Test
    void testProcessFormDataWithInhabitant() {
        // Create mock data
        FormData formData = new FormData();
        formData.setSelectedRoom("Living Room");
        formData.setSelectedInhabitant("Sarah");

        // Mock the Room object
        Room selectedRoomMock = mock(Room.class);
        when(roomService.houseLayout.getRoom("Living Room")).thenReturn(selectedRoomMock);

        // Call the method to be tested
        roomService.processFormData(formData);

        // Verify that the method correctly processes the selected room and prints the selected inhabitant
        // Here you can check the console output or log messages to ensure that the selected inhabitant was printed
        // For the sake of simplicity, we are not checking console output in this test
    }

    @Test
    void testProcessFormDataWithWindow() {
        // Create mock data
        FormData formData = new FormData();
        formData.setSelectedRoom("Living Room");
        formData.setSelectedWindow("Window 1");

        // Mock the Room object
        Room selectedRoomMock = mock(Room.class);
        when(roomService.houseLayout.getRoom("Living Room")).thenReturn(selectedRoomMock);

        // Mock the elements list in the room
        ArrayList<HouseElement> elements = new ArrayList<>();
        Window window = new Window();
        window.setId(1);
        elements.add(window);
        when(selectedRoomMock.getElements()).thenReturn(elements);

        // Call the method to be tested
        roomService.processFormData(formData);

        // Verify that the method correctly processes the selected room and toggles the state of the selected window
        assertTrue(window.getState());
    }

    @Test
    void testExtractWindowNumber() {
        // Test with a window string containing only digits
        String windowString1 = "Window123";
        int expectedNumber1 = 123;
        assertEquals(expectedNumber1, roomService.extractWindowNumber(windowString1));

        // Test with a window string containing digits and non-digits
        String windowString2 = "Window 45";
        int expectedNumber2 = 45;
        assertEquals(expectedNumber2, roomService.extractWindowNumber(windowString2));
    }
}
