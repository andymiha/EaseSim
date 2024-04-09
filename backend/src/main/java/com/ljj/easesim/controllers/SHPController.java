package com.ljj.easesim.controllers;

import com.ljj.easesim.SmartHomeSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shp")
public class SHPController {
    private final SmartHomeSecurity shp = SmartHomeSecurity.getInstance();

    @GetMapping("/get-away-mode")
    public boolean getAwayMode() {
        return shp.isAway();
    }

    @PostMapping("/set-away-mode")
    public String setAwayMode(@RequestBody boolean isAway) {
        shp.setAwayMode(isAway);
        return "Away mode set to: " + isAway;
    }

    @GetMapping("/get-police-timer")
    public int getPoliceTimer() {
        return shp.getPoliceTimer();
    }

    @PostMapping("/set-police-timer")
    public String setPoliceTimer(@RequestBody int policeTimer) {
        shp.setPoliceTimer(policeTimer);
        return "Police timer set to: " + policeTimer;
    }

}
