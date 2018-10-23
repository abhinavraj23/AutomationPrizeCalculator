package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;

public class Floor implements Serializable {

    private String floorName;

    public Floor(){
    }

    public Floor(String floorName){
        this.floorName =floorName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }
}
