package com.ljj.easesim;

import com.ljj.easesim.layout.HouseLayout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeSecurityTest {

    private SmartHomeSecurity smartHomeSecurity;
    private HouseLayout houseLayout;

    @BeforeEach
    void setUp(){
        smartHomeSecurity = SmartHomeSecurity.getInstance();

    }

    @Test
    public void testIsAwayInitiallyFalse() {
        assertFalse(smartHomeSecurity.isAway());
    }

    @Test
    void registerAwayModeObserver() {
    }

    @Test
    void removeAwayModeObserver() {
    }

    @Test
    void notifyAwayModeObservers() {
    }

    @Test
    void setAwayMode() {
    }

    @Test
    void lockdown() {
    }

    @Test
    void updateTemperature() {
    }

    @Test
    void logEvent() {
    }

    @Test
    void sendNotificationToOwners() {
    }

    @Test
    void printIndoorTemps() {
    }
}