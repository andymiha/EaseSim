package com.ljj.easesim.users;

import com.ljj.easesim.abstractions.User;
import com.ljj.easesim.layout.Room;

public class Parent implements User {
    private int id;
    private String name;

    public Parent(int id, String name) {
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
        return true;
    }
}
