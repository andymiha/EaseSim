package com.ljj.easesim.elements;

import com.ljj.easesim.interfaces.HouseElement;

public class Window implements HouseElement {
    private int id;
    private boolean isOpen;
    private boolean isToggleable;

    public Window() {
        this.isToggleable = true; // By default, window is toggleable
    }

    @Override
    public void toggle() {
        if (isToggleable) {
            isOpen = !isOpen;
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean getState() {
        return isOpen;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setToggleable(boolean toggleable) {
        this.isToggleable = toggleable;
    }
}
