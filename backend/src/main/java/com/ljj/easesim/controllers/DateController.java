package com.ljj.easesim.controllers;


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

    private HVAC hvac;

    private LocalDate currentDate;
    private int accelerationFactor;
    private LocalTime currentTime;
    private ScheduledExecutorService executorService;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public DateController() {
        this.shs = SmartHomeSimulator.getInstance();
        this.shh = SmartHomeHeating.getInstance();
        this.hvac = HVAC.getInstance();
        this.accelerationFactor = 1;
        this.currentDate = LocalDate.of(2022, 3, 1); // Start date on March 1st, 2022
        this.currentTime = LocalTime.of(0, 0, 0); // Start time at midnight
    }

    public void startClock() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        boolean isClockStart = true;

        executorService.scheduleAtFixedRate(() -> {
            LocalTime previousTime = currentTime; // Make a copy of the current time

            currentTime = currentTime.plusSeconds(accelerationFactor);// Increment time by acceleration factor
            trackChangeTemp(accelerationFactor);

            boolean isHourChanged = previousTime.getHour() != currentTime.getHour();

            if (isHourChanged){
                isClockStart = false;

            }

            if (isHourChanged || isClockStart) {
                System.out.println("\tHour changed: " + previousTime.getHour() + " -> " + currentTime.getHour());
                //BUG HERE - probably inside of getTemp...
                double temp = shs.getTemperatureFromCSV(getCurrentDate(), getCurrentTime());
                shs.setOutsideTemp(temp);
                System.out.println("New Temperature DataController: " + temp);
                System.out.println("New Temperature SHS: " + shs.getOutsideTemp());
            }


            //change incrementation condition -- increment if previous hour > next hour
            if (previousTime.getHour() > currentTime.getHour()) {
                currentDate = currentDate.plusDays(1); // Increment date by 1 day
            }
            System.out.println("New Temperature DataController: " + shs.getTemperatureFromCSV(getCurrentDate(), getCurrentTime()));
            System.out.println("New Temperature SHS: " + shs.getOutsideTemp());

            printCurrentDateTimeNewLine(); // Print current date and time in new lines
        }, 0, 1, TimeUnit.SECONDS); // Start immediately and repeat every second
    }

    public int changeAccelerationFactor(int newFactor) {
        this.accelerationFactor = newFactor;
        return accelerationFactor;
    }

    //called in DateController
    //pass acceleration rate and do 0.1 * rate to speed up/slow down
    public void trackChangeTemp(int accelerationFactor) {
        if (hvac.isHvacRunning()) {
            if (hvac.getCurrentTemperature() < hvac.getDesiredTemperature()) {
                hvac.setCurrentTemperature(hvac.getCurrentTemperature() + 0.1*accelerationFactor);
            } else if (hvac.getCurrentTemperature() > hvac.getDesiredTemperature()) {
                hvac.setCurrentTemperature(hvac.getCurrentTemperature() - 0.1*accelerationFactor);
            }
        } else { // HVAC not running  -- temp changes according to outside
            double currentTemperature = hvac.getCurrentTemperature();
            double outsideTemperature = hvac.getOutsideTemperature();

            if (currentTemperature < outsideTemperature) {
                hvac.setCurrentTemperature(currentTemperature + 0.05*accelerationFactor);
            } else if (currentTemperature > outsideTemperature) {
                hvac.setCurrentTemperature(currentTemperature - 0.05*accelerationFactor);
            }
        }
        System.out.println("\nCurrent Temperature: " + hvac.getCurrentTemperature());
        hvac.controlHVAC(); // Check if HVAC needs to start or stop after each time step
    }

    public void printCurrentDateTimeNewLine() {
        Thread updaterThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("\rCurrent Date: %s | Current Time: %s",
                        currentDate, currentTime.format(TIME_FORMATTER));
            }
        });
        updaterThread.setDaemon(true); // Set the thread as a daemon thread to stop when the main thread stops
        updaterThread.start();
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
