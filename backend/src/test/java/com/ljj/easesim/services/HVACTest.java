package com.ljj.easesim.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HVACTest {

    private HVAC hvac;

    @BeforeEach
    public void setUp() {
        hvac = HVAC.getInstance();
    }

    @Test
    void testSetZone() {
        HeatingZone heatingZone = new HeatingZone("TestZone");

        // Initially, HVAC is not running
        assertFalse(hvac.isHvacRunning());

        // Setting zone should change current and desired temperatures and start HVAC
        hvac.setZone(heatingZone);

        assertEquals(10, hvac.getCurrentTemperature());
        assertEquals(20, hvac.getDesiredTemperature());

        assertTrue(hvac.isHvacRunning());
    }

    @Test
    void testControlHVAC() {
        assertFalse(hvac.isHvacRunning());

        hvac.setCurrentTemperature(10);
        hvac.setDesiredTemperature(20);

        assertTrue(hvac.isHvacRunning());
    }


}