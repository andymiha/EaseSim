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
        dateController.setAccelerationFactor(1);
        dateController.setDesiredTemperature(20);
        dateController.assignHVACZone("Garage");

    }

    @Test
    void registerObserver() {
        shs.registerObserver(shh);
        assertEquals(1, shs.getTemperatureObservers().size());
    }

    @Test
    void removeObserver() {
        shs.removeObserver(shh);
        assertEquals(0, shs.getTemperatureObservers().size());
    }

//    @Test
//    void notifyObservers() {
//        shs.notifyObservers();
//        assertEquals(-14.5,hvac.getOutsideTemperature());
//
//    }

//    @Test
//    void getTemperatureFromCSV() {
//        assertEquals(-14.5, shs.getTemperatureFromCSV(dateController.getCurrentDate(), dateController.getCurrentTime()));
//    }
}