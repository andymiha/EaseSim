package com.ljj.easesim.layout;

import com.ljj.easesim.elements.*;
import com.ljj.easesim.abstractions.*;

import java.util.ArrayList;

public class Room {
    private final int id;
    private Command command;
    private String name;
    private ArrayList<HouseElement> elements;
    private ArrayList<User> users;

    private double currentTemp;

    private double desiredTemp;

    public Room(int id, String name, ArrayList<HouseElement> elements, ArrayList<User> users) {
        this.id = id;
        this.name = name;
        this.elements = elements;
        this.users = users;
    }


    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public double getDesiredTemp() {
        return desiredTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public void setDesiredTemp(double desiredTemp) {
        this.desiredTemp = desiredTemp;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public Command getCommand() {
        return this.command;
    }
    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

    public ArrayList<HouseElement> getElements() {
        return this.elements;
    }

    public void addElement(HouseElement element) {
        elements.add(element);
    }

    public void removeElement(HouseElement element) {
        elements.remove(element);
    }

    public ArrayList<Door> getDoors() {
        ArrayList<Door> doors = new ArrayList<>();
        for (HouseElement element : elements) {
            if (element instanceof Door) {
                doors.add((Door) element);
            }
        }
        return doors;
    }

    public ArrayList<Window> getWindows() {
        ArrayList<Window> windows = new ArrayList<>();
        for (HouseElement element : elements) {
            if (element instanceof Window) {
                windows.add((Window) element);
            }
        }
        return windows;
    }

    public ArrayList<Light> getLights() {
        ArrayList<Light> lights = new ArrayList<>();
        for (HouseElement element : elements) {
            if (element instanceof Light) {
                lights.add((Light) element);
            }
        }
        return lights;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", command=" + command +
                ", name='" + name + '\'' +
                ", elements=" + elements +
                ", users=" + users +
                '}';
    }
}
