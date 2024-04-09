package com.ljj.easesim.controllers;

import com.ljj.easesim.SmartHomeHeating;
import com.ljj.easesim.services.HVAC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.ljj.easesim.SmartHomeSimulator;
import com.ljj.easesim.services.HVAC;
import com.ljj.easesim.SmartHomeHeating;
import com.ljj.easesim.SmartHomeSecurity;
import org.springframework.util.Assert;

class DateControllerTest {
    private DateController dateController;
    private SmartHomeSimulator smartHomeSimulator;
    private SmartHomeHeating smartHomeHeating;
    private SmartHomeSecurity smartHomeSecurity;
    private HVAC hvac;

    @BeforeEach
    public void setUp() {
        dateController = new DateController();
        smartHomeSimulator = SmartHomeSimulator.getInstance();
        smartHomeHeating = SmartHomeHeating.getInstance();
        smartHomeSecurity = SmartHomeSecurity.getInstance();
        smartHomeSimulator.registerObserver(smartHomeHeating);
        smartHomeHeating.registerObserver(smartHomeSecurity);
        hvac = HVAC.getInstance();
        dateController.assignHVACZone("Garage");

    }

    //think about this one
//    @Test
//    void testStartClock() {
//    }

    @Test
    void testSetAccelerationFactor() {
        int newFactor = 2;
        assertEquals(newFactor, dateController.changeAccelerationFactor(newFactor));
    }

    @Test
    void testTrackChangeTempHVACRunningBelow() {
        hvac.setCurrentTemperature(10);
        hvac.setDesiredTemperature(20);
        dateController.trackChangeTemp(1);
        assertEquals(10.1,hvac.getCurrentTemperature());
    }

    @Test
    void testTrackChangeTempHVACRunningAbove() {
        hvac.setCurrentTemperature(25);
        hvac.setDesiredTemperature(20);
        dateController.trackChangeTemp(1);
        assertEquals(24.9,hvac.getCurrentTemperature());
    }

    @Test
    void testTrackChangeTempHVACNotRunningAbove() {
        hvac.setCurrentTemperature(25);
        hvac.setDesiredTemperature(25);
        hvac.setOutsideTemperature(10);
        dateController.trackChangeTemp(1);
        assertEquals(24.95,hvac.getCurrentTemperature());
    }

    @Test
    void testTrackChangeTempHVACNotRunningBelow() {
        hvac.setCurrentTemperature(25);
        hvac.setDesiredTemperature(25);
        hvac.setOutsideTemperature(30);
        dateController.trackChangeTemp(1);
        assertEquals(25.05,hvac.getCurrentTemperature());
    }


    @Test
    void testGetCurrentDate() {
        dateController.startClock();
        assertEquals("2022-03-01",dateController.getCurrentDate());
    }

    @Test
    void testGetCurrentTime() {
        dateController.startClock();
        assertEquals("00:00:00",dateController.getCurrentTime());
    }

    @Test
    void testStopClockSuccess() {
        dateController.startClock();
        assertEquals("Clock stopped successfully!",dateController.stopClock());
    }
    @Test
    void testStopClockFail() {
        assertEquals("Clock is already stopped!",dateController.stopClock());
    }

    @Test
    void testSetDesiredTemperature() {
        double desiredTemperature = 25.0;
        assertEquals("Desired temperature set to " + desiredTemperature,
                dateController.setDesiredTemperature(desiredTemperature));
    }

    @Test
    void testAssignHVACZone() {
        String zoneName = "Living Room";
        assertEquals("Assigned HVAC zone: " + zoneName,
                dateController.assignHVACZone(zoneName));
    }
}