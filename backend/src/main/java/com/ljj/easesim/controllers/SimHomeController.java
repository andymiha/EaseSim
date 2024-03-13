package com.ljj.easesim.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljj.easesim.commands.ToggleLightCommand;
import com.ljj.easesim.commands.ToggleWindowCommand;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.requestBodies.ToggleRequest;
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

    @GetMapping("/getHouseLights")
    public String getHouseLights() {
        HouseLayout house = HouseLayout.getInstance();
        List<Map<String, Object>> roomsList = new ArrayList<>();

        house.getHouseLights().forEach((key, value) -> {
            Map<String, Object> room = new HashMap<>();
            room.put("roomName", key);
            room.put("light", value.getState());
            room.put("id", value.getId());
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
            room.put("roomName", key);
            room.put("window", value.getState());
            room.put("id", value.getId());
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
    public String toggleLight(@RequestBody ToggleRequest request) {
        HouseLayout house = HouseLayout.getInstance();
        AtomicReference<Room> room = new AtomicReference<Room>();
        AtomicReference<Light> light = new AtomicReference<Light>();

        house.getHouseLights().forEach((key, value) -> {
            if (value.getId() == request.getId()) {
                room.set(house.getRoom(key));
                light.set((Light) value);
            }
        });

        // Toggle using Command Design Pattern
        System.out.println(light.get().getState());
        room.get().setCommand(new ToggleLightCommand(light.get()));
        room.get().executeCommand();
        System.out.println(light.get().getState());

        return "Light state is: " + light.get().getState();
    }

    @PostMapping("/toggleWindow")
    public String toggleWindow(@RequestBody ToggleRequest request) {
        HouseLayout house = HouseLayout.getInstance();
        AtomicReference<Room> room = new AtomicReference<Room>();
        AtomicReference<Window> window = new AtomicReference<Window>();

        house.getHouseWindows().forEach((key, value) -> {
            if (value.getId() == request.getId()) {
                room.set(house.getRoom(key));
                window.set((Window) value);
            }
        });

        // Toggle using Command Design Pattern
        System.out.println(window.get().getState());
        room.get().setCommand(new ToggleWindowCommand(window.get()));
        room.get().executeCommand();
        System.out.println(window.get().getState());

        return "Window state is: " + window.get().getState();
    }
}
