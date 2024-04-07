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
public class SmartHomeSimulator implements TemperatureObservable {
    private static SmartHomeSimulator INSTANCE = null;
    private ArrayList<User> users;
    private User loggedInUser;
    private final HouseLayout houseLayout;
    private File outsideTempsFile;
    private double outsideTemp;
    private List<TemperatureObserver> temperatureObservers;

    public SmartHomeSimulator() {
        users = new ArrayList<>();
        houseLayout = HouseLayout.getInstance();
        outsideTemp = 69;
        mapUsersFromJson("db.json");
        outsideTempsFile = new File("OutdoorTemp.csv");
        temperatureObservers = new ArrayList<>();
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

    @Override
    public void registerObserver(TemperatureObserver observer) {
        temperatureObservers.add(observer);
    }

    @Override
    public void removeObserver(TemperatureObserver observer) {
        temperatureObservers.remove(observer);
    }

    public List<TemperatureObserver> getTemperatureObservers() {
        return temperatureObservers;
    }

    @Override
    public void notifyObservers() {
                                                // Implement this method to get indoor temperature
        double outdoorTemp = getOutsideTemp(); // Implement this method to get outdoor temperature

        for (TemperatureObserver observer : temperatureObservers) {
            observer.updateTemperature("", outdoorTemp);
        }
    }

    //METHODS

    private void mapUsersFromJson(String fileName) {
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
                    //System.out.println("csvDate: '" + csvDate + "', date: '" + date + "'");
                    //System.out.println("csvTime: '" + csvTime.substring(0,2) + "', time: '" + time.substring(0,2) + "'"); //substring times to match hours (discard minutes & seconds)

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
}
