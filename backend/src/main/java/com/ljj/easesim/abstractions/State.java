package com.ljj.easesim.abstractions;

import com.ljj.easesim.SmartHomeSecurity;

public abstract class State {
    protected SmartHomeSecurity shp;

    public State(SmartHomeSecurity shp) {
        this.shp = shp;
    }

    public abstract boolean isAway();

    public abstract void setAwayMode(boolean isAway);
}
