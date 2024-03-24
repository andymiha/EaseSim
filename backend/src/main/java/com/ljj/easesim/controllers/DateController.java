package com.ljj.easesim.controllers;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/time")
public class DateController {

    private LocalDate currentDate;
    private int accelerationFactor;
    private LocalTime currentTime;
    private ScheduledExecutorService executorService;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public DateController() {
        this.accelerationFactor = 1;
        this.currentDate = LocalDate.of(2022, 3, 1); // Start date on March 1st, 2022
        this.currentTime = LocalTime.of(0, 0, 0); // Start time at midnight
        startClock();
    }

    private void startClock() {
        executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(() -> {
            if (currentTime.getHour() == 23 && currentTime.getMinute() == 59 && currentTime.getSecond() == 59) {
                currentDate = currentDate.plusDays(1); // Increment date by 1 day
            }
            currentTime = currentTime.plusSeconds(accelerationFactor); // Increment time by acceleration factor
            printCurrentDateTimeNewLine(); // Print current date and time in new lines
        }, 0, 1, TimeUnit.SECONDS); // Start immediately and repeat every second
    }

    public int changeAccelerationFactor(int newFactor) {
        this.accelerationFactor = newFactor;
        return accelerationFactor;
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
}
