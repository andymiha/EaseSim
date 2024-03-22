package com.ljj.easesim.elements;

import com.ljj.easesim.abstractions.HouseElement;

public class Light extends HouseElement {
    private boolean isAuto;

    public boolean getIsAutoState() {
        return isAuto;
    }

    public void setIsAutoState(boolean isAuto) {
        this.isAuto = isAuto;
    }
}
