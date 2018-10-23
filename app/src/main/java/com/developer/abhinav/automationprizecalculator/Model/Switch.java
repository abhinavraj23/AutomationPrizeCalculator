package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Switch implements Serializable {

    private String switchBoard;
    private List<Appliance> appliances;

    public  Switch(){
    }

    public Switch(String switchBoard){
        this.switchBoard = switchBoard;
    }

    public String getSwitchBoard() {
        return switchBoard;
    }

    public void setSwitchBoard(String switchBoard) {
        this.switchBoard = switchBoard;
    }

    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(List<Appliance> appliances) {
        this.appliances = appliances;
    }

}
