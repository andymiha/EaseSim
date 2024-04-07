package com.ljj.easesim;

import com.ljj.easesim.abstractions.*;
import com.ljj.easesim.layout.*;
import com.ljj.easesim.users.*;
import com.ljj.easesim.elements.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SmartHomeSecurity implements TemperatureObserver, AwayModeObservable {
    private final HouseLayout house = SmartHomeSimulator.getInstance().getHouseLayout();
    private static final SmartHomeSecurity INSTANCE = new SmartHomeSecurity();;
    private static SmartHomeCore shc;
    private Map<String, Double> indoorTemps;
    private Map<String, Long> lastTemperatureUpdate;
    private boolean isAway;
    private AwayModeObserver awayObserver;

    public SmartHomeSecurity() {
        shc = SmartHomeCore.getInstance();
        this.indoorTemps = new HashMap<>();
        this.lastTemperatureUpdate = new HashMap<>();
        this.isAway = false;
    }

    public static SmartHomeSecurity getInstance() {
        return INSTANCE;
    }

    public boolean isAway() {
        return isAway;
    }

    @Override
    public void registerAwayModeObserver(AwayModeObserver observer) {
        this.awayObserver = observer;
    }

    @Override
    public void removeAwayModeObserver(AwayModeObserver observer) {
        this.awayObserver = null;
    }

    public AwayModeObserver getAwayObserver(){ return awayObserver; }
    @Override
    public void notifyAwayModeObservers() {
        awayObserver.updateAwayMode(isAway);
    }

    public void setAwayMode(boolean isAway) {
        this.isAway = isAway;
        notifyAwayModeObservers();
        logEvent("away mode is: " + isAway);
        if(isAway) {
            lockdown();
        }
    }

    public boolean lockdown(){
        Map<HouseElement, String> doors = house.getHouseDoors();
        Map<HouseElement, String> windows = house.getHouseWindows();

        // Iterate over doors
        for (Map.Entry<HouseElement, String> entry : doors.entrySet()) {
            HouseElement door = entry.getKey();
            String doorInfo = entry.getValue();

            if(door.getState()){
                shc.toggle((Door) door);
            }
            if(door.getState()){
                System.out.println("A door in " + door.getRoomName() + " is blocked. Couldn't close it...");
            }
        }

        // Iterate over windows
        for (Map.Entry<HouseElement, String> entry : windows.entrySet()) {
            HouseElement window = entry.getKey();
            String windowInfo = entry.getValue();

            if(window.getState()){
                shc.toggle((Window) window);
            }
            if(window.getState()){
                System.out.println("A window in " + window.getRoomName() + " is blocked. Couldn't close it...");
            }
        }

        ArrayList<Room> rooms = house.getRooms();
        for(Room room : rooms){
            System.out.println("Toggled lockdown: " + room.toString());
        }

        return true;
    }

    @Override
    public void updateTemperature(String zoneName, double zoneTemp) {
        indoorTemps.put(zoneName, zoneTemp); // Update the indoor temperature for the specified zone

        if (zoneTemp >= 135.0 && isAway()) {
            setAwayMode(false);
            logEvent("Temperature reached 135 degrees Celsius. Away mode turned off.");
            sendNotificationToOwners("Temperature alert: 135 degrees Celsius reached in " + zoneName);
        }
    }


    //Module logging methods
    public void logEvent(String message) {
        System.out.println("Event logged: " + message);
    }

    public void sendNotificationToOwners(String message) {
        System.out.println("Notification sent to owners: " + message);
    }

    public void printIndoorTemps() {
        for (Map.Entry<String, Double> entry : indoorTemps.entrySet()) {
            String zoneName = entry.getKey();
            double temp = entry.getValue();
            System.out.println(zoneName + ": " + temp);
        }
    }

}