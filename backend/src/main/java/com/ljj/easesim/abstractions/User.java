package com.ljj.easesim.abstractions;

import com.ljj.easesim.layout.Room;

public interface User {
    int getId();
    String getName();
    boolean hasRoomAccess(Room room);
}
