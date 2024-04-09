package com.ljj.easesim;

import com.ljj.easesim.abstractions.HouseElement;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HouseLayoutTest {

    @Test
    void testGetRoom() {
        // Test if getRoom method retrieves the correct room by name
        HouseLayout houseLayout = new HouseLayout();

        // Test existing room
        Room livingRoom = houseLayout.getRoom("LivingRoom");
        assertNotNull(livingRoom);
        assertEquals("LivingRoom", livingRoom.getName());

        // Test non-existing room
        Room nonExistingRoom = houseLayout.getRoom("Study");
        assertNull(nonExistingRoom);
    }

    @Test
    void testGetRooms() {
        // Test if getRooms method returns all rooms in the house layout with their elements
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
                assertEquals(2, elements.stream().filter(e -> e instanceof Window).count());
            }
        }
    }

    @Test
    void testGetHouseDoors() {
        // Test if getHouseDoors method returns all doors in the house layout
        HouseLayout houseLayout = new HouseLayout();
        Map<HouseElement, String> doorsMap = houseLayout.getHouseDoors();

        // Test if doors map is not null
        assertNotNull(doorsMap);

        // Test if doors map contains expected keys
        assertFalse(doorsMap.keySet().stream().allMatch(e -> e instanceof Light));
    }

    @Test
    void testGetHouseLights() {
        // Test if getHouseLights method returns all lights in the house layout
        HouseLayout houseLayout = new HouseLayout();
        Map<HouseElement, String> lightsMap = houseLayout.getHouseLights();

        // Test if lights map is not null
        assertNotNull(lightsMap);

        // Test if lights map contains expected keys
        assertTrue(lightsMap.keySet().stream().allMatch(e -> e instanceof Light));
    }

    @Test
    void testGetHouseWindows() {
        // Test if getHouseWindows method returns all windows in the house layout
        HouseLayout houseLayout = new HouseLayout();
        Map<HouseElement, String> windowsMap = houseLayout.getHouseWindows();

        // Test if windows map is not null
        assertNotNull(windowsMap);

        // Test if windows map contains expected keys
        assertTrue(windowsMap.keySet().stream().allMatch(e -> e instanceof Window));
    }

    @Test
    void testGetInstance() {
        // Test if getInstance method returns the same instance of HouseLayout
        HouseLayout instance1 = HouseLayout.getInstance();
        HouseLayout instance2 = HouseLayout.getInstance();
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertEquals(instance1, instance2);
    }

    @Test
    void testGetLights() {
        // Test if getLights method returns all lights in the house layout
        HouseLayout houseLayout = HouseLayout.getInstance();

        // Add some lights to the house layout for testing
        houseLayout.getRooms().get(0).addElement(new Light()); // Adding a light to the first room
        houseLayout.getRooms().get(1).addElement(new Light()); // Adding a light to the second room
        houseLayout.getRooms().get(2).addElement(new Light()); // Adding a light to the third room

        // Test if getLights returns the correct number of lights
        assertEquals(10, houseLayout.getLights().size());
    }

}

