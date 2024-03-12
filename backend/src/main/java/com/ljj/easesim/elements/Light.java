package com.ljj.easesim.elements;

import com.ljj.easesim.interfaces.HouseElement;

public class Light implements HouseElement {
    private int id;
    private boolean switchedOn;

    @Override
    public void toggle() {
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
