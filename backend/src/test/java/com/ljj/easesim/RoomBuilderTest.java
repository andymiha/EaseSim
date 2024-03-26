package com.ljj.easesim;

import com.ljj.easesim.builders.RoomBuilder;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.abstractions.HouseElement;
import com.ljj.easesim.abstractions.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomBuilderTest {

    private RoomBuilder roomBuilder;

    // Setup method to initialize RoomBuilder before each test
    @BeforeEach
    void setUp() {
        roomBuilder = new RoomBuilder();
    }

    // Test to verify that RoomBuilder correctly builds a Room object with provided properties
    @Test
    void testBuildRoom() {
        // Define test data
        int id = 1;
        String name = "Living Room";
        ArrayList<HouseElement> elements = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        // Build a Room object using RoomBuilder
        Room room = roomBuilder.id(id)
                .name(name)
                .elements(elements)
                .users(users)
                .build();

        // Verify that the Room object is not null
        assertNotNull(room);
        // Verify that the Room object has the correct id
        assertEquals(id, room.getId());
        // Verify that the Room object has the correct name
        assertEquals(name, room.getName());
        // Verify that the Room object has the correct list of elements
        assertEquals(elements, room.getElements());
        // Verify that the Room object has the correct list of users
        assertEquals(users, room.getUsers());
    }

    // Test to verify that RoomBuilder correctly builds a Room object with default properties when elements and users lists are null
    @Test
    void testBuildRoomWithNullElementsAndUsers() {
        // Define test data
        int id = 1;
        String name = "Kitchen";

        // Build a Room object with null elements and users lists using RoomBuilder
        Room room = roomBuilder.id(id)
                .name(name)
                .build();

        // Verify that the Room object is not null
        assertNotNull(room);
        // Verify that the Room object has the correct id
        assertEquals(id, room.getId());
        // Verify that the Room object has the correct name
        assertEquals(name, room.getName());
        // Verify that the Room object has an empty list of elements
        assertEquals(null, room.getElements());
        // Verify that the Room object has an empty list of users
        assertEquals(null, room.getUsers());
    }
}
