package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Switch implements Serializable {

    private String switchBoard;
    private List<Appliance> appliances;
    private String ID;

    public  Switch(){
    }

    public Switch(String switchBoard,String ID){
        this.switchBoard = switchBoard;
        this.ID = ID;
    }

    public String getSwitchBoard() {
        return switchBoard;
    }

    public void setSwitchBoard(String switchBoard) {
        this.switchBoard = switchBoard;
    }

    public String  getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public List<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(List<Appliance> appliances) {
        this.appliances = appliances;
    }

}
