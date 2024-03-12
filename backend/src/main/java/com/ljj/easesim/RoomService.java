package com.ljj.easesim;

import org.springframework.stereotype.Service;

import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.User;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.elements.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    private HouseLayout houseLayout;

    public RoomService() {
        this.houseLayout = new HouseLayout();
    }

    public Map<String, List<String>> processGetData() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("inhabitants", getInhabitants());
        data.put("rooms", getRooms());
        data.put("windows", getWindows());
        return data;
    }

    //HardCoded for Now, need to complete Users and create and assign various users to rooms
    public List<String> getInhabitants() {
        List<String> inhabitants = new ArrayList<>();

        // Hardcoded inhabitants for demonstration
        inhabitants.add("Sarah");
        inhabitants.add("Mehdi");
        inhabitants.add("Sarah");
        inhabitants.add("Andy");
        inhabitants.add("Jon");
        inhabitants.add("Noura");

        return inhabitants;
    }

    public List<String> getRooms() {
        List<String> rooms = new ArrayList<>();
        for (Room room : houseLayout.getRooms()) {
            rooms.add(room.getName());
        }
        return rooms;
    }

    public List<String> getWindows() {
        List<String> windows = new ArrayList<>();
        for (Room room : houseLayout.getRooms()) {
            List<HouseElement> elements = room.getElements();
            for (HouseElement element : elements) {
                if (element instanceof Window) {
                    windows.add("Window " + element.getId());
                }
            }
        }
        return windows;
    }

    public void processFormData(FormData formData) {
        // Perform necessary actions with the form data
        System.out.println("Received form data:");
        System.out.println("Selected Room: " + formData.getSelectedRoom());
        System.out.println("Selected Inhabitant: " + formData.getSelectedInhabitant());
        System.out.println("Selected Window: " + formData.getSelectedWindow());
        System.out.println("Is Window Blocked: " + formData.isWindowBlocked());

        // You can add your business logic here, such as storing the data in a database.
    }
}
