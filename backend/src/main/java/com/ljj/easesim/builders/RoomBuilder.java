package com.ljj.easesim.builders;

import com.ljj.easesim.layout.Room;
import com.ljj.easesim.abstractions.HouseElement;
import com.ljj.easesim.abstractions.User;

import java.util.ArrayList;

public class RoomBuilder {
    private int id;
    private String name;
    private ArrayList<HouseElement> elements;
    private ArrayList<User> users;

    public RoomBuilder id(int id) {
        this.id = id;
        return this;
    }

    public RoomBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RoomBuilder elements(ArrayList<HouseElement> elements) {
        this.elements = elements;
        return this;
    }

    public RoomBuilder users(ArrayList<User> users) {
        this.users = users;
        return this;
    }

    public Room build() {
        return new Room(id, name, elements, users);
    }
}
