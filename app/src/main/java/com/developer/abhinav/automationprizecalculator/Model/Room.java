package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;

public class Room implements Serializable {

    private String roomName;

    public Room() {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
