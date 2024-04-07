package com.ljj.easesim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartHomeSecurityTest {

    void setUp(){
        smartHomeSecurity = SmartHomeSecurity.getInstance();
        houseLayoutMock = Mockito.mock(HouseLayout.class);
        smartHomeSecurity.setHouseLayout(houseLayoutMock);
    }

    @Test
    void getInstance() {
    }

    @Test
    void isAway() {
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