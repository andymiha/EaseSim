package com.ljj.easesim;

import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.interfaces.Command;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.User;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.users.Parent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RoomTest {

    @Test
    void testGetId() {
        Room room = new Room(1, "LivingRoom", new ArrayList<>(), new ArrayList<>());
        assertEquals(1, room.getId());
    }

    @Test
    void testGetName() {
        Room room = new Room(1, "LivingRoom", new ArrayList<>(), new ArrayList<>());
        assertEquals("LivingRoom", room.getName());
    }

    @Test
    void testGetCommand() {
        Room room = new Room(1, "Living Room", new ArrayList<>(), new ArrayList<>());
        assertNull(room.getCommand());
    }

    @Test
    void testGetUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new Parent(1, "Sarah"));
        Room room = new Room(1, "Living Room", new ArrayList<>(), users);
        assertEquals(1, room.getUsers().size());
    }

    @Test
    void testGetElements() {
        ArrayList<HouseElement> elements = new ArrayList<>();
        elements.add(new Window());
        elements.add(new Light());
        Room room = new Room(1, "Living Room", elements, new ArrayList<>());
        assertEquals(2, room.getElements().size());
    }

    @Test
    void testGetDoors() {
        ArrayList<HouseElement> elements = new ArrayList<>();
        elements.add(new Window());
        elements.add(new Door());
        elements.add(new Door());
        Room room = new Room(1, "Living Room", elements, new ArrayList<>());
        assertEquals(2, room.getDoors().size());
    }

    @Test
    void testSetCommand() {
        Room room = new Room(1, "Living Room", new ArrayList<>(), new ArrayList<>());
        Command command = mock(Command.class);
        room.setCommand(command);
        assertEquals(command, room.getCommand());
    }

    @Test
    void testExecuteCommand() {
        Command command = mock(Command.class);

        Room room = new Room(1, "Living Room", new ArrayList<>(), new ArrayList<>());
        room.setCommand(command);
        room.executeCommand();

        // Verify that the command's execute method was called
        verify(command, times(1)).execute();
    }

    @Test
    void testAddElement() {
        Room room = new Room(1, "LivingRoom", new ArrayList<>(), new ArrayList<>());
        room.addElement(new Window());
        assertEquals(1, room.getElements().size());
    }

    @Test
    void testRemoveElement() {
        HouseElement window = new Window();
        Room room = new Room(1, "LivingRoom", new ArrayList<>(), new ArrayList<>());
        room.addElement(window);
        assertEquals(1, room.getElements().size());
        room.removeElement(window);
        assertEquals(0, room.getElements().size());
    }


    @Test
    void testAddUser() {
        Room room = new Room(1, "LivingRoom", new ArrayList<>(), new ArrayList<>());
        room.addUser(new Parent(1, "Sarah"));
        assertEquals(1, room.getUsers().size());
    }

    @Test
    void testRemoveUser() {
        User user = new Parent(1, "Sarah");
        Room room = new Room(1, "LivingRoom", new ArrayList<>(), new ArrayList<>());
        room.addUser(user);
        assertEquals(1, room.getUsers().size());
        room.removeUser(user);
        assertEquals(0, room.getUsers().size());
    }

    @Test
    void testRoomConstructorAndGetters() {
        ArrayList<HouseElement> elements = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        Room room = new Room(1, "Living Room", elements, users);

        assertEquals(1, room.getId());
        assertEquals("Living Room", room.getName());
        assertEquals(elements, room.getElements());
        assertEquals(users, room.getUsers());
    }

}
