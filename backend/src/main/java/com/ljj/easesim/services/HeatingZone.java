package com.ljj.easesim.services;

import com.ljj.easesim.layout.Room;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeatingZone {
    private String zoneName;
    private double currentZoneTemp;
    private double desiredZoneTemp;
    private List<Room> rooms;

    public HeatingZone(String zoneName) {
        this.zoneName = zoneName;
        this.currentZoneTemp = 10;
        this.desiredZoneTemp = 20;
        this.rooms = new ArrayList<>();
    }

    public String getZoneName() {
        return zoneName;
    }

    public double getCurrentZoneTemp() {
        return currentZoneTemp;
    }

    public double getDesiredZoneTemp() {
        return desiredZoneTemp;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public void setCurrentZoneTemp(double zoneTemp) {
        this.currentZoneTemp = zoneTemp;
        for (Room room : rooms) {
            room.setCurrentTemp(zoneTemp);
        }
    }

    public void setDesiredZoneTemp(double desiredZoneTemp) {
        this.desiredZoneTemp = desiredZoneTemp;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }


}
