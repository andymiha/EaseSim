package com.ljj.easesim;


import com.ljj.easesim.layout.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import com.ljj.easesim.services.HVAC;



class SmartHomeHeatingTest {
    private SmartHomeHeating smartHomeHeating;
    private SmartHomeSecurity smartHomeSecurity;
    private HVAC hvac;

    @BeforeEach
    public void setUp() {
        smartHomeHeating = SmartHomeHeating.getInstance();
        smartHomeSecurity = SmartHomeSecurity.getInstance();
        hvac = HVAC.getInstance();
    }

    @Test
    void testToggleActive() {
        //when the program is run, SHH is automatically toggled
        assertTrue(smartHomeHeating.isActive());
        smartHomeHeating.toggleActive();
        assertFalse(smartHomeHeating.isActive());
    }

    @Test
    void testUpdateTemperature() {
        smartHomeHeating.toggleActive();
        double outdoorTemperature = 15;
        smartHomeHeating.updateTemperature("", outdoorTemperature);
        assertEquals(15,hvac.getOutsideTemperature());
    }

    @Test
    void testUpdateAwayMode(){
        smartHomeHeating.updateAwayMode(true);
        assertTrue(smartHomeHeating.isSHPAway());
    }

    @Test
    void testCreateHeatingZone() {
        assertTrue(smartHomeHeating.createHeatingZone("Zone45"));
    }

    @Test
    void testCreateHeatingZoneFalse() {
        assertFalse(smartHomeHeating.createHeatingZone("Zone3"));
    }

    @Test
    void testDeleteHeatingZone() {
        smartHomeHeating.createHeatingZone("Zone3");
        assertTrue(smartHomeHeating.deleteHeatingZone("Zone3"));
    }
    @Test
    void testDeleteHeatingZoneFalse() {
        assertFalse(smartHomeHeating.deleteHeatingZone("Zone36"));
    }

    @Test
    public void testRemoveObserver() {
        smartHomeHeating.registerObserver(smartHomeSecurity);
        assertNotNull(smartHomeHeating.getTemperatureObserver());
        smartHomeHeating.removeObserver(smartHomeSecurity);
        assertNull(smartHomeHeating.getTemperatureObserver());
    }
    @Test
    void testAddRoomToHeatingZone() {
        smartHomeHeating.createHeatingZone("Zone3");
        assertTrue(smartHomeHeating.addRoomToHeatingZone("Zone3", new Room(2,"TestRoom")));
    }

    @Test
    void testRemoveRoomFromHeatingZone() {
        smartHomeHeating.createHeatingZone("Zone3");
        Room testRoom = new Room(2, "TestRoom");
        smartHomeHeating.addRoomToHeatingZone("Zone3", testRoom);
        assertTrue(smartHomeHeating.removeRoomFromHeatingZone("Zone3", testRoom));
    }

    @Test
    void testAddRoomToHeatingZoneFalse() {
        assertFalse(smartHomeHeating.addRoomToHeatingZone("Zone85", new Room(2,"TestRoom")));
    }

    @Test
    void testRemoveRoomFromHeatingZoneFalse() {
        Room testRoom = new Room(2, "TestRoom");
        smartHomeHeating.addRoomToHeatingZone("Zone3", testRoom);
        assertFalse(smartHomeHeating.removeRoomFromHeatingZone("Zone3", testRoom));
    }
    @Test
    void testGetRoomsInHeatingZone() {
        smartHomeHeating.createHeatingZone("ZoneAndy");
        Room andyRoom = new Room(12,"AndyRoom");
        smartHomeHeating.addRoomToHeatingZone("ZoneAndy", andyRoom);
        List<Room> rooms = smartHomeHeating.getRoomsInHeatingZone("ZoneAndy");
        assertEquals(1, rooms.size());
        assertEquals(andyRoom, rooms.get(0));
    }

    @Test
    void printHeatingZones() {
    }
}