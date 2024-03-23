package com.ljj.easesim.users;

import com.ljj.easesim.abstractions.User;
import com.ljj.easesim.layout.Room;

public class Child implements User {
    private int id;
    private String name;

    public Child(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean hasRoomAccess(Room room) {
        if (room.getUsers().contains(this)) {
            return true;
        }
        return false;
    }
}
