package com.ljj.easesim.controllers;

import com.ljj.easesim.FormData;
import com.ljj.easesim.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//Handles GET/POST requests for the EDIT FORM functionality
@RestController
public class FormController {

    @Autowired
    private RoomService roomService;

    //returns data to the frontend edit form component
    @GetMapping("/getData")
    public Map<String, List<String>> getData() {
        return roomService.processGetData();
    }

    //handles data from the frontend form submission
    @PostMapping("/submitForm")
    public String submitForm(@RequestBody FormData formData) {
        // Process form data and perform required actions
        roomService.processFormData(formData);
        return "Form submitted successfully!";
    }

}

