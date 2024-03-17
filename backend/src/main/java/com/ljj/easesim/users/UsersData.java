package com.ljj.easesim.users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.easesim.interfaces.User; // Import the User interface
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersData {
    private List<User> users;

    // list
    private static final UsersData INSTANCE = new UsersData();

    private UsersData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("usersData.json"); // Assuming the JSON file is named users.json
            users = objectMapper.readValue(file, new TypeReference<List<User>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            users = new ArrayList<>(); // Initialize an empty list in case of failure
        }
    }

    public static UsersData getInstance() {
        return INSTANCE;
    }

    public List<User> getUsers() {
        return users;
    }
}
