package com.ljj.easesim.controllers;

import com.ljj.easesim.requestBodies.EditContextFormData;
import com.ljj.easesim.services.EditContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//Handles GET/POST requests for the EDIT FORM functionality
@RestController
public class EditContextFormController {

    @Autowired
    private EditContextService editContextService;

    //returns data to the frontend edit form component
    @GetMapping("/getData")
    public Map<String, List<String>> getData() {
        return editContextService.processGetData();
    }

    //handles data from the frontend form submission
    @PostMapping("/submitForm")
    public String submitForm(@RequestBody EditContextFormData editContextFormData) {
        // Process form data and perform required actions
        editContextService.processFormData(editContextFormData);
        return "Form submitted successfully!";
    }
}

