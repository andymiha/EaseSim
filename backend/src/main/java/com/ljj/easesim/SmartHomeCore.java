package com.ljj.easesim;

import com.ljj.easesim.commands.*;
import com.ljj.easesim.elements.Door;
import com.ljj.easesim.elements.Light;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.abstractions.HouseElement;
import com.ljj.easesim.abstractions.User;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;

public class SmartHomeCore {
    private final HouseLayout house = SmartHomeSimulator.getInstance().getHouseLayout();
    private static final SmartHomeCore INSTANCE = new SmartHomeCore();

    // Log all actions (save in log file) and display in console. (Observer)

    public static SmartHomeCore getInstance() {
        return INSTANCE;
    }


    public Room findElementRoom(HouseElement element) {
        System.out.println("TOGGLE " + element.toString());
//        System.out.println(element.getId());
        Room foundRoom = null;
        System.out.println("Size = " + house.getRooms().size());

        for (Room room : house.getRooms()) {
            for (HouseElement roomElement : room.getElements()) {
                if (roomElement.getId() == element.getId()) {
                    System.out.println("FOUND ELEMENT");
                    foundRoom = room;
                }
            }
        }
        return foundRoom;
    }

    // Toggle element, no permissions check
    public Light toggle(Light light) {
        Room elementRoom = findElementRoom(light);
        // Toggle the element, return state
        elementRoom.setCommand(new ToggleLightCommand(light));
        elementRoom.executeCommand();
        return light;
    }

    public Light toggle(Light light, User user) {
        Room elementRoom = findElementRoom(light);
        if (user.hasRoomAccess(elementRoom)) {
            // Toggle the element, return state
            elementRoom.setCommand(new ToggleLightCommand(light));
            elementRoom.executeCommand();
        }
        return light;
    }

    public Window toggle(Window window) {
        Room elementRoom = findElementRoom(window);
        // Toggle the element, return state
        elementRoom.setCommand(new ToggleWindowCommand(window));
        elementRoom.executeCommand();
        return window;
    }

    public Window toggle(Window window, User user) {
        Room elementRoom = findElementRoom(window);
        if (user.hasRoomAccess(elementRoom)) {
            // Toggle the element, return state
            elementRoom.setCommand(new ToggleWindowCommand(window));
            elementRoom.executeCommand();
        }
        return window;
    }

    public Door toggle(Door door) {
        Room elementRoom = findElementRoom(door);
        // Toggle the element, return state
        elementRoom.setCommand(new ToggleDoorCommand(door));
        elementRoom.executeCommand();
        return door;
    }

    public Door toggle(Door door, User user) {
        Room elementRoom = findElementRoom(door);
        if (user.hasRoomAccess(elementRoom)) {
            // Toggle the element, return state
            elementRoom.setCommand(new ToggleDoorCommand(door));
            elementRoom.executeCommand();
        }
        return door;
    }

    public Light toggleIsAuto(Light light) {
        Room elementRoom = findElementRoom(light);
        // Toggle the element, return state
        elementRoom.setCommand(new ToggleIsAutoLightCommand(light));
        elementRoom.executeCommand();
        return light;
    }

    public Light toggleIsAuto(Light light, User user) {
        Room elementRoom = findElementRoom(light);
        if (user.hasRoomAccess(elementRoom)) {
            // Toggle the element, return state
            elementRoom.setCommand(new ToggleIsAutoLightCommand(light));
            elementRoom.executeCommand();
        }
        return light;
    }

    public Door toggleIsAuto(Door door, User user) {
        Room elementRoom = findElementRoom(door);
        if (user.hasRoomAccess(elementRoom)) {
            // Toggle the element, return state
            elementRoom.setCommand(new ToggleIsAutoDoorCommand(door));
            elementRoom.executeCommand();
        }
        return door;
    }
}
