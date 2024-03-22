package com.ljj.easesim;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.easesim.abstractions.User;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.users.Child;
import com.ljj.easesim.users.Guest;
import com.ljj.easesim.users.Parent;
import com.ljj.easesim.users.Stranger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.Integer.parseInt;

// Allow upload CSV for temperature info.
public class SmartHomeSimulator {
    private static final SmartHomeSimulator INSTANCE = new SmartHomeSimulator();
    private ArrayList<User> users;
    private User loggedInUser;

    public SmartHomeSimulator() {
        ObjectMapper objectMapper = new ObjectMapper();
        users = new ArrayList<>();

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
        HouseLayout.getInstance().getRoom("Bathroom").addUser(getUser(2));
    }

    public static SmartHomeSimulator getInstance() {
        return INSTANCE;
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

    // God API Method
}
