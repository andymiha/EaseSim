package com.ljj.easesim.elements;

import com.ljj.easesim.interfaces.HouseElement;

public class Light implements HouseElement {
    private int id;
    private boolean switchedOn;
    private boolean isAuto;

    @Override
    public void toggle() {
        switchedOn = !switchedOn;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean getState() {
        return switchedOn;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsAutoState() {
        return switchedOn;
    }

    public void setIsAutoState(int id) {
        this.id = id;
    }
}
