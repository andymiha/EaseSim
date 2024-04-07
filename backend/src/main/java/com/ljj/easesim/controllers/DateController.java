package com.ljj.easesim.controllers;


import com.ljj.easesim.SmartHomeSecurity;
import org.springframework.web.bind.annotation.*;

import com.ljj.easesim.SmartHomeSimulator;
import com.ljj.easesim.services.HVAC;
import com.ljj.easesim.SmartHomeHeating;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/time")
public class DateController {

    private SmartHomeSimulator shs;

    private SmartHomeHeating shh;

    private SmartHomeSecurity shp;

    private HVAC hvac;

    private LocalDate currentDate;
    private int accelerationFactor;
    private LocalTime currentTime;
    private boolean isClockStart;
    private ScheduledExecutorService executorService;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public DateController() {
        this.shs = SmartHomeSimulator.getInstance();
        this.shh = SmartHomeHeating.getInstance();
        this.shp = SmartHomeSecurity.getInstance();
        this.hvac = HVAC.getInstance();
        this.accelerationFactor = 1;
        this.currentDate = LocalDate.of(2022, 3, 1); // Start date on March 1st, 2022
        this.currentTime = LocalTime.of(0, 0, 0); // Start time at midnight
        this.isClockStart = true;
    }

    public void startClock() {
        executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            LocalTime previousTime = currentTime; // Make a copy of the current time

            currentTime = currentTime.plusSeconds(accelerationFactor); // Increment time by acceleration factor
            trackChangeTemp(accelerationFactor);

            boolean isHourChanged = previousTime.getHour() != currentTime.getHour();

            if (isHourChanged || isClockStart) {
                System.out.println("Hour changed: " + previousTime.getHour() + " -> " + currentTime.getHour());
                double temp = shs.getTemperatureFromCSV(getCurrentDate(), getCurrentTime());
                shs.setOutsideTemp(temp);
            }

            if (previousTime.getHour() > currentTime.getHour()) {
                currentDate = currentDate.plusDays(1); // Increment date by 1 day
            }

            System.out.println(String.format(
                            "%-10s%s || Current Temperature from HVAC\n" +
                            "%-10s%s || New Temperature DataController\n" +
                            "%-10s%s || New Temperature SHS\n" +
                            "%-10s%s || New Temperature Garage Zone\n" +
                            "%-10s%s || Current Date\n" +
                            "%-10s%s || Current Time",
                    hvac.getCurrentTemperature(), " ",
                    shs.getTemperatureFromCSV(getCurrentDate(), getCurrentTime()), " ",
                    shs.getOutsideTemp(), " ",
                    shh.getHeatingZones().get("Garage").getCurrentZoneTemp(), " ",
                    currentDate, " ",
                    currentTime.format(TIME_FORMATTER), " "));


            // Print current date and time in new lines
            //shp.printIndoorTemps();
            System.out.println("\n" + "-".repeat(700));
            isClockStart = false; // Set isClockStart to false after the first iteration
        }, 0, 1, TimeUnit.SECONDS); // Start immediately and repeat every second
    }

    public int changeAccelerationFactor(int newFactor) {
        this.accelerationFactor = newFactor;
        return accelerationFactor;
    }

    //called in DateController
    //pass acceleration rate and do 0.1 * rate to speed up/slow down
    public void trackChangeTemp(int accelerationFactor) {
        double previousTemperature = hvac.getCurrentTemperature();

        if (hvac.isHvacRunning()) {
            if (hvac.getCurrentTemperature() < hvac.getDesiredTemperature()) {
                hvac.setCurrentTemperature(roundToDecimal(hvac.getCurrentTemperature() + 0.1 * accelerationFactor, 1));
                shh.notifyObservers();
            } else if (hvac.getCurrentTemperature() > hvac.getDesiredTemperature()) {
                hvac.setCurrentTemperature(roundToDecimal(hvac.getCurrentTemperature() - 0.1 * accelerationFactor, 1));
                shh.notifyObservers();
            }
        } else { // HVAC not running  -- temp changes according to outside
            double currentTemperature = hvac.getCurrentTemperature();
            double outsideTemperature = hvac.getOutsideTemperature();

            if (currentTemperature < outsideTemperature) {
                hvac.setCurrentTemperature(roundToDecimal(currentTemperature + 0.05 * accelerationFactor, 1));
            } else if (currentTemperature > outsideTemperature) {
                hvac.setCurrentTemperature(roundToDecimal(currentTemperature - 0.05 * accelerationFactor, 1));
            }
        }
        hvac.controlHVAC(); // Check if HVAC needs to start or stop after each time step

        double newTemperature = hvac.getCurrentTemperature();
        double temperatureChange = Math.abs(newTemperature - previousTemperature);

        if (temperatureChange >= 15 && shp.isAway()) {
            // Emit an event or perform some action when the temperature changes by 15C in 1 minute
            //shh.emitTemperatureChangeAlert(newTemperature, previousTemperature);
            shp.setAwayMode(false);
            shp.logEvent("Temperature increased by 15 degrees Celsius in 1 minute. Away mode turned off.");
            shp.sendNotificationToOwners("Temperature alert: 15 degrees Celsius increase in 1 minute.");
        }
    }

    public static double roundToDecimal(double value, int decimalPlaces) {
        if (decimalPlaces < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, decimalPlaces);
        value = value * factor;
        long temp = Math.round(value);
        return (double) temp / factor;
    }

    @GetMapping("/current/date")
    public String getCurrentDate() {
        return currentDate.toString();
    }

    @GetMapping("/current/clock")
    public String getCurrentTime() {
        return currentTime.format(TIME_FORMATTER);
    }

    @PostMapping("/acceleration")
    public String setAccelerationFactor(@RequestBody int newFactor) {
        stopClock(); // Stop the clock before changing the acceleration factor
        int newAccel = changeAccelerationFactor(newFactor);
        startClock(); // Start the clock again with the new acceleration factor
        return "New acceleration factor = " + newAccel;
    }

    @PostMapping("/stop")
    public String stopClock() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
            return "Clock stopped successfully!";
        } else {
            return "Clock is already stopped!";
        }
    }

    @PostMapping("/hvac/desiredTemperature")
    public String setDesiredTemperature(@RequestBody double desiredTemperature) {
        hvac.setDesiredTemperature(desiredTemperature);
        return "Desired temperature set to " + desiredTemperature;
    }

    @PostMapping("/hvac/assignHVACZone")
    public String assignHVACZone(@RequestBody String zoneName) {
        zoneName = zoneName.replace("\"", "");
        shh.assignHVACZone(zoneName);
        return "Assigned HVAC zone: " + zoneName;
    }
}
