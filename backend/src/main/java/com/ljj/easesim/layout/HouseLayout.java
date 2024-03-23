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
    // ID Counter necessary for incrementing room ids when creating rooms.
    private int idCounter = 0;
    private ArrayList<Room> rooms;

    // Constructor
    public HouseLayout() {
        rooms = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Open the Layout.txt File
            File file = new File("Layout.txt");

            // Read Layout.txt
            List<Map<String, Object>> layoutData = objectMapper.readValue(file, new TypeReference<>() {});
            Map<String, Door> doorMap = new HashMap<>();

            // Iterate over obj array in the Layout.txt file and create Rooms
            for(Map<String, Object> roomData : layoutData) {
                ArrayList<HouseElement> elements = new ArrayList<>();

                String name = (String) roomData.get("name");
                int windows = (int) roomData.get("windows");
                int lights = (int) roomData.get("lights");
                List<String> doorsTo = (List<String>) roomData.get("doorsTo");

                // Build empty room
                Room room  = new RoomBuilder()
                        .id(++idCounter)
                        .name(name)
                        .elements(elements)
                        .users(new ArrayList<User>())
                        .build();

                // Populate elements collection
                Light light = null;
                Window window = null;
                Door door = null;

                // Add Lights
                for(int i = 0; i < lights; i++) {
                    light = new Light();
                    light.setId(++idCounter);
                    elements.add(light);
                    light.setRoom(room);
                }

                // Add Windows
                for(int i = 0; i < windows; i++) {
                    window = new Window();
                    window.setId(++idCounter);
                    elements.add(window);
                    window.setRoom(room);
                }

                // Add Doors
                for(String roomConnectionName : doorsTo) {
                    if (doorMap.containsKey(roomConnectionName)) {
                        door = doorMap.get(roomConnectionName); // Use existing door
                    } else {
                        door = new Door(); // Create new door
                        door.setId(++idCounter);
                        doorMap.put(roomConnectionName, door); // Track the new door
                        door.setRoom(room);
                    }
                    elements.add(door);

                }

                // Add Rooms
                this.rooms.add(room);

                // Display Separator Line
                System.out.println("\n" + "-".repeat(700));

                // Display room content
                System.out.println("Created " + room.toString() + "\n");

                // Display getRoom test results
                System.out.println("Testing getRoom on elements of Room " + room.getName() + "\n");
                for (HouseElement element : room.getElements()) {
                    Room location = element.getRoom();
                    if (location != null) {
                        System.out.println("Room found for " + element.toString() + ": " + room.getName());
                    } else {
                        System.out.println("Room not found for " + element.toString());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methods
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
