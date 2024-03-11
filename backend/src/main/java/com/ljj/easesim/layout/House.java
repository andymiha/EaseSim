package com.ljj.easesim.layout;

import com.ljj.easesim.builders.RoomBuilder;
import com.ljj.easesim.enums.RoomType;
import com.ljj.easesim.interfaces.HouseElement;
import com.ljj.easesim.interfaces.User;
import java.util.ArrayList;

public class House {
    // ID Counter necessary for incrementing room id's when creating rooms.
    private int roomIdCounter = 0;
    private ArrayList<Room> rooms;

    public House() {
        // Example House Layout Constructor
        rooms = new ArrayList<>();
        Room room = createLivingRoom();
        System.out.println(room.getId());
    }

    public Room createKitchen() {
        Room kitchen = new RoomBuilder()
                .id(++roomIdCounter)
                .roomType(RoomType.KITCHEN)
                .elements(new ArrayList<HouseElement>())
                .users(new ArrayList<User>())
                .build();
        this.rooms.add(kitchen);
        return kitchen;
    }

    public Room createLivingRoom() {
        Room livingRoom = new RoomBuilder()
                .id(++roomIdCounter)
                .roomType(RoomType.LIVING_ROOM)
                .elements(new ArrayList<HouseElement>())
                .users(new ArrayList<User>())
                .build();
        this.rooms.add(livingRoom);
        return livingRoom;
    }

    public Room createBedroom() {
        Room bedRoom = new RoomBuilder()
                .id(++roomIdCounter)
                .roomType(RoomType.BEDROOM)
                .elements(new ArrayList<HouseElement>())
                .users(new ArrayList<User>())
                .build();
        this.rooms.add(bedRoom);
        return bedRoom;
    }

    public Room createBathroom() {
        Room bathroom = new RoomBuilder()
                .id(++roomIdCounter)
                .roomType(RoomType.BATHROOM)
                .elements(new ArrayList<HouseElement>())
                .users(new ArrayList<User>())
                .build();
        this.rooms.add(bathroom);
        return bathroom;
    }
}
