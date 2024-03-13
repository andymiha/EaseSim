package com.ljj.easesim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;

public class HouseLayoutTest {

    @Test
    void testGetRoom() {
        HouseLayout houseLayout = new HouseLayout();

        // Test existing room
        Room livingRoom = houseLayout.getRoom("LivingRoom");
        assertNotNull(livingRoom);
        assertEquals("LivingRoom", livingRoom.getName());

        // Test non-existing room
        Room nonExistingRoom = houseLayout.getRoom("Study");
        assertEquals(null, nonExistingRoom);
    }

    @Test
    void testGetRooms() {
        HouseLayout houseLayout = new HouseLayout();
        List<Room> rooms = houseLayout.getRooms();

        // Test if rooms are loaded correctly
        assertEquals(8, rooms.size());

        // Test if elements are loaded correctly for each room
        for (Room room : rooms) {
            if (room.getName().equals("Living Room")) {
                List<HouseElement> elements = room.getElements();
                assertEquals(1, elements.stream().filter(e -> e instanceof Light).count());
                assertEquals(1, elements.stream().filter(e -> e instanceof Window).count());
            } else if (room.getName().equals("Kitchen")) {
                List<HouseElement> elements = room.getElements();
                assertEquals(2, elements.stream().filter(e -> e instanceof Light).count());
                assertEquals(3, elements.stream().filter(e -> e instanceof Window).count());
            }
        }
    }
}
