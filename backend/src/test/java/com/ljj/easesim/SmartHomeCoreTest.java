package com.ljj.easesim;

import com.ljj.easesim.abstractions.User;
import com.ljj.easesim.commands.ToggleWindowCommand;
import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.layout.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SmartHomeCoreTest {

    @Mock
    User mockUser;

    Room mockRoom;

    @Test
    void testToggleWindow() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Create a window object with ID 1
        Window window = new Window();

        // Mock the findElementRoom method to return the mock room
        SmartHomeCore smartHomeCore = spy(SmartHomeCore.getInstance()); // Spy on the real instance to mock findElementRoom
        doReturn(mockRoom).when(smartHomeCore).findElementRoom(any(Window.class));

        // Call the toggle method with the window object

        Window toggledWindow = smartHomeCore.toggle(window);

        if (toggledWindow == null){
            System.out.println("Window is null");
        }

        // Verify that findElementRoom was called with the correct window object
        verify(smartHomeCore).findElementRoom(window);

        // Verify that setCommand and executeCommand were called on the mock room
        verify(mockRoom).setCommand(any(ToggleWindowCommand.class));
        verify(mockRoom).executeCommand();

        // Assert that the toggledWindow is the same as the original window
        assertSame(window, toggledWindow);
    }

    @Test
    void testToggleWindowWithPermissions() {
        SmartHomeCore smartHomeCore = SmartHomeCore.getInstance();
        Window window = new Window();
        // Mock a user without room access
        User user = mock(User.class);
        when(user.hasRoomAccess(mock(Room.class))).thenReturn(false);

        // Toggle the light without user permissions
        Window toggledWindow = smartHomeCore.toggle(window, user);

        // Assert that the light state remains unchanged
        assertEquals(window.getState(), toggledWindow.getState());
    }

    @Test
    void testToggleDoorWithPermissions() {
        SmartHomeCore smartHomeCore = SmartHomeCore.getInstance();
        Door door = new Door();
        // Mock a user without room access
        User user = mock(User.class);
        when(user.hasRoomAccess(mock(Room.class))).thenReturn(false);

        // Toggle the light without user permissions
        Door toggledDoor = smartHomeCore.toggle(door, user);

        // Assert that the light state remains unchanged
        assertEquals(door.getState(), toggledDoor.getState());
    }

    @Test
    void testToggleLightWithPermissions() {
        SmartHomeCore smartHomeCore = SmartHomeCore.getInstance();
        Light light = new Light();
        // Mock a user without room access
        User user = mock(User.class);
        when(user.hasRoomAccess(mock(Room.class))).thenReturn(false);

        // Toggle the light without user permissions
        Light toggledLight = smartHomeCore.toggle(light, user);

        // Assert that the light state remains unchanged
        assertEquals(light.getState(), toggledLight.getState());
    }

    @Test
    void testToggleIsAutoDoorWithPermissions() {
        SmartHomeCore smartHomeCore = SmartHomeCore.getInstance();
        Door door = new Door();
        // Mock a user without room access
        User user = mock(User.class);
        when(user.hasRoomAccess(mock(Room.class))).thenReturn(false);

        // Toggle the auto mode of the light without user permissions
        Door toggledDoor = smartHomeCore.toggleIsAuto(door, user);

        // Assert that the auto mode state remains unchanged
        assertEquals(door.getIsAutoState(), toggledDoor.getIsAutoState());
    }


    @Test
    void testToggleIsAutoLightWithPermissions() {
        SmartHomeCore smartHomeCore = SmartHomeCore.getInstance();
        Light light = new Light();
        // Mock a user without room access
        User user = mock(User.class);
        when(user.hasRoomAccess(mock(Room.class))).thenReturn(false);

        // Toggle the auto mode of the light without user permissions
        Light toggledLight = smartHomeCore.toggleIsAuto(light, user);

        // Assert that the auto mode state remains unchanged
        assertEquals(light.getIsAutoState(), toggledLight.getIsAutoState());
    }

    @Test
    void testToggleLightWithNullElementRoom() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Create a light object with ID 1 (assuming it's not found in any room)
        Light light = new Light();

        // Mock the findElementRoom method to return null
        SmartHomeCore smartHomeCore = spy(SmartHomeCore.getInstance()); // Spy on the real instance to mock findElementRoom
        doReturn(null).when(smartHomeCore).findElementRoom(any(Light.class));

        // Call the toggle method with the mock user
        Light toggledLight = smartHomeCore.toggle(light, mockUser);

        // Verify that findElementRoom was called with the correct light object
        verify(smartHomeCore).findElementRoom(light);

        // Verify that the toggle command was not executed (elementRoom is null)
        verify(mockUser, never()).hasRoomAccess(any(Room.class));
        verify(mockUser, never()).getId(); // Assuming getId() is a method in the User class

        // Assert that the toggledLight is the same as the original light (not toggled)
        assertSame(light, toggledLight);
    }
}
