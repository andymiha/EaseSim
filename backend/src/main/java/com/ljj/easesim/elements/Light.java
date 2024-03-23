package com.ljj.easesim.elements;

import com.ljj.easesim.abstractions.HouseElement;

public class Light extends HouseElement {
    private boolean isAuto;

    public Light() {
        this.isAuto = false;
    }

    public boolean getIsAutoState() {
        return isAuto;
    }

    public void setIsAutoState(boolean isAuto) {
        this.isAuto = isAuto;
    }

    @Override
    public String toString() {
        return "Light{" +
                "id=" + id +
                ", room="  +
                ", isAuto=" + isAuto +
                ", isOpen=" + isOpen +
                '}';
    }
}
