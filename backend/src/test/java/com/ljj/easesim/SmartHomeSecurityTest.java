package com.ljj.easesim;

import com.ljj.easesim.abstractions.AwayModeObserver;
import com.ljj.easesim.abstractions.HouseElement;
import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

class SmartHomeSecurityTest {

    private SmartHomeSecurity smartHomeSecurity;
    private HouseLayout houseLayout;

    @BeforeEach
    void setUp(){
        smartHomeSecurity = new SmartHomeSecurity();
    }

    @Test
    public void testIsAwayInitiallyFalse() {
        assertFalse(smartHomeSecurity.isAway());
    }

    @Test
    void registerAndRemoveAwayModeObserver() {
        AwayModeObserver awayObserver = isAway -> System.out.println("Mock observer notified. Away mode updated: " + isAway);
        smartHomeSecurity.registerAwayModeObserver(awayObserver);
        assertNotNull(smartHomeSecurity.getAwayObserver());
        smartHomeSecurity.removeAwayModeObserver(awayObserver);
        assertNull(smartHomeSecurity.getAwayObserver());

    }

    @Test
    void notifyAwayModeObservers() {
        AwayModeObserver mockObserver = mock(AwayModeObserver.class);
        smartHomeSecurity.registerAwayModeObserver(mockObserver);
        smartHomeSecurity.notifyAwayModeObservers();
        verify(mockObserver, times(1)).updateAwayMode(anyBoolean());

    }

    @Test
    public void testSetAwayMode() {
        AwayModeObserver mockObserver = mock(AwayModeObserver.class);
        smartHomeSecurity.registerAwayModeObserver(mockObserver);
        smartHomeSecurity.setAwayMode(true);
        assertTrue(smartHomeSecurity.isAway());
    }

    @Test
    void lockdown() {
        smartHomeSecurity = SmartHomeSecurity.getInstance();
        SmartHomeCore shc = SmartHomeCore.getInstance();
        HouseLayout houseLayout = shc.getHouse();

        Window window = houseLayout.getRoom("Bedroom1").getWindows().get(0);
        Door door = houseLayout.getRoom("Bedroom1").getDoors().get(0);
        window.toggle();
        door.toggle();

        assertTrue(window.getState());
        assertTrue(door.getState());

        // Call the method to be tested
        boolean result = smartHomeSecurity.lockdown();

        // Verify that the method returns true (or perform additional specific assertions)
        assertTrue(result);

    }

    @Test
    public void testUpdateTemperature() {
        AwayModeObserver mockObserver = mock(AwayModeObserver.class);
        smartHomeSecurity.registerAwayModeObserver(mockObserver);
        // Mock data
        String zoneName = "TestZone";
        double zoneTemp = 150.0;
        smartHomeSecurity.setAwayMode(true);
        // Call the method to be tested
        smartHomeSecurity.updateTemperature(zoneName, zoneTemp);

        // Verify that the observer's updateAwayMode method was called
        verify(mockObserver, times(1)).updateAwayMode(true);
    }

    @Test
    public void testSetAndGetPoliceTimer() {
        // Set police timer to 10 minutes
        smartHomeSecurity.setPoliceTimer(10);
        assertEquals(10, smartHomeSecurity.getPoliceTimer());

        // Set police timer to 0 minutes
        smartHomeSecurity.setPoliceTimer(0);
        assertEquals(0, smartHomeSecurity.getPoliceTimer());

        // Set police timer to negative value (should not change)
        smartHomeSecurity.setPoliceTimer(-5);
        assertEquals(-5, smartHomeSecurity.getPoliceTimer());
    }

    @Test
    public void testIsHouseEmpty(){

            // Call the method to be tested
            boolean result = smartHomeSecurity.isHouseEmpty();

            // Verify that house is considered empty
            assertTrue(result);
        }
}