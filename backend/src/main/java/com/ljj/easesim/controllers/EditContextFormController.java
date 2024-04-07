package com.ljj.easesim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.ljj.easesim.requestBodies.EditContextFormData;
import com.ljj.easesim.services.EditContextService;


import com.ljj.easesim.layout.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Handles GET/POST requests for the EDIT FORM functionality
@RestController
public class EditContextFormController {

    @Autowired
    private EditContextService editContextService;

    //returns data to the frontend edit form component
    @GetMapping("/getData")
    public List<Room> getData() {
        System.out.println("Fetching data...");
        List<Room> rooms = editContextService.processGetData();
        System.out.println("Data fetched successfully.");
        return rooms;
    }

    //handles data from the frontend form submission
    @PostMapping("/submitForm")
    public String submitForm(@RequestBody EditContextFormData editContextFormData) {
        editContextService.processFormData(editContextFormData);
        return "Form submitted successfully!";
    }
}

