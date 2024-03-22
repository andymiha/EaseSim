package com.ljj.easesim.users;

import com.ljj.easesim.interfaces.User;
import com.ljj.easesim.layout.Room;

public class Stranger implements User {
    private int id;
    private String name;

    public Stranger(int id, String name) {
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
        return false;
    }
}
