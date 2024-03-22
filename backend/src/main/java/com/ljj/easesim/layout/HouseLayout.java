package com.ljj.easesim.layout;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ljj.easesim.builders.RoomBuilder;
import com.ljj.easesim.elements.*;
import com.ljj.easesim.abstractions.HouseElement;
import com.ljj.easesim.abstractions.User;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class HouseLayout {
    // ID Counter necessary for incrementing room id's when creating rooms.
    private int idCounter = 0;
    private ArrayList<Room> rooms;

    public HouseLayout() {
        rooms = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // Read Layout.txt
        // Iterate over obj array in the Layout.txt file
        try {
            File file = new File("Layout.txt");
            List<Map<String, Object>> layoutData = objectMapper.readValue(file, new TypeReference<>() {});
            Map<String, Door> doorMap = new HashMap<>();

            for(Map<String, Object> roomData : layoutData) {
                ArrayList<HouseElement> elements = new ArrayList<>();

                String name = (String) roomData.get("name");
                int windows = (int) roomData.get("windows");
                int lights = (int) roomData.get("lights");
                List<String> doorsTo = (List<String>) roomData.get("doorsTo");

                Light light = null;
                Window window = null;
                Door door = null;

                Room room  = new RoomBuilder()
                        .id(++idCounter)
                        .name(name)
                        .elements(elements)
                        .users(new ArrayList<User>())
                        .build();

                // Add Lights
                for(int i = 0; i < lights; i++) {
                    light = new Light();
                    light.setId(++idCounter);
                    elements.add(light);

                    if (light != null) {
                        light.setRoom(room);
                        System.out.println("added light " + light.toString());
                    } else {
                        System.out.println("light is null");
                    }
                }

                // Add Windows
                for(int i = 0; i < windows; i++) {
                    window = new Window();
                    window.setId(++idCounter);
                    elements.add(window);

                    if (window != null) {
                        window.setRoom(room);
                        System.out.println("added window " + window.toString());
                    } else {
                        System.out.println("window is null");
                    }
                }

                for(String roomConnectionName : doorsTo) {
                    if (doorMap.containsKey(roomConnectionName)) {
                        door = doorMap.get(roomConnectionName); // Use existing door
                    } else {
                        door = new Door(); // Create new door
                        door.setId(++idCounter);
                        doorMap.put(roomConnectionName, door); // Track the new door
                    }
                    elements.add(door);

                    if (door != null) {
                        door.setRoom(room);
                        System.out.println("added door " + door.toString());
                    } else {
                        System.out.println("door is null");
                    }
                }

                this.rooms.add(room);
                System.out.println("Created " + room.toString() + "\n");

                System.out.println("Testing getRoom on elements of Room " + room.getName() + "\n");
                for (HouseElement element : room.getElements()) {
                    Room location = element.getRoom();
                    if (location != null) {
                        // Do something with the room object
                        System.out.println("Room found for " + element.toString() + ": " + room.getName());
                    } else {
                        System.out.println("Room not found for " + element.toString());
                    }
                }
                System.out.println("\n-------------------------------------------------------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<HouseElement, String> getHouseDoors() {
        Map<HouseElement, String> doorsMap = new HashMap<>();
        for (Room room : this.rooms) {
            for (HouseElement element : room.getElements()) {
                if (element instanceof Door) {
                    doorsMap.put(element, room.getName());
                }
            }
        }
        return doorsMap;
    }

    public Map<HouseElement, String> getHouseLights() {
        Map<HouseElement, String> lightsMap = new HashMap<>();
        for (Room room : this.rooms) {
            for (HouseElement element : room.getElements()) {
                if (element instanceof Light) {
                    lightsMap.put(element, room.getName());
                }
            }
        }
        return lightsMap;
    }

    public Map<HouseElement, String> getHouseWindows() {
        Map<HouseElement, String> windowsMap = new HashMap<>();
        for (Room room : this.rooms) {
            for (HouseElement element : room.getElements()) {
                if (element instanceof Window) {
                    System.out.println(element.getId());
                    windowsMap.put(element, room.getName());
                }
            }
        }
        return windowsMap;
    }

    public Room getRoom(String name) {
        for (Room room : this.rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Light> getLights() {
        ArrayList<Light> lights = new ArrayList<>();
        for (Room room : this.rooms) {
            for (HouseElement element : room.getElements()) {
                if (element instanceof Light) {
                    lights.add((Light) element);
                }
            }
        }
        return lights;
    }
}
