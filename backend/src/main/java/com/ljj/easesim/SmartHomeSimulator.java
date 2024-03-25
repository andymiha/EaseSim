package com.ljj.easesim;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ljj.easesim.abstractions.*;
import com.ljj.easesim.layout.*;
import com.ljj.easesim.users.*;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

// Allow upload CSV for temperature info.
public class SmartHomeSimulator {
    private static SmartHomeSimulator INSTANCE = null;
    private ArrayList<User> users;
    private User loggedInUser;
    private final HouseLayout houseLayout;
    private File outsideTempsFile;
    private double outsideTemp;



    public SmartHomeSimulator() {
        users = new ArrayList<>();
        houseLayout = HouseLayout.getInstance();
        mapUsersFromJson("db.json");
        outsideTempsFile = new File("OutdoorTemp.csv");
    }

    public static SmartHomeSimulator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SmartHomeSimulator(); // Create new instance if null
        }
        return INSTANCE;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users); // Return a copy of the users list
    }

    public HouseLayout getHouseLayout() {
        return houseLayout;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null; // User not found
    }

    public File getOutsideTempsFile() {
        return outsideTempsFile;
    }

    public void setOutsideTempsFile(File outsideTempsFile) {
        this.outsideTempsFile = outsideTempsFile;
    }

    public double getOutsideTemp() {
        return outsideTemp;
    }

    public void setOutsideTemp(double outsideTemp) {
        this.outsideTemp = outsideTemp;
    }

    //METHODS

    public void mapUsersFromJson(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(fileName);
            Map<String, Object> dbData = objectMapper.readValue(file, new TypeReference<>() {});
            Map<String, Map<String, Object>> profiles = (Map<String, Map<String, Object>>) dbData.get("profiles");

            for (Map.Entry<String, Map<String, Object>> entry : profiles.entrySet()) {
                String userKey = entry.getKey();
                Map<String, Object> details = entry.getValue();
                addUser(parseInt(userKey), details.get("userType").toString(), details.get("name").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getTemperatureFromCSV(String date, String time) {
        try (Scanner scanner = new Scanner(outsideTempsFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 3) {
                    String csvDate = parts[0].replaceAll("\\p{C}", "");; // Trim whitespace characters and remove 5G influence
                    String csvTime = parts[1].replaceAll("\\p{C}", "");; // Trim whitespace characters and remove 5G influence


                    double temperature = Double.parseDouble(parts[2]);

                    // Debugging outputs to check values
                    System.out.println("csvDate: '" + csvDate + "', date: '" + date + "'");
                    System.out.println("csvTime: '" + csvTime.substring(0,2) + "', time: '" + time.substring(0,2) + "'"); //substring times to match hours (discard minutes & seconds)

                    if (csvDate.equals(date) && csvTime.substring(0,2).equals(time.substring(0,2))) {
                        return temperature;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Return a default temperature value if the specified date and time are not found
        return -273.15; // Absolute zero as a default value
    }


    public User addUser(int id, String userType, String name) {
        // Generating a unique ID for the new user
        int newId = (id >= 0) ? id : users.stream().mapToInt(User::getId).max().orElse(-1) + 1;

        User newUser = null;
        switch (userType) {
            case "Child":
                newUser = new Child(newId, name);
                break;
            case "Guest":
                newUser = new Guest(newId, name);
                break;
            case "Parent":
                newUser = new Parent(newId, name);
                break;
            case "Stranger":
                newUser = new Stranger(newId, name);
                break;
        }
        if (newUser != null) {
            users.add(newUser);
        }

        return newUser;
    }

    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }





//    //----------------------------------------------------------------------------------------------------------------
//    //SHH testing
//    //To be rethought/refactored -- just for testing purposes
//
//    // God API Method
//
//    public void testSHHFunctions() {
//        // Display Separator Line
//        System.out.println("\n" + "-".repeat(700));
//        System.out.println("-".repeat(700));
//        System.out.println("TESTING SHH FUNCTIONS\n");
//
//        System.out.println("\nTesting heating zone creation...\n");
//
//        shh.createHeatingZone("Living Room");
//        System.out.println("Living Room heating zone created successfully.");
//        System.out.println("Living Room heating zone is empty: " + shh.getRoomsInHeatingZone("Living Room").isEmpty());
//
//        shh.createHeatingZone("Kitchen");
//        System.out.println("Kitchen heating zone created successfully.");
//        System.out.println("Kitchen heating zone is empty: " + shh.getRoomsInHeatingZone("Kitchen").isEmpty());
//
//        SmartHomeHeating shh = SmartHomeHeating.getInstance();
//
//        // Test adding rooms to heating zones
//        Room room1 = houseLayout.getRooms().get(0);
//        Room room2 = houseLayout.getRooms().get(1);
//
//        shh.addRoomToHeatingZone("Living Room", room1);
//        shh.addRoomToHeatingZone("Kitchen", room2);
//
//        // Verify that rooms are added to the correct heating zones
//        System.out.println("\nTesting adding rooms to heating zones...\n");
//
//        System.out.println("Room added to Living Room heating zone: " + shh.getRoomsInHeatingZone("Living Room").contains(room1));
//        System.out.println("Room added to Kitchen heating zone: " + shh.getRoomsInHeatingZone("Kitchen").contains(room2));
//
//        System.out.println("Living Room heating zone contents: " + shh.getRoomsInHeatingZone("Living Room"));
//        System.out.println("Kitchen heating zone contents: " + shh.getRoomsInHeatingZone("Kitchen"));
//    }

}
