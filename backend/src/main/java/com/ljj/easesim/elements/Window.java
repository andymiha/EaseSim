package com.ljj.easesim.elements;

import com.ljj.easesim.abstractions.HouseElement;

public class Window extends HouseElement {

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

    public boolean getBlockedState() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void toggleBlocked() {
        isBlocked = !isBlocked;

    }

    @Override
    public String toString() {
        return "Window{" +
                "id=" + id +
                ", room=" + roomName +
                ", isBlocked=" + isBlocked +
                ", isOpen=" + isOpen +
                '}';
    }
}
