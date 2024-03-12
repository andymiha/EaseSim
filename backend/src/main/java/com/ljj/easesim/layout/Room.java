package com.ljj.easesim.layout;

import com.ljj.easesim.elements.Door;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.User;
import com.ljj.easesim.interfaces.Command;
import java.util.ArrayList;

public class Room {
    private final int id;
    private Command command;
    private String name;
    private ArrayList<HouseElement> elements;
    private ArrayList<User> users;

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

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
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
}
