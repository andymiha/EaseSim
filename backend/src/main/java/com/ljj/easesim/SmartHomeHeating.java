package com.ljj.easesim;

import com.ljj.easesim.layout.*;
import com.ljj.easesim.abstractions.*;
import com.ljj.easesim.services.HVAC;
import com.ljj.easesim.services.HeatingZone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartHomeHeating implements TemperatureObserver, TemperatureObservable{
    private final HouseLayout house = SmartHomeSimulator.getInstance().getHouseLayout();
    private static final SmartHomeHeating INSTANCE = new SmartHomeHeating();
    private boolean isActive;
    private Map<String, HeatingZone> heatingZones;
    private double averageIndoorTemp;
    private HVAC hvac;
    private TemperatureObserver temperatureObserver;


    // Log all actions (save in log file) and display in console. (Observer)

    private SmartHomeHeating() {
        isActive = false;
        heatingZones = new HashMap<>();
        averageIndoorTemp = calculateAverageZoneTemperature();
        generateHeatingZones();
        SmartHomeSimulator.getInstance().registerObserver(this);
        this.hvac = HVAC.getInstance();
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


    @Override
    public void updateTemperature(double outdoorTemperature) {
        // Update heating based on temperature changes
        if (isActive) {
            hvac.setOutsideTemperature(outdoorTemperature);
        }
    }

    @Override
    public void registerObserver(TemperatureObserver observer) {
        this.temperatureObserver = observer;
    }

    @Override
    public void removeObserver(TemperatureObserver observer) {
        this.temperatureObserver = null;
    }

    @Override
    public void notifyObservers() {
        temperatureObserver.updateTemperature(averageIndoorTemp);
    }

    public Map<String, HeatingZone> getHeatingZones() {
        return heatingZones;
    }

    public boolean createHeatingZone(String zoneName) {
        if (!heatingZones.containsKey(zoneName)) {
            heatingZones.put(zoneName, new HeatingZone(zoneName));
            return true;
        }
        return false;
    }

    public boolean deleteHeatingZone(String zoneName) {
        if (!heatingZones.containsKey(zoneName)) {
            heatingZones.remove(zoneName);
            return true;
        }
        return false;
    }

    public boolean addRoomToHeatingZone(String zoneName, Room room) {
        HeatingZone heatingZone = heatingZones.get(zoneName);
        if (heatingZone != null && room != null) {
            heatingZone.addRoom(room);
            return true;
        }
        return false;
    }


    public boolean removeRoomFromHeatingZone(String zoneName, Room room) {
        HeatingZone heatingZone = heatingZones.get(zoneName);
        if (heatingZone != null && room != null) {
            heatingZone.removeRoom(room);
            return true;
        }
        return false;
    }

    public List<Room> getRoomsInHeatingZone(String zoneName) {
        HeatingZone heatingZone = heatingZones.get(zoneName);
        return heatingZone != null ? heatingZone.getRooms() : new ArrayList<>();
    }

    public void assignHVACZone(String zoneName) {
        HeatingZone heatingZone = heatingZones.get(zoneName);
        if (heatingZone != null) {
            hvac.setZone(heatingZone);
        }
        System.out.println(zoneName);

        //show a message if heating zone fails !
    }

    public double calculateAverageZoneTemperature() {
        if (heatingZones.isEmpty()) {
            return 0.0; // Return 0 if no zones are present
        }

        double totalTemperature = 0.0;
        int zoneCount = 0;

        for (HeatingZone zone : heatingZones.values()) {
            totalTemperature += zone.getCurrentZoneTemp();
            zoneCount++;
        }

        System.out.println("\nindoorTemp: " + (totalTemperature / zoneCount));

        return totalTemperature / zoneCount;
    }


    public void generateHeatingZones() {
        createHeatingZone("Garage");
        addRoomToHeatingZone("Garage", HouseLayout.getInstance().getRoom("Garage"));

        createHeatingZone("Zone1");
        addRoomToHeatingZone("Zone1", HouseLayout.getInstance().getRoom("Bedroom1"));
        addRoomToHeatingZone("Zone1", HouseLayout.getInstance().getRoom("Bedroom2"));


        createHeatingZone("Zone2");
        addRoomToHeatingZone("Zone2", HouseLayout.getInstance().getRoom("Bathroom"));
        addRoomToHeatingZone("Zone2", HouseLayout.getInstance().getRoom("LivingRoom"));
        addRoomToHeatingZone("Zone2", HouseLayout.getInstance().getRoom("Kitchen"));
    }

    public void printHeatingZones() {
        for (Map.Entry<String, HeatingZone> entry : heatingZones.entrySet()) {
            String zoneName = entry.getKey();
            HeatingZone heatingZone = entry.getValue();

            System.out.println("Heating Zone: " + zoneName);
            System.out.println("Rooms:");
            for (Room room : heatingZone.getRooms()) {
                System.out.println("- " + room.getName());
            }
            System.out.println();
        }
    }



}
