package com.ljj.easesim.layout;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseLayoutTest {

    @Test
    void testGetInstance() {
        HouseLayout instance1 = HouseLayout.getInstance();
        HouseLayout instance2 = HouseLayout.getInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);
        assertEquals(instance1, instance2, "Instances should be the same");
    }

    @Test
    void testGetRoom() {
        HouseLayout houseLayout = new HouseLayout();

        Room room1 = houseLayout.getRoom("Bedroom1");

        assertNotNull(room1,"room1 is null");

        assertEquals("Bedroom1", room1.getName());

    }
    @Test
    void testGetRooms() {
        HouseLayout houseLayout = new HouseLayout();

        List<Room> rooms = houseLayout.getRooms();

        assertEquals(8, rooms.size());
    }
}