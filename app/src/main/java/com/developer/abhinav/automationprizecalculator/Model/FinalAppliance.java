package com.developer.abhinav.automationprizecalculator.Model;

import java.io.Serializable;

public class FinalAppliance implements Serializable {

    private String applianceName;
    private String applianceID;
    private int prize;

    public FinalAppliance(){
    }

    public FinalAppliance(String applianceName,String applianceID,int prize){
        this.applianceID = applianceID;
        this.applianceName = applianceName;
        this.prize = prize;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public String getApplianceID() {
        return applianceID;
    }

    public void setApplianceID(String applianceID) {
        this.applianceID = applianceID;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
