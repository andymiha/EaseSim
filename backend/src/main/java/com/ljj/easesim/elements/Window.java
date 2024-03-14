package com.ljj.easesim.elements;

import com.ljj.easesim.interfaces.HouseElement;

public class Window implements HouseElement {
    private int id;
    private boolean isOpen;
    private boolean isBlocked;

    public Window() {
        this.isBlocked = false; // By default, window is unblocked
    }

    @Override
    public void toggle() {
        if (!isBlocked) {
            isOpen = !isOpen;
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    public boolean getState() {
        return isOpen;
    }

    public boolean getBlockedState() {
        return isBlocked;
    }

    public void setBlocked(boolean blockStatus) {
        this.isBlocked = blockStatus;
    }
}
