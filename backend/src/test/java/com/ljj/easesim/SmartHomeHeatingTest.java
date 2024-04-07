package com.ljj.easesim;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.ljj.easesim.services.HVAC;



class SmartHomeHeatingTest {
    private SmartHomeHeating smartHomeHeating;
    private HVAC hvac;

    @BeforeEach
    public void setUp() {
        smartHomeHeating = SmartHomeHeating.getInstance();
        hvac = HVAC.getInstance();
    }

    @Test
    void testToggleActive() {
        //when the program is run, SHH is automatically toggled
        assertTrue(smartHomeHeating.isActive());
        smartHomeHeating.toggleActive();
        assertFalse(smartHomeHeating.isActive());
    }

    @Test
    void testUpdateTemperature() {
        smartHomeHeating.toggleActive();
        double outdoorTemperature = 15;
        smartHomeHeating.updateTemperature("", outdoorTemperature);
        assertEquals(15,hvac.getOutsideTemperature());
    }

    @Test
    void testCreateHeatingZone() {
        assertTrue(smartHomeHeating.createHeatingZone("Zone3"));
    }

    @Test
    void testDeleteHeatingZone() {
        assertTrue(smartHomeHeating.deleteHeatingZone("Zone3"));
    }

    @Test
    void testGetRoomsInHeatingZone() {
    }

    @Test
    void testAssignHVACZone() {
    }

    @Test
    void removeRoomFromHeatingZone() {
    }

    @Test
    void printHeatingZones() {
    }
}