package com.ljj.easesim.layout;

import com.ljj.easesim.enums.RoomType;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.User;
import com.ljj.easesim.interfaces.Command;
import java.util.ArrayList;

public class Room {
    private final int id;
    private Command command;
    private RoomType roomType;
    private ArrayList<HouseElement> elements;
    private ArrayList<User> users;

    public Room(int id, RoomType roomType, ArrayList<HouseElement> elements, ArrayList<User> users) {
        this.id = id;
        this.elements = elements;
        this.users = users;
    }

    public int getId() {
        return this.id;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
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
}
