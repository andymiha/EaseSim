package com.ljj.easesim;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.easesim.abstractions.User;
import com.ljj.easesim.layout.HouseLayout;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class SmartHomeSimulatorTest {
    private SmartHomeSimulator smartHomeSimulator;
    private HouseLayout mockedHouseLayout;
    private User mockedUser;

    private ObjectMapper mockObjectMapper;

    @BeforeEach
    void setUp() {
        smartHomeSimulator = SmartHomeSimulator.getInstance();
        SmartHomeSimulator mockedSmartHomeSimulator = mock(SmartHomeSimulator.class);
        mockedHouseLayout = mock(HouseLayout.class);
        mockedUser = mock(User.class);
        mockObjectMapper = mock(ObjectMapper.class);
    }

    @Test
    void testGetInstance() {
        assertNotNull(smartHomeSimulator); // Check that instance is not null
    }

    @Test
    void testGetUsers() {
        ArrayList<User> users = smartHomeSimulator.getUsers();
        assertNotNull(users); // Check that users list is not null
    }

    @Test
    void testGetHouseLayout() {
        HouseLayout houseLayout = smartHomeSimulator.getHouseLayout();
        assertNotNull(houseLayout); // Check that house layout is not null
    }

    @Test
    void testGetUser() {
        when(mockedUser.getId()).thenReturn(0);
        smartHomeSimulator.addUser(0, "Parent", "John Doe");

        User user = smartHomeSimulator.getUser(0);
        assertEquals("John Doe", user.getName()); // Check that user with ID 1 is retrieved
    }

    @Test
    void testAddUser() {
        User newUser = smartHomeSimulator.addUser(2, "Child", "Alice");
        assertNotNull(newUser); // Check that new user is added successfully
        assertEquals(2, newUser.getId()); // Check that new user has the correct ID
    }

    @Test
    void testDeleteUser() {
        smartHomeSimulator.addUser(3, "Guest", "Jane Smith");
        smartHomeSimulator.deleteUser(3);
        User deletedUser = smartHomeSimulator.getUser(3);
        assertEquals(null, deletedUser); // Check that user with ID 3 is deleted
    }

    @Test
    void testMapUsersFromJsonFileNotFound() throws IOException {
        SmartHomeSimulator smartHomeSimulator = new SmartHomeSimulator();
        String fileName = "nonexistent.json";

        File mockFile = mock(File.class);
        when(mockFile.exists()).thenReturn(false);

        smartHomeSimulator.mapUsersFromJson(fileName);

        verify(mockObjectMapper, never()).readValue(eq(mockFile), any(TypeReference.class));
    }
    }

