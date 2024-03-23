package com.ljj.easesim.users;

import com.ljj.easesim.interfaces.User;

public class Guest implements User {
    private int id;
    private String name;

    public Guest(int id, String name) {
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
}
