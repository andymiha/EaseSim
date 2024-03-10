package com.ljj.easesim.elements;

import com.ljj.easesim.interfaces.HouseElement;

public class Light implements HouseElement {
    private int id;
    private boolean switchedOn;

    public void toggleLight() {
        switchedOn = !switchedOn;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
