package com.ljj.easesim.builders;

import com.ljj.easesim.layout.Room;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.User;

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

        // added so that it can return an empty list instead of null if there are no elements
        if (elements == null) {
            elements = new ArrayList<>();
        }
        if (users == null) {
            users = new ArrayList<>();
        }
            return new Room(id, name, elements, users);
        }

}
