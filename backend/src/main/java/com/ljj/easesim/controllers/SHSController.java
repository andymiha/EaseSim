package com.ljj.easesim.controllers;

import com.ljj.easesim.SmartHomeSecurity;
import com.ljj.easesim.SmartHomeSimulator;
import com.ljj.easesim.users.*;
import com.ljj.easesim.abstractions.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/smart-home-security")
public class SHSController {

    private final SmartHomeSimulator shs = SmartHomeSimulator.getInstance();

    @PostMapping("/add-user-to-room/{roomName}")
    public String addUserToRoom(@PathVariable String roomName, @RequestBody User user) {
        boolean added = shs.getHouseLayout().getRoom(roomName).addUser(user);
        if (added) {
            return "User added to room " + roomName + ": " + user.toString();
        } else {
            return "Failed to add user to room " + roomName;
        }
    }
}
