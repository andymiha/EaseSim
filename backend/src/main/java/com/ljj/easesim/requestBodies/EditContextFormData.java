package com.ljj.easesim.requestBodies;

import com.ljj.easesim.layout.Room;
import com.ljj.easesim.elements.*;

public class EditContextFormData {
    private String selectedRoom;
    private String selectedInhabitant;
    private String selectedWindow;
    private boolean isWindowBlocked;

    // Default constructor
    public EditContextFormData() {
    }

    // Getters and setters
    public String getSelectedRoom() {
        return selectedRoom;
    }

    public String getSelectedInhabitant() {
        return selectedInhabitant;
    }

    public String getSelectedWindow() {
        return selectedWindow;
    }

    public boolean isWindowBlocked() {
        return isWindowBlocked;
    }

}
