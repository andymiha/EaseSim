package com.ljj.easesim;

import com.ljj.easesim.FormData;
import com.ljj.easesim.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/submitForm")
    public String submitForm(@RequestBody FormData formData) {
        // Process form data and perform required actions
        roomService.processFormData(formData);
        return "Form submitted successfully!";
    }
}

