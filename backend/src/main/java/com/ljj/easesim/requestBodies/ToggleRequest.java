package com.ljj.easesim.requestBodies;
public class ToggleRequest {
    private int id;
    private int userId;

    // Getters and setters
    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

