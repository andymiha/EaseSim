package com.ljj.easesim.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SHSController {

    public SHSController() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File("db.json");
            Map<String, Object> dbData = objectMapper.readValue(file, new TypeReference<>() {});
            System.out.println(dbData.get("profiles"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private final SHSService shsService;
//
//    public SHSController(SHSService shsService) {
//        this.shsService = shsService;
//    }
//
//    public String getData(String userId) {
//        String data = shsService.getData(userId);
//        // Convert data to JSON or any desired format
//        return data;
//    }
//
//    public void saveData(String data) {
//        shsService.saveData(data);
//    }
//
//    public void updateData(String data) {
//        shsService.updateData(data);
//    }
//
//    public void deleteData(String dataId) {
//        shsService.deleteData(dataId);
//    }
}
