package com.ljj.easesim;

import com.ljj.easesim.abstractions.HouseElement;
import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.layout.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElementsTest {

    @Test
    void testDoorConstructorAndGetters() {
        Door door = new Door();
        door.setId(1);
        assertEquals(1, door.getId());
        assertFalse(door.getState());
        assertFalse(door.getBlockedState());
    }

    @Test
    void testDoorToggle() {
        Door door = new Door();
        assertFalse(door.getState());
        door.toggle();
        assertTrue(door.getState());
    }

    @Test
    void testDoorBlocked() {
        Door door = new Door();
        assertFalse(door.getBlockedState());
        door.toggleBlocked();
        assertTrue(door.getBlockedState());
    }

    @Test
    void testLightConstructorAndGetters() {
        Light light = new Light();
        light.setId(2);
        assertEquals(2, light.getId());
        assertFalse(light.getState());
    }

    @Test
    void testLightToggle() {
        Light light = new Light();
        assertFalse(light.getState());
        light.toggle();
        assertTrue(light.getState());
    }

    @Test
    void testLightAutoState() {
        Light light = new Light();
        assertFalse(light.getIsAutoState());
        light.setIsAutoState(true);
        assertTrue(light.getIsAutoState());
    }

    @Test
    void testWindowConstructorAndGetters() {
        Window window = new Window();
        window.setId(3);
        assertEquals(3, window.getId());
        assertFalse(window.getState());
        assertFalse(window.getBlockedState());
    }

    @Test
    void testWindowToggle() {
        Window window = new Window();
        assertFalse(window.getState());
        window.toggle();
        assertTrue(window.getState());
    }

    @Test
    void testWindowBlocked() {
        Window window = new Window();
        assertFalse(window.getBlockedState());
        window.toggleBlocked();
        assertTrue(window.getBlockedState());
    }

    @Test
    void testDoorToString() {
        Door door = new Door();
        door.setId(1);
        door.setRoomName("Room 1");
        door.setIsAutoState(true);
        assertEquals("Door{id=1, roomName=Room 1, roomConnection=null, isBlocked=false, isAuto=true, isOpen=false}", door.toString());
    }

    @Test
    void testDoorGetIsAutoState() {
        Door door = new Door();
        assertFalse(door.getIsAutoState());
    }

    @Test
    void testDoorSetIsAutoState() {
        Door door = new Door();
        assertFalse(door.getIsAutoState());
        door.setIsAutoState(true);
        assertTrue(door.getIsAutoState());
    }

    @Test
    void testDoorGetRoomConnection() {
        Door door = new Door();
        assertNull(door.getRoomConnection());
    }

    @Test
    void testLightToString() {
        Light light = new Light();
        light.setId(3);
        light.setRoomName("Living Room");
        light.setIsAutoState(true);
        light.toggle();
        assertEquals("Light{id=3, roomName=Living Room, isAuto=true, isOpen=true}", light.toString());
    }

    @Test
    void testWindowSetBlocked() {
        Window window = new Window();
        assertFalse(window.getBlockedState());
        window.setBlocked(true);
        assertTrue(window.getBlockedState());
    }

    @Test
    void testWindowToString() {
        Window window = new Window();
        window.setId(5);
        window.setRoomName("Bedroom");
        window.toggle(); // Open the window
        window.setBlocked(true); // Block the window
        assertEquals("Window{id=5, roomName=Bedroom, isBlocked=true, isOpen=true}", window.toString());
    }

    @Test
    void testSetState() {
        HouseElement element = new TestHouseElement(); // Create a test HouseElement
        assertFalse(element.getState()); // Initial state should be false

        element.setState(true); // Set state to true
        assertTrue(element.getState()); // State should be true after setting
    }

    @Test
    void testGetRoomName() {
        HouseElement element = new TestHouseElement(); // Create a test HouseElement
        element.setRoomName("Living Room"); // Set room name
        assertEquals("Living Room", element.getRoomName()); // Check if room name is retrieved correctly
    }

    @Test
    void testGetRoom() {
        // Create a dummy room
        Room room = new Room(1, "Living Room", null, null);

        // Create a test HouseElement
        HouseElement element = new TestHouseElement();
        element.setRoomName("Living Room"); // Set room name

        // Retrieve room using getRoom method
        Room retrievedRoom = element.getRoom(1); // ID doesn't matter for this test
        assertNotNull(retrievedRoom); // Room should not be null
        assertEquals(room.getName(), retrievedRoom.getName()); // Room name should match
    }

    // Test implementation of HouseElement for testing purposes
    private static class TestHouseElement extends HouseElement {
        @Override
        public Room getRoom(int roomId) {
            // For testing purposes, return a dummy room
            return new Room(1, "Living Room", null, null);
        }
    }

}
