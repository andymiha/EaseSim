package com.ljj.easesim.elements;

import com.ljj.easesim.interfaces.HouseElement;

public class Window implements HouseElement {
    private int id;
    private boolean isOpen;

    @Override
    public void toggle() {
        isOpen = !isOpen;
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
