package com.ljj.easesim.states;

import com.ljj.easesim.SmartHomeSecurity;
import com.ljj.easesim.abstractions.State;

public class AwayOff extends State{
    public AwayOff(SmartHomeSecurity shp) {
        super(shp);
    }

    @Override
    public boolean isAway(){
        return false;
    }

    @Override
    public void setAwayMode(boolean isAway) {
        if (isAway) {
            shp.changeState(shp.getAwayOn());
            shp.logEvent("away mode is turned on");
            shp.lockdown();
        }
    }
}
