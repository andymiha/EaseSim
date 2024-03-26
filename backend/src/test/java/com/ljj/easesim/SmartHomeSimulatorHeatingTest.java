package com.ljj.easesim;

import com.ljj.easesim.controllers.DateController;
import com.ljj.easesim.services.HVAC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class SmartHomeSimulatorHeatingTest {
    private SmartHomeSimulator shs;
    private SmartHomeHeating shh;
    private HVAC hvac;
    private DateController dateController;

    @BeforeEach
    public void setUp() {
        shs = SmartHomeSimulator.getInstance();
        shh = SmartHomeHeating.getInstance();
        hvac = HVAC.getInstance();
        dateController = new DateController();

    }

    @Test
    void registerObserver() {
        assertEquals(1, shs.getTemperatureObservers().size());
    }

    @Test
    void removeObserver() {

        shs.removeObserver(shh);
        assertEquals(0, shs.getTemperatureObservers().size());
    }

    @Test
    void notifyObservers() {
        dateController.startClock();
        hvac.setDesiredTemperature(10);
        shs.notifyObservers();
        assertEquals(-14.5,shs.getOutsideTemp());

    }

    @Test
    void getTemperatureFromCSV() {
    }
}