package com.ljj.easesim.abstractions;

import com.ljj.easesim.SmartHomeSimulator;
import com.ljj.easesim.layout.*;

import java.util.Map;
import java.util.HashMap;

public abstract class HouseElement {
    protected String roomName;
    protected int id;
    protected boolean isOpen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getState() {
        return isOpen;
    }

    public void setState(boolean open) {
        isOpen = open;
    }

    public void toggle() {
        isOpen = !isOpen;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Room getRoom(int roomId) {
        return SmartHomeSimulator.getInstance().getHouseLayout().getRoom(roomName);
    }
}
