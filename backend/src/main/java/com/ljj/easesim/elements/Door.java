package com.ljj.easesim.elements;

import com.ljj.easesim.abstractions.HouseElement;
import java.util.AbstractMap;

public class Door extends HouseElement {
    private boolean isAuto;
    private boolean isBlocked;

    //the main room door is inherited through the abstract definition
    //
    private AbstractMap.SimpleEntry<String, String> roomConnection;


    public Door() {
        this.isBlocked = false; // By default, window is unblocked
    }

    @Override
    public void toggle() {
        if (!isBlocked) {
            isOpen = !isOpen;
        }
    }

    public boolean getBlockedState() {
        return isBlocked;
    }

    /* not used for now
    *  public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    } */

    public void toggleBlocked() {
        isBlocked = !isBlocked;

    }
    public boolean getIsAutoState() {
        return isAuto;
    }
    public void setIsAutoState(boolean auto) {
        this.isAuto = auto;
    }
    public AbstractMap.SimpleEntry<String, String> getRoomConnection() {
        return roomConnection;
    }
    /* not used for now
    public void setRoomConnection(AbstractMap.SimpleEntry<String, String> roomConnection) {
        this.roomConnection = roomConnection;
    }
*/
    @Override
    public String toString() {
        return "Door{" +
                "id=" + id +
                ", roomName=" + roomName +
                ", roomConnection=" + roomConnection +
                ", isBlocked=" + isBlocked +
                ", isAuto=" + isAuto +
                ", isOpen=" + isOpen +
                '}';
    }


}
