package com.ljj.easesim.controllers;

import com.ljj.easesim.SmartHomeSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shp")
public class SHPController {
    private final SmartHomeSecurity shs = SmartHomeSecurity.getInstance();

    @PostMapping("/set-away-mode")
    public String setAwayMode(@RequestBody boolean isAway) {
        shs.setAwayMode(isAway);
        return "Away mode set to: " + isAway;
    }

    @PostMapping("/set-police-timer")
    public String setPoliceTimer(@RequestBody int policeTimer) {
        shs.setPoliceTimer(policeTimer);
        return "Police timer set to: " + policeTimer;
    }

}
