package com.ljj.easesim.requestBodies;

import java.util.ArrayList;

public class UserRequest {
    private String name;

    private String userType;


    public String getName() {
        return name;
    }

    public String getUserType() {
        return userType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
