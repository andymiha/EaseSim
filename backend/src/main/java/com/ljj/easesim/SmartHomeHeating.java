package com.ljj.easesim;

import com.ljj.easesim.layout.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartHomeHeating {
    private final HouseLayout house = SmartHomeSimulator.getInstance().getHouseLayout();
    private static final SmartHomeHeating INSTANCE = new SmartHomeHeating();
    private boolean isActive;
    private Map<String, List<Room>> heatingZones;

    // Log all actions (save in log file) and display in console. (Observer)

    private SmartHomeHeating() {
        isActive = false;
        heatingZones = new HashMap<>();
    }

    public boolean isActive() {
        return isActive;
    }

    public void toggleActive() {
        isActive = !isActive;
    }
    public static SmartHomeHeating getInstance() {
        return INSTANCE;
    }

    public void createHeatingZone(String zoneName) {
        if (!heatingZones.containsKey(zoneName)) {
            heatingZones.put(zoneName, new ArrayList<>());
        }
    }

    public void addRoomToHeatingZone(String zoneName, Room room) {
        if (!heatingZones.containsKey(zoneName)) {
            throw new IllegalArgumentException("Heating zone '" + zoneName + "' does not exist.");
        }
        heatingZones.get(zoneName).add(room);
    }

    public void removeRoomFromHeatingZone(String zoneName, Room room) {
        if (!heatingZones.containsKey(zoneName)) {
            throw new IllegalArgumentException("Heating zone '" + zoneName + "' does not exist.");
        }
        heatingZones.get(zoneName).remove(room);
    }

    public List<Room> getRoomsInHeatingZone(String zoneName) {
        return heatingZones.getOrDefault(zoneName, new ArrayList<>());
    }
}
