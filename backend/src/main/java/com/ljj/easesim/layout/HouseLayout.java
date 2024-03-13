package com.ljj.easesim.layout;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ljj.easesim.builders.RoomBuilder;
import com.ljj.easesim.elements.*;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.User;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class HouseLayout {
    // ID Counter necessary for incrementing room id's when creating rooms.
    private int idCounter = 0;
    private ArrayList<Room> rooms;
    private static final HouseLayout INSTANCE = new HouseLayout();

    public HouseLayout() {
        // Example House Layout Constructor
        rooms = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // Read Layout.txt
        // Iterate over obj array in the Layout.txt file
        try {
            File file = new File("Layout.txt");
            List<Map<String, Object>> layoutData = objectMapper.readValue(file, new TypeReference<>() {});
            Map<String, Door> doorMap = new HashMap<>();

            for(Map<String, Object> roomData : layoutData) {
                int windows = (int) roomData.get("windows");
                int lights = (int) roomData.get("lights");

                String name = (String) roomData.get("name");
                List<String> doorsTo = (List<String>) roomData.get("doorsTo");

                ArrayList<HouseElement> elements = new ArrayList<>();

                // Add Lights
                for(int i = 0; i <= lights; i++) {
<<<<<<< HEAD
                    elements.add(new Light());
=======
                    Light light = new Light();
                    light.setId(++idCounter);
                    elements.add(light);
>>>>>>> staging
                }

                // Add Windows
                for(int i = 0; i <= windows; i++) {
<<<<<<< HEAD
                    elements.add(new Window());
=======
                    Window window = new Window();
                    window.setId(++idCounter);
                    elements.add(window);
>>>>>>> staging
                }

                Room room = new RoomBuilder()
                        .id(++idCounter)
                        .name(name)
                        .elements(elements)
                        .users(new ArrayList<User>())
                        .build();

                for(String roomConnectionName : doorsTo) {
                    Door door;
                    if (doorMap.containsKey(roomConnectionName)) {
                        door = doorMap.get(roomConnectionName); // Use existing door
                    } else {
                        door = new Door(); // Create new door
                        door.setId(++idCounter);
                        doorMap.put(roomConnectionName, door); // Track the new door
                    }
                    room.addElement(door);
                }

                this.rooms.add(room);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD
=======
    public static HouseLayout getInstance() {
        return INSTANCE;
    }

    public Map<String, HouseElement> getHouseElements() {
        Map<String, HouseElement> elementsMap = new HashMap<>();
        for (Room room : this.rooms) {
            for (HouseElement element : room.getElements()) {
                elementsMap.put(room.getName(), element);
            }
        }
        return elementsMap;
    }

    public Map<String, HouseElement> getHouseDoors() {
        Map<String, HouseElement> doorsMap = new HashMap<>();
        for (Room room : this.rooms) {
            for (HouseElement element : room.getElements()) {
                if (element instanceof Door) {
                    doorsMap.put(room.getName(), element);
                }
            }
        }
        return doorsMap;
    }

    public Map<String, HouseElement> getHouseLights() {
        Map<String, HouseElement> lightsMap = new HashMap<>();
        for (Room room : this.rooms) {
            for (HouseElement element : room.getElements()) {
                if (element instanceof Light) {
                    lightsMap.put(room.getName(), element);
                }
            }
        }
        return lightsMap;
    }

    public Map<String, HouseElement> getHouseWindows() {
        Map<String, HouseElement> windowsMap = new HashMap<>();
        for (Room room : this.rooms) {
            for (HouseElement element : room.getElements()) {
                if (element instanceof Window) {
                    windowsMap.put(room.getName(), element);
                }
            }
        }
        return windowsMap;
    }

>>>>>>> staging
    public Room getRoom(String name) {
        for (Room room : this.rooms) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }
<<<<<<< HEAD
=======

    public List<Room> getRooms() {
        return rooms;
    }
>>>>>>> staging
}
