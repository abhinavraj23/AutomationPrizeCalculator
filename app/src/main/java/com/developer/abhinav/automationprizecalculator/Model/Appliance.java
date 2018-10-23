package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;

public class Appliance implements Serializable {

    private String applianceName;

    public Appliance(){
    }

    public Appliance(String applianceName){
        this.applianceName = applianceName;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }
}
