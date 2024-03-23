package com.ljj.easesim;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.easesim.abstractions.HouseElement;
import com.ljj.easesim.abstractions.User;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.elements.*;
import com.ljj.easesim.users.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Integer.parseInt;

// Allow upload CSV for temperature info.
public class SmartHomeSimulator {
    private static SmartHomeSimulator INSTANCE = null;
    private ArrayList<User> users;
    private User loggedInUser;
    private final HouseLayout houseLayout;

    private final SmartHomeCore shc;

    public SmartHomeSimulator() {
        users = new ArrayList<>();
        houseLayout = new HouseLayout();
        shc = SmartHomeCore.getInstance();


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("db.json");
            Map<String, Object> dbData = objectMapper.readValue(file, new TypeReference<>() {});
            Map<String, Map<String, Object>> profiles = (Map<String, Map<String, Object>>) dbData.get("profiles");

            for (Map.Entry<String, Map<String, Object>> entry : profiles.entrySet()) {
                String userKey = entry.getKey();
                Map<String, Object> details = entry.getValue();
                User user = addUser(parseInt(userKey), details.get("userType").toString(), details.get("name").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Place 'Guest' in room to test permissions
        //HouseLayout.getInstance().getRoom("Bathroom").addUser(getUser(2));
    }

    public void toggleLight(Light light) {
        shc.toggle(light); // Use SHC method to toggle light
    }

    public void toggleWindow(Window window) {
        shc.toggle(window); // Use SHC method to toggle window
    }

    public void toggleDoor(Door door) {
        shc.toggle(door); // Use SHC method to toggle door
    }

    public void toggleIsAutoLight(Light light) {
        shc.toggleIsAuto(light); // Use SHC method to toggle light's auto state
    }

    public void toggleIsAutoDoor(Door door, User user) {
        shc.toggleIsAuto(door, user); // Use SHC method to toggle door's auto state with user permissions
    }

    public static SmartHomeSimulator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SmartHomeSimulator(); // Create new instance if null
        }
        return INSTANCE;
    }

    public HouseLayout getHouseLayout() {
        return houseLayout;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // User not found
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users); // Return a copy of the users list
    }

    public User addUser(int id, String userType, String name) {
        // Generating a unique ID for the new user
        int newId = (id >= 0) ? id : users.stream().mapToInt(User::getId).max().orElse(-1) + 1;

        User newUser = null;
        switch (userType) {
            case "Child":
                newUser = new Child(newId, name);
                break;
            case "Guest":
                newUser = new Guest(newId, name);
                break;
            case "Parent":
                newUser = new Parent(newId, name);
                break;
            case "Stranger":
                newUser = new Stranger(newId, name);
                break;
        }
        if (newUser != null) {
            users.add(newUser);
        }

        return newUser;
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    public void testSHCFunctions() {
        // Create sample light, window, and door
        Light sampleLight = houseLayout.getRooms().get(0).getLights().get(0);
        Window sampleWindow = houseLayout.getRooms().get(0).getWindows().get(0);
        Door sampleDoor = houseLayout.getRooms().get(0).getDoors().get(0);

        // Test toggleLight function
        System.out.println("Testing toggleLight for " + sampleLight.toString());
        toggleLight(sampleLight);
        System.out.println("Result: " + sampleLight.toString());


        // Test toggleWindow function
        System.out.println("\nTesting toggleWindow for " + sampleWindow.toString());
        toggleWindow(sampleWindow);
        System.out.println("Result: " + sampleWindow.toString());


        // Test toggleDoor function
        System.out.println("\nTesting toggleDoor for " + sampleDoor.toString());
        toggleDoor(sampleDoor);
        System.out.println("Result: " + sampleDoor.toString());


        // Test toggleIsAutoLight function
        System.out.println("\nTesting toggleIsAutoLight for " + sampleLight.toString());
        toggleIsAutoLight(sampleLight);
        System.out.println("Result: " + sampleLight.toString());

        // Test toggleIsAutoDoor function with a sample user
        System.out.println("\nTesting toggleLIsAutoDoor for " + sampleDoor.toString());
        User sampleUser = new Guest(1, "John Doe");
        toggleIsAutoDoor(sampleDoor, sampleUser);
        System.out.println("Result: " + sampleDoor.toString());

        //fix setRoom to doors (multiple rooms to doors)
    }

    // God API Method
}
