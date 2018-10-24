package com.developer.abhinav.automationprizecalculator.Model;

public class ApplianceList {

    String applianceName;
    Boolean isSelected = false;
    int type;

    public ApplianceList(){
    }

    public ApplianceList(String applianceName, int type){
        this.applianceName = applianceName;
        this.type = type;
    }

    public void setApplianceName(String applianceName) {
        applianceName = applianceName;
    }

    public String getApplianceName() {
        return applianceName;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
