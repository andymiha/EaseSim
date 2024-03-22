package com.ljj.easesim.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.easesim.SmartHomeCore;
import com.ljj.easesim.SmartHomeSimulator;
import com.ljj.easesim.commands.*;
import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.interfaces.User;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.requestBodies.ToggleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class SimHomeController {

    @GetMapping("/getUsers")
    public String getUsers() {
        SmartHomeSimulator shs = SmartHomeSimulator.getInstance();

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(shs.getUsers());
        } catch (Exception e) {
            // Handle the error properly
            e.printStackTrace();
            return "Error converting to JSON";
        }
    }

    @GetMapping("/getHouseLights")
    public String getHouseLights() {
        // test commit
        HouseLayout house = HouseLayout.getInstance();
        List<Map<String, Object>> roomsList = new ArrayList<>();

        house.getHouseLights().forEach((key, value) -> {
            Map<String, Object> room = new HashMap<>();
            Light light = (Light) key;
            room.put("roomName", value);
            room.put("isAuto", light.getIsAutoState());
            room.put("state", light.getState());
            room.put("id", light.getId());
            roomsList.add(room);
        });

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(roomsList);
        } catch (Exception e) {
            // Handle the error properly
            e.printStackTrace();
            return "Error converting to JSON";
        }
    }

    @GetMapping("/getHouseWindows")
    public String getHouseWindows() {
        HouseLayout house = HouseLayout.getInstance();
        List<Map<String, Object>> roomsList = new ArrayList<>();

        house.getHouseWindows().forEach((key, value) -> {
            Map<String, Object> room = new HashMap<>();
            Window window = (Window) key;
            room.put("roomName", value);
            room.put("isBlocked", window.getBlockedState());
            room.put("state", window.getState());
            room.put("id", window.getId());
            roomsList.add(room);
        });

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(roomsList);
        } catch (Exception e) {
            // Handle the error properly
            e.printStackTrace();
            return "Error converting to JSON";
        }
    }

    @GetMapping("/getHouseDoors")
    public String getHouseDoors() {
        HouseLayout house = HouseLayout.getInstance();
        List<Map<String, Object>> roomsList = new ArrayList<>();

        house.getHouseDoors().forEach((key, value) -> {
            Map<String, Object> room = new HashMap<>();
            Door door = (Door) key;
            room.put("roomName", value);
            room.put("isAuto", door.getIsAutoState());
            room.put("state", door.getState());
            room.put("id", door.getId());
            roomsList.add(room);
        });

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(roomsList);
        } catch (Exception e) {
            // Handle the error properly
            e.printStackTrace();
            return "Error converting to JSON";
        }
    }

    @PostMapping("/toggleLight")
    public ResponseEntity<Map<String, Object>> toggleLight(@RequestBody ToggleRequest request) {
        HouseLayout house = HouseLayout.getInstance();
        SmartHomeSimulator shs = SmartHomeSimulator.getInstance();
        SmartHomeCore shc = SmartHomeCore.getInstance();

        // Find Light using id
        Light foundLight = null;
        for (Light light : house.getLights()) {
            if (light.getId() == request.getId()) {
                foundLight = light;
            }
        }

        // Find User using id
        User foundUser = null;
        for (User user : shs.getUsers()) {
            if (user.getId() == request.getUserId()) {
                foundUser = user;
            }
        }

        foundLight = shc.toggle(foundLight, foundUser);

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("state", foundLight.getState());
        // Return the response
        return ResponseEntity.ok(response);
    }

    @PostMapping("/toggleWindow")
    public ResponseEntity<Map<String, Object>> toggleWindow(@RequestBody ToggleRequest request) {
        HouseLayout house = HouseLayout.getInstance();
        AtomicReference<Room> room = new AtomicReference<Room>();
        AtomicReference<Window> window = new AtomicReference<Window>();

        house.getHouseWindows().forEach((key, value) -> {
            if (key.getId() == request.getId()) {
                room.set(house.getRoom(value));
                window.set((Window) key);
            }
        });

        // Toggle using Command Design Pattern
        System.out.println(window.get().getState());
        room.get().setCommand(new ToggleWindowCommand(window.get()));
        room.get().executeCommand();
        System.out.println(window.get().getState());
        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("state", window.get().getState());
        // Return the response
        return ResponseEntity.ok(response);
    }

    @PostMapping("/toggleDoor")
    public ResponseEntity<Map<String, Object>> toggleDoor(@RequestBody ToggleRequest request) {
        HouseLayout house = HouseLayout.getInstance();
        AtomicReference<Room> room = new AtomicReference<Room>();
        AtomicReference<Door> door = new AtomicReference<Door>();

        house.getHouseDoors().forEach((key, value) -> {
            if (key.getId() == request.getId()) {
                room.set(house.getRoom(value));
                door.set((Door) key);
            }
        });

        // Toggle using Command Design Pattern
        System.out.println(door.get().getState());
        room.get().setCommand(new ToggleDoorCommand(door.get()));
        room.get().executeCommand();
        System.out.println(door.get().getState());
        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("state", door.get().getState());
        // Return the response
        return ResponseEntity.ok(response);
    }

    @PostMapping("/toggleIsAutoLight")
    public ResponseEntity<Map<String, Object>> toggleIsAutoLight(@RequestBody ToggleRequest request) {
        HouseLayout house = HouseLayout.getInstance();
        AtomicReference<Room> room = new AtomicReference<Room>();
        AtomicReference<Light> light = new AtomicReference<Light>();

        house.getHouseLights().forEach((key, value) -> {
            if (key.getId() == request.getId()) {
                room.set(house.getRoom(value));
                light.set((Light) key);
            }
        });

        // Toggle using Command Design Pattern
        System.out.println(light.get().getState());
        room.get().setCommand(new ToggleIsAutoLightCommand(light.get()));
        room.get().executeCommand();
        System.out.println(light.get().getState());
        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("state", light.get().getIsAutoState());
        // Return the response
        return ResponseEntity.ok(response);
    }

    @PostMapping("/toggleIsAutoDoor")
    public ResponseEntity<Map<String, Object>> toggleIsAutoDoor(@RequestBody ToggleRequest request) {
        HouseLayout house = HouseLayout.getInstance();
        AtomicReference<Room> room = new AtomicReference<Room>();
        AtomicReference<Door> door = new AtomicReference<Door>();

        house.getHouseDoors().forEach((key, value) -> {
            if (key.getId() == request.getId()) {
                room.set(house.getRoom(value));
                door.set((Door) key);
            }
        });

        // Toggle using Command Design Pattern
        System.out.println(door.get().getState());
        room.get().setCommand(new ToggleIsAutoDoorCommand(door.get()));
        room.get().executeCommand();
        System.out.println(door.get().getState());
        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Success");
        response.put("state", door.get().getIsAutoState());
        // Return the response
        return ResponseEntity.ok(response);
    }
}
