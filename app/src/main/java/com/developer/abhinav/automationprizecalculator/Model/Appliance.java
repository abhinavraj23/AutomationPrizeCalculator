package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;

public class Appliance implements Serializable {

    private String applianceName;
    private  String ID;

    public Appliance(){
    }

    public Appliance(String applianceName,String ID){
        this.applianceName = applianceName;
        this.ID = ID;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public String  getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }
}
