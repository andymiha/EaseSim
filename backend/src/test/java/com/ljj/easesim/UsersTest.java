package com.ljj.easesim;

import com.ljj.easesim.layout.Room;
import com.ljj.easesim.users.Child;
import com.ljj.easesim.users.Guest;
import com.ljj.easesim.users.Parent;
import com.ljj.easesim.users.Stranger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {

    @Test
    void testGuestConstructorAndGetters() {
        Guest guest = new Guest(1, "John");
        assertEquals(1, guest.getId());
        assertEquals("John", guest.getName());
    }

    @Test
    void testChildConstructorAndGetters() {
        Child child = new Child(2, "Alice");
        assertEquals(2, child.getId());
        assertEquals("Alice", child.getName());
    }

    @Test
    void testParentConstructorAndGetters() {
        Parent parent = new Parent(3, "Bob");
        assertEquals(3, parent.getId());
        assertEquals("Bob", parent.getName());
    }

    @Test
    void testStrangerConstructorAndGetters() {
        Stranger stranger = new Stranger(4, "Jane");
        assertEquals(4, stranger.getId());
        assertEquals("Jane", stranger.getName());
    }

    @Test
    void testGuestHasRoomAccess() {
        // Creating a room with a guest
        Room room = new Room(1, "Living Room", new ArrayList<>(), new ArrayList<>(Arrays.asList(new Guest(1, "John"))));

        // Guest should have access to the room
        assertFalse(new Guest(1, "John").hasRoomAccess(room));

        // Other users should not have access to the room
        assertFalse(new Child(2, "Alice").hasRoomAccess(room));
        assertTrue(new Parent(3, "Bob").hasRoomAccess(room));
        assertFalse(new Stranger(4, "Jane").hasRoomAccess(room));
    }

    @Test
    void testChildHasRoomAccess() {
        // Creating a room with a child
        Room room = new Room(1, "Living Room", new ArrayList<>(), new ArrayList<>(List.of(new Child(2, "Alice"))));

        // Child should have access to the room
        assertFalse(new Child(2, "Alice").hasRoomAccess(room));

        // Other users should not have access to the room
        assertFalse(new Guest(1, "John").hasRoomAccess(room));
        assertTrue(new Parent(3, "Bob").hasRoomAccess(room));
        assertFalse(new Stranger(4, "Jane").hasRoomAccess(room));
    }

    @Test
    void testParentHasRoomAccess() {
        // Creating a room with a parent
        Room room = new Room(1, "Living Room", new ArrayList<>(), new ArrayList<>(List.of(new Parent(3, "Bob"))));

        // Parent should have access to the room
        assertTrue(new Parent(3, "Bob").hasRoomAccess(room));

        // Other users should not have access to the room
        assertFalse(new Guest(1, "John").hasRoomAccess(room));
        assertFalse(new Child(2, "Alice").hasRoomAccess(room));
        assertFalse(new Stranger(4, "Jane").hasRoomAccess(room));
    }

    @Test
    void testStrangerHasRoomAccess() {
        // Creating a room without any users
        Room room = new Room(1, "Living Room", new ArrayList<>(), new ArrayList<>());

        // Stranger should not have access to the room
        assertFalse(new Stranger(4, "Jane").hasRoomAccess(room));

        // Other users should not have access to the room
        assertFalse(new Guest(1, "John").hasRoomAccess(room));
        assertFalse(new Child(2, "Alice").hasRoomAccess(room));
        assertTrue(new Parent(3, "Bob").hasRoomAccess(room));
    }

}
