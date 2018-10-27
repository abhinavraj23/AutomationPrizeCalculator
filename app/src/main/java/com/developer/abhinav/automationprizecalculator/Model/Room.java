package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {

    private String roomName;
    private List<Switch> switches;
    private String ID;

    public Room() {
    }

    public Room(String roomName, String ID) {
        this.roomName = roomName;
        this.ID = ID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<Switch> getSwitches() {
        return switches;
    }

    public String  getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setSwitches(List<Switch> switches) {
        this.switches = switches;
    }

}
