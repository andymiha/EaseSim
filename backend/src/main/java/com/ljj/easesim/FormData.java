package com.ljj.easesim;


public class FormData {
    private String selectedRoom;
    private String selectedInhabitant;
    private String selectedWindow;
    private boolean isWindowBlocked;

    // Default constructor
    public FormData() {
    }

    // Getters and setters
    public String getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(String selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public String getSelectedInhabitant() {
        return selectedInhabitant;
    }

    public void setSelectedInhabitant(String selectedInhabitant) {
        this.selectedInhabitant = selectedInhabitant;
    }

    public String getSelectedWindow() {
        return selectedWindow;
    }

    public void setSelectedWindow(String selectedWindow) {
        this.selectedWindow = selectedWindow;
    }

    public boolean isWindowBlocked() {
        return isWindowBlocked;
    }

    public void setWindowBlocked(boolean windowBlocked) {
        isWindowBlocked = windowBlocked;
    }
}
