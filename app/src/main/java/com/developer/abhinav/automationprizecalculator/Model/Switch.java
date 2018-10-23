package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;

public class Switch implements Serializable {

    private String switchBoard;

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
}
