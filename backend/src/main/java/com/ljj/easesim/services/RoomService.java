package com.ljj.easesim.services;

import com.ljj.easesim.FormData;
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

    public HouseLayout houseLayout;

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
        Room selectedRoom = houseLayout.getRoom(formData.getSelectedRoom());

        if (selectedRoom != null) {
            System.out.println("Found corresponding Room object for selected room.");
            // Proceed with processing the selected inhabitant and window
            String selectedInhabitant = formData.getSelectedInhabitant();
            if (selectedInhabitant != null && !selectedInhabitant.isEmpty()) {
                processSelectedInhabitant(selectedRoom, formData.getSelectedInhabitant());
            } else {
                System.out.println("No inhabitant selected.");
            }

            if (formData.getSelectedWindow() != null && !formData.getSelectedWindow().isEmpty()) {
                int windowNumber = extractWindowNumber(formData.getSelectedWindow());
                processSelectedWindow(selectedRoom, windowNumber);
            } else {
                System.out.println("No window selected.");
            }
        } else {
            System.out.println("Corresponding Room object not found for selected room.");
        }

        System.out.println("Is Window Blocked: " + formData.isWindowBlocked());

        // You can add your business logic here, such as storing the data in a database.
    }

    private void processSelectedInhabitant(Room selectedRoom, String selectedInhabitant) {
        System.out.println("Selected Inhabitant: " + selectedInhabitant);
        // Add logic to update corresponding room with selected inhabitant
        // do this later when the UserProfiles are done
    }

    private void processSelectedWindow(Room selectedRoom, int selectedWindow) {
        System.out.println("Selected Window: " + selectedWindow);
        List<HouseElement> elements = selectedRoom.getElements();
        for (HouseElement element : elements) {
            if (element instanceof Window && element.getId() == selectedWindow) {
                ((Window) element).toggle();
                System.out.println("Window state toggled.");
                break; // No need to continue searching for the window
            }
        }
    }


    public int extractWindowNumber(String selectedWindow) {
        // Use regular expression to extract the integer part
        String windowNumberStr = selectedWindow.replaceAll("\\D+", "");
        // Parse the window number string to an integer
        return Integer.parseInt(windowNumberStr);
    }


}
