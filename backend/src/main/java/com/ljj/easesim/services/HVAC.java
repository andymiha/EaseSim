package com.ljj.easesim.services;

import com.ljj.easesim.services.HeatingZone;

public class HVAC {
    private double outsideTemperature;
    private double currentTemperature;
    private double desiredTemperature;
    private boolean hvacRunning;
    private HeatingZone currentZone;

    private static final HVAC INSTANCE = new HVAC();

    public HVAC() {
        this.outsideTemperature = 10;
        this.currentTemperature = outsideTemperature; // Initial temperature inside set to outside temperature
        this.desiredTemperature = outsideTemperature; // Initially, desired temperature same as outside temperature
        this.hvacRunning = false; // HAVC initially not running
    }

    public static HVAC getInstance() {
        return INSTANCE;
    }

    public double getOutsideTemperature() {
        return outsideTemperature;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getDesiredTemperature() {
        return desiredTemperature;
    }

    public boolean isHvacRunning() {
        return hvacRunning;
    }

    public void setOutsideTemperature(double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
        controlHVAC();
    }

    public void setCurrentTemperature(double currentTemperature) {
        this.currentTemperature = currentTemperature;
        updateZoneTemp();
    }

    public void updateZoneTemp(){
        this.currentZone.setCurrentZoneTemp(currentTemperature);
    }

    public void setDesiredTemperature(double desiredTemperature) {
        this.desiredTemperature = desiredTemperature;
        controlHVAC(); // Check if HVAC needs to start or stop
    }

    public void setZone(HeatingZone heatingZone){
        this.currentZone = heatingZone;
        this.currentTemperature = heatingZone.getCurrentZoneTemp();
        this.desiredTemperature = heatingZone.getDesiredZoneTemp();
        controlHVAC();
    }

    public void setHvacRunning(boolean hvacRunning) {
        this.hvacRunning = hvacRunning;
    }

    public void controlHVAC() {
        if (!hvacRunning) {
            if (currentTemperature < desiredTemperature - 1 || currentTemperature > desiredTemperature + 1) {
                startHVAC();
            }
        } else {
            if (Math.abs(currentTemperature - desiredTemperature) <= 0.25) {
                stopHVAC();
            }
        }
    }

    public void startHVAC() {
        hvacRunning = true;
        System.out.println("HVAC started.");
    }

    public void stopHVAC() {
        hvacRunning = false;
        System.out.println("HVAC stopped.");
    }
}



