package com.ljj.easesim.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.ljj.easesim.layout.Room;

import java.util.List;

public class HeatingZoneTest {
    private HeatingZone heatingZone;
    private Room testRoom1;
    private Room testRoom2;

    @BeforeEach
    public void setUp() {
        heatingZone = new HeatingZone("TestZone");
        testRoom1 = new Room(10, "Room1");
        testRoom2 = new Room(15, "Room2");
    }


    @Test
    public void testSetters() {
        heatingZone.setDesiredZoneTemp(25);
        assertEquals(25, heatingZone.getDesiredZoneTemp());
    }

    @Test
    public void testAddAndRemoveRoom() {
        heatingZone.addRoom(testRoom1);
        heatingZone.addRoom(testRoom2);

        List<Room> rooms = heatingZone.getRooms();
        assertEquals(2, rooms.size());
        assertTrue(rooms.contains(testRoom1));
        assertTrue(rooms.contains(testRoom2));

        heatingZone.removeRoom(testRoom1);
        assertFalse(heatingZone.getRooms().contains(testRoom1));
        assertEquals(1, heatingZone.getRooms().size());
    }

    @Test
    public void testSetCurrentZoneTemp() {
        heatingZone.addRoom(testRoom1);
        heatingZone.addRoom(testRoom2);

        heatingZone.setCurrentZoneTemp(25);

        assertEquals(25, heatingZone.getCurrentZoneTemp());
        assertEquals(25, testRoom1.getCurrentTemp());
        assertEquals(25, testRoom2.getCurrentTemp());
    }
}