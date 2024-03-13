package com.ljj.easesim.services;

import com.ljj.easesim.requestBodies.EditContextFormData;
import org.springframework.stereotype.Service;

import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.Command;
import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.commands.ToggleBlockWindowCommand;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EditContextService {

    private HouseLayout houseLayout;

    public EditContextService() {
        this.houseLayout = houseLayout.getInstance();
    }

//GET process
    public Map<String, List<String>> processGetData() {
        Map<String, List<String>> data = new HashMap<>();
        data.put("inhabitants", getInhabitants());
        data.put("rooms", getRooms());
        data.put("windows", getWindows());
        data.put("windowStates", getWindowStates()); // Add window block states to the response
        return data;
    }

    public List<String> getRooms() {
        List<String> rooms = new ArrayList<>();
        for (Room room : houseLayout.getRooms()) {
            rooms.add(room.getName());
        }
        return rooms;
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

    private List<String> getWindowStates() {
        List<String> windowStates = new ArrayList<>();
        for (Room room : houseLayout.getRooms()) {
            List<HouseElement> elements = room.getElements();
            for (HouseElement element : elements) {
                if (element instanceof Window) {
                    windowStates.add("Window " + element.getId() + " isBlocked: " + ((Window) element).getBlockedState());
                }
            }
        }
        return windowStates;
    }


//POST Processing
    public void processFormData(EditContextFormData editContextFormData) {
        // Perform necessary actions with the form data
        System.out.println("Received form data:");

        System.out.println("Selected Room: " + editContextFormData.getSelectedRoom());
        Room selectedRoom = houseLayout.getRoom(editContextFormData.getSelectedRoom());

        if (selectedRoom != null) {
            System.out.println("Found corresponding Room object for selected room.");
            // Proceed with processing the selected inhabitant and window
            if (!editContextFormData.getSelectedInhabitant().isEmpty()) {
                processSelectedInhabitant(selectedRoom, editContextFormData.getSelectedInhabitant());
            } else {
                System.out.println("No inhabitant selected.");
            }

            if (!editContextFormData.getSelectedWindow().isEmpty()) {
                int windowNumber = extractWindowNumber(editContextFormData.getSelectedWindow());
                boolean isWindowBlocked = editContextFormData.isWindowBlocked();
                processSelectedWindow(selectedRoom, windowNumber, isWindowBlocked);
            } else {
                System.out.println("No window selected.");
            }
        } else {
            System.out.println("Corresponding Room object not found for selected room.");
        }

        // You can add your business logic here, such as storing the data in a database.
    }

    private void processSelectedInhabitant(Room selectedRoom, String selectedInhabitant) {
        System.out.println("Selected Inhabitant: " + selectedInhabitant);
        // Add logic to update corresponding room with selected inhabitant
        // do this later when the UserProfiles are done
    }

    private void processSelectedWindow(Room selectedRoom, int selectedWindow, boolean isWindowBlocked) {
        System.out.println("Selected Window: " + selectedWindow);

        for (HouseElement element : selectedRoom.getElements()) {
            if (element instanceof Window && element.getId() == selectedWindow) {
                Window window = (Window) element;

                // Update the window block state
                window.setBlocked(isWindowBlocked);

                // Create command to toggle block state of the window
                Command toggleBlockCommand = new ToggleBlockWindowCommand(window);

                // Set the command in the room
                selectedRoom.setCommand(toggleBlockCommand);

                // Execute the command in the room
                selectedRoom.executeCommand();

                System.out.println("Window block state toggled.");

                System.out.println("Final Window Block State: " + window.getBlockedState());
                break; // No need to continue searching for the window
            }
        }
    }

    private int extractWindowNumber(String selectedWindow) {
        // Use regular expression to extract the integer part
        String windowNumberStr = selectedWindow.replaceAll("\\D+", "");
        // Parse the window number string to an integer
        return Integer.parseInt(windowNumberStr);
    }


}
