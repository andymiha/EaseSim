package com.ljj.easesim.elements;

import com.ljj.easesim.abstractions.HouseElement;

public class Light extends HouseElement {
    public Light() {
        this.isAuto = false;
    }

    private boolean isAuto;

    public boolean getIsAutoState() {
        return isAuto;
    }

    public void setIsAutoState(boolean isAuto) {
        this.isAuto = isAuto;
    }

//    @Override
//    public String toString() {
//        return "Light{" +
//                "isAuto=" + isAuto +
//                ", room=" + room +
//                ", id=" + id +
//                ", isOpen=" + isOpen +
//                '}';
//    }

    @Override
    public String toString() {
        return "Light id : " + id;
    }
}
