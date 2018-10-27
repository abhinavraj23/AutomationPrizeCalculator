package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Floor implements Serializable {

    private String floorName;
    private List<Room> rooms;
    private String ID;

    public Floor(){
    }

    public Floor(String floorName, String ID){
        this.floorName = floorName;
        this.ID = ID;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String  getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
