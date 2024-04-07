package com.ljj.easesim.services;

import com.ljj.easesim.SmartHomeSimulator;
import com.ljj.easesim.elements.Window;
import com.ljj.easesim.layout.Room;
import com.ljj.easesim.requestBodies.EditContextFormData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EditContextService {

    private static final SmartHomeSimulator shs = SmartHomeSimulator.getInstance();

    //GET process
    public List<Room> processGetData() {
        try {
            System.out.println("Fetching rooms from house layout...");
            List<Room> rooms = shs.getHouseLayout().getRooms();
            System.out.println("Rooms fetched successfully.");
            return rooms;
        } catch (Exception e) {
            // Log the exception and handle it appropriately
            System.err.println("Error while retrieving room data: " + e.getMessage());
            // You can throw a specific exception or handle it in other ways based on your application's logic
            throw new RuntimeException("Failed to retrieve room data. Please try again later.");
        }
    }



    //POST process
    public boolean processFormData(EditContextFormData editContextFormData) {
        System.out.println("Received form data:");
        System.out.println("Selected Room: " + editContextFormData.getSelectedRoom());

        // Retrieve the selected room based on the room ID
        Room selectedRoom = SmartHomeSimulator.getInstance().getHouseLayout().getRoom(editContextFormData.getSelectedRoom());

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
        return true;
    }

    //Helper methods for POST
    public int extractWindowNumber(String selectedWindow) {
        // Use regular expression to extract the integer part
        String windowNumberStr = selectedWindow.replaceAll("\\D+", "");
        // Parse the window number string to an integer
        return Integer.parseInt(windowNumberStr);
    }

    public boolean toggleWindowBlockState(Room selectedRoom, int windowNumber) {
        // Retrieve the list of windows in the selected room
        for (Window window : selectedRoom.getWindows()) {
            if (window.getId() == windowNumber) {
                // Toggle the BLOCK state of the window
                window.toggleBlocked();
                System.out.println("Window block state toggled. New state: " + window.getBlockedState());
                break;
            }
        }
        return true;
    }
}
