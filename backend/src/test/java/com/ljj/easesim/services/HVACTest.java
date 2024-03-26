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

        //hvac should be on when program starts as long as current temp (10) and desired temp (20) are distinct.
        assertTrue(hvac.isHvacRunning());

        // Setting zone should change current and desired temperatures and start HVAC
        hvac.setZone(heatingZone);

        assertEquals(10, hvac.getCurrentTemperature());
        assertEquals(20, hvac.getDesiredTemperature());

        assertTrue(hvac.isHvacRunning());
    }

    @Test
    void testControlHVAC() {
        //hvac should be on when program starts as long as current temp (10) and desired temp (20) are distinct.
        assertTrue(hvac.isHvacRunning());

        hvac.setCurrentTemperature(10);
        hvac.setDesiredTemperature(20);

        assertTrue(hvac.isHvacRunning());
    }


}