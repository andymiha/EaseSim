package com.ljj.easesim;

import com.ljj.easesim.abstractions.*;
import com.ljj.easesim.layout.*;
import com.ljj.easesim.elements.*;
import com.ljj.easesim.states.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SmartHomeSecurity implements TemperatureObserver, AwayModeObservable {
    private static final SmartHomeSecurity INSTANCE = new SmartHomeSecurity();
    private HouseLayout house = SmartHomeSimulator.getInstance().getHouseLayout();
    private SmartHomeCore shc = SmartHomeCore.getInstance();;
    private Map<String, Double> indoorTemps;
    private Map<String, Long> lastTemperatureUpdate;
    private int policeTimer;
    //private boolean isAway;
    private State AwayOn;
    private State AwayOff;
    private State currentState;
    private AwayModeObserver awayObserver;

    public SmartHomeSecurity() {
        this.indoorTemps = new HashMap<>();
        this.lastTemperatureUpdate = new HashMap<>();
        this.AwayOn = new AwayOn(this);
        this.AwayOff = new AwayOff(this);
        this.currentState = AwayOff;
    }

    public static SmartHomeSecurity getInstance() {
        return INSTANCE;
    }

    public boolean isAway() {
        return currentState.isAway();
    }

    public State getAwayOn() {
        return AwayOn;
    }

    public State getAwayOff() {
        return AwayOff;
    }

    public State getCurrentState() {
        return currentState;
    }

    public int getPoliceTimer() {
        return policeTimer;
    }

    public void setPoliceTimer(int policeTimer) {
        this.policeTimer = policeTimer;
        logEvent("Police Timer is now this many minutes: " + policeTimer);
    }

    public void changeState(State state){
        this.currentState = state;
    }

    @Override
    public void registerAwayModeObserver(AwayModeObserver observer) {
        this.awayObserver = observer;
    }

    @Override
    public void removeAwayModeObserver(AwayModeObserver observer) {
        this.awayObserver = null;
    }

    public AwayModeObserver getAwayObserver() {
        return awayObserver;
    }

    @Override
    public void notifyAwayModeObservers() {
        awayObserver.updateAwayMode(currentState.isAway());
    }

    public void setAwayMode(boolean isAway) {
        currentState.setAwayMode(isAway);
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

    public boolean isHouseEmpty() {
        for (Room room : house.getRooms()) {
            if (!room.getUsers().isEmpty()) {
                sendNotificationToOwners("Motion detected: calling police in " + policeTimer + " minutes");
                return false; // If any room has users, return false
            }
        }
        return true; // If no rooms have users, return true
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