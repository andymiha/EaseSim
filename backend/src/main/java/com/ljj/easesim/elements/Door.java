package com.ljj.easesim.elements;

import com.ljj.easesim.interfaces.HouseElement;
import java.util.AbstractMap;

public class Door implements HouseElement {
    private int id;
    private boolean isOpen;
    private AbstractMap.SimpleEntry<String, String> roomConnection;
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
