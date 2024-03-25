package com.ljj.easesim.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/clock")
public class ClockController {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // Custom start time (replace with your desired start time)
    private static final long customStartTimeMillis = parseCustomStartTime("09:00:00");

    @GetMapping("/current")
    public String getCurrentTime() {
        return dateFormat.format(new Date());
    }

    // Method to calculate the initial delay based on custom start time
    private static long parseCustomStartTime(String customStartTime) {
        try {
            Date startTime = dateFormat.parse(customStartTime);
            return startTime.getTime() - System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
/*
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@EnableScheduling
public class DateTimeSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateTimeSimulationApplication.class, args);
    }
}

@Component
class DateTimeSimulator {

    private LocalDateTime currentDateTime = LocalDateTime.now();

    @Scheduled(fixedRate = 1000) // Update every second
    public void updateDateTime() {
        currentDateTime = currentDateTime.plusSeconds(1);
        System.out.println("Current Date and Time: " + formatDateTime(currentDateTime));
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
 */
