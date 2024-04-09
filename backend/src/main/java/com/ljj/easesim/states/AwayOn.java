package com.ljj.easesim.states;

import com.ljj.easesim.SmartHomeSecurity;
import com.ljj.easesim.abstractions.State;

public class AwayOn extends State{
    public AwayOn(SmartHomeSecurity shp) {
        super(shp);
    }

    @Override
    public boolean isAway(){
        return true;
    }

    @Override
    public void setAwayMode(boolean isAway) {
        if (!isAway) {
            shp.changeState(shp.getAwayOff());
            shp.logEvent("away mode is turned off");
        }
    }
}
