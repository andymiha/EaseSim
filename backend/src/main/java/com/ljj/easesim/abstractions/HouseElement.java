package com.ljj.easesim.abstractions;

import com.ljj.easesim.layout.Room;

public abstract class HouseElement {
    protected Room room = null;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
