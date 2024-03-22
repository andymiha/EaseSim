package com.ljj.easesim.services;

import com.ljj.easesim.requestBodies.EditContextFormData;
import org.springframework.stereotype.Service;

import com.ljj.easesim.layout.HouseLayout;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.elements.Window;


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
    public Map<String, List<Room>> processGetData() {
        Map<String, List<Room>> data = new HashMap<>();
        data.put("rooms", getRooms());
        return data;
    }

    public List<Room> getRooms() {
        return houseLayout.getRooms();
    }


//    public void processFormData(EditContextFormData editContextFormData) {
//        System.out.println("Received form data:");
//        System.out.println("Selected Room: " + editContextFormData.getSelectedRoom());
//        System.out.println("Selected Inhabitant: " + editContextFormData.getSelectedInhabitant());
//        System.out.println("Selected Window: " + editContextFormData.getSelectedWindow());
//        System.out.println("Is Window Blocked: " + editContextFormData.isWindowBlocked());
//
//        // Implement logic to process the form data here
//    }

    public void processFormData(EditContextFormData editContextFormData) {
        System.out.println("Received form data:");
        System.out.println("Selected Room: " + editContextFormData.getSelectedRoom());

        // Retrieve the selected room based on the room ID
        Room selectedRoom = houseLayout.getRoom(editContextFormData.getSelectedRoom());

        if (selectedRoom != null) {
            System.out.println("Found corresponding Room object for selected room.");

            // Proceed with processing the selected inhabitant and window
            if (!editContextFormData.getSelectedInhabitant().isEmpty()) {
                System.out.println("Selected Inhabitant: " + editContextFormData.getSelectedInhabitant());
                // Add logic to update corresponding room with selected inhabitant
                // For example, you can add the inhabitant to the room's list of users
            } else {
                System.out.println("No inhabitant selected.");
            }

            if (!editContextFormData.getSelectedWindow().isEmpty()) {
                System.out.println("Selected Window: " + editContextFormData.getSelectedWindow());
                // Extract the window number from the selected window string
                int windowNumber = extractWindowNumber(editContextFormData.getSelectedWindow());
                // Toggle the BLOCK state of the selected window
                toggleWindowBlockState(selectedRoom, windowNumber);
            } else {
                System.out.println("No window selected.");
            }
        } else {
            System.out.println("Corresponding Room object not found for selected room.");
        }
    }

    private int extractWindowNumber(String selectedWindow) {
        // Use regular expression to extract the integer part
        String windowNumberStr = selectedWindow.replaceAll("\\D+", "");
        // Parse the window number string to an integer
        return Integer.parseInt(windowNumberStr);
    }

    private void toggleWindowBlockState(Room selectedRoom, int windowNumber) {
        // Retrieve the list of windows in the selected room
        for (Window window : selectedRoom.getWindows()) {
            if (window.getId() == windowNumber) {
                // Toggle the BLOCK state of the window
                window.toggleBlocked();
                System.out.println("Window block state toggled. New state: " + window.getBlockedState());
                break;
            }
        }
    }


//    //POST Processing
//    public void processFormData(EditContextFormData editContextFormData) {
//        // Perform necessary actions with the form data
//        System.out.println("Received form data:");
//
//        System.out.println("Selected Room: " + editContextFormData.getSelectedRoom());
//        Room selectedRoom = houseLayout.getRoom(editContextFormData.getSelectedRoom());
//
//        if (selectedRoom != null) {
//            System.out.println("Found corresponding Room object for selected room.");
//            // Proceed with processing the selected inhabitant and window
//            if (!editContextFormData.getSelectedInhabitant().isEmpty()) {
//                processSelectedInhabitant(selectedRoom, editContextFormData.getSelectedInhabitant());
//            } else {
//                System.out.println("No inhabitant selected.");
//            }
//
//            if (!editContextFormData.getSelectedWindow().isEmpty()) {
//                int windowNumber = extractWindowNumber(editContextFormData.getSelectedWindow());
//                boolean isWindowBlocked = editContextFormData.isWindowBlocked();
//                processSelectedWindow(selectedRoom, windowNumber, isWindowBlocked);
//            } else {
//                System.out.println("No window selected.");
//            }
//        } else {
//            System.out.println("Corresponding Room object not found for selected room.");
//        }
//
//        // You can add your business logic here, such as storing the data in a database.
//    }
//
//    private void processSelectedInhabitant(Room selectedRoom, String selectedInhabitant) {
//        System.out.println("Selected Inhabitant: " + selectedInhabitant);
//        // Add logic to update corresponding room with selected inhabitant
//        // do this later when the UserProfiles are done
//    }
//
//    private void processSelectedWindow(Room selectedRoom, int selectedWindow, boolean isWindowBlocked) {
//        System.out.println("Selected Window: " + selectedWindow);
//
//        for (HouseElement element : selectedRoom.getElements()) {
//            if (element instanceof Window && element.getId() == selectedWindow) {
//                Window window = (Window) element;
//
//                // Create command to toggle block state of the window
//                Command toggleBlockCommand = new ToggleBlockWindowCommand(window);
//
//                // Set the command in the room
//                selectedRoom.setCommand(toggleBlockCommand);
//
//                // Execute the command in the room
//                selectedRoom.executeCommand();
//
//                System.out.println("Window block state toggled.");
//
//                System.out.println("Final Window Block State: " + window.getBlockedState());
//                break; // No need to continue searching for the window
//            }
//        }
//    }
//
//    private int extractWindowNumber(String selectedWindow) {
//        // Use regular expression to extract the integer part
//        String windowNumberStr = selectedWindow.replaceAll("\\D+", "");
//        // Parse the window number string to an integer
//        return Integer.parseInt(windowNumberStr);
//    }


}
