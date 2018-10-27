package com.developer.abhinav.automationprizecalculator.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.developer.abhinav.automationprizecalculator.Adapter.FinalApplianceAdapter;
import com.developer.abhinav.automationprizecalculator.Model.Appliance;
import com.developer.abhinav.automationprizecalculator.Model.ApplianceList;
import com.developer.abhinav.automationprizecalculator.Model.FinalAppliance;
import com.developer.abhinav.automationprizecalculator.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ListActivity extends AppCompatActivity {

    SharedPreferences sharedPreferencesAppliances, sharedPreferencesSwitches, sharedPreferencesRoom, sharedPreferencesFloor;
    List<ApplianceList> applianceLists;
    List<Integer> allApplianceIndex;
    List<FinalAppliance> finalAppliances;
    FinalApplianceAdapter finalApplianceAdapter;
    TextView totalPrize;
    ListView finalList;
    String ID, tempID;
    int noOfFloors;
    double totalSum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        finalList = (ListView) findViewById(R.id.final_list);
        totalPrize = (TextView) findViewById(R.id.total_prize);
        applianceLists = new ArrayList<>();
        allApplianceIndex = new ArrayList<>();
        finalAppliances = new ArrayList<>();
        sharedPreferencesAppliances = getSharedPreferences("APPLIANCE", MODE_PRIVATE);
        sharedPreferencesFloor = getSharedPreferences("FLOOR", MODE_PRIVATE);
        sharedPreferencesRoom = getSharedPreferences("ROOM", MODE_PRIVATE);
        sharedPreferencesSwitches = getSharedPreferences("SWITCH", MODE_PRIVATE);

        noOfFloors = sharedPreferencesFloor.getInt("COUNTER", 4);

        applianceLists.add(new ApplianceList("Non DimableLight", 0));
        applianceLists.add(new ApplianceList("Dimable Light", 0));
        applianceLists.add(new ApplianceList("Fan", 0));
        applianceLists.add(new ApplianceList("AC", 1));
        applianceLists.add(new ApplianceList("TV", 1));
        applianceLists.add(new ApplianceList("Gyser", 1));
        applianceLists.add(new ApplianceList("Fridge", 1));
        applianceLists.add(new ApplianceList("Heater", 1));
        applianceLists.add(new ApplianceList("Oven", 1));
        applianceLists.add(new ApplianceList("PC", 0));
        applianceLists.add(new ApplianceList("Socket", 0));
        applianceLists.add(new ApplianceList("Lamps", 0));
        applianceLists.add(new ApplianceList("Air Freshner", 0));
        applianceLists.add(new ApplianceList("Coffee Maker", 0));
        applianceLists.add(new ApplianceList("Exhaust Fan", 0));

        new generarteList().execute();
        finalApplianceAdapter = new FinalApplianceAdapter(getApplicationContext(), finalAppliances);
        new setPrize().execute();
        finalList.setAdapter(finalApplianceAdapter);
    }

    private class generarteList extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < noOfFloors; i++) {
                for (int j = 0; j < sharedPreferencesRoom.getInt(String.valueOf(i + 1), 4); j++) {
                    for (int k = 0; k < sharedPreferencesSwitches.getInt(String.valueOf(i + 1) + String.valueOf(j + 1), 5); k++) {
                        ID = String.valueOf(i + 1) + String.valueOf(j + 1) + String.valueOf(k + 1);
                        String savedString = sharedPreferencesAppliances.getString(ID, String.valueOf(""));
                        StringTokenizer st = new StringTokenizer(savedString, ",");
                        while (st.hasMoreTokens()) {
                            allApplianceIndex.add(Integer.parseInt(st.nextToken()));
                        }

                        for (int z = 0; z < allApplianceIndex.size(); z++) {
                            tempID = ID + String.valueOf(z + 1) + String.valueOf(allApplianceIndex.get(z));
                            finalAppliances.add(new FinalAppliance(applianceLists.get(allApplianceIndex.get(z)).getApplianceName(), tempID, 0));
                        }
                        allApplianceIndex.clear();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    private class setPrize extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean[][] onceOnly = new Boolean[noOfFloors][];
            for (int i = 0; i < noOfFloors; i++) {
                onceOnly[i] = new Boolean[sharedPreferencesRoom.getInt(String.valueOf(i + 1), 4)];
                Arrays.fill(onceOnly[i], Boolean.FALSE);
            }

            for (int i = 0; i < finalAppliances.size(); i++) {
                FinalAppliance appliance = finalAppliances.get(i);
                String applianceID = appliance.getApplianceID();
                int index;
                if (applianceID.length() == 5)
                    index = Integer.parseInt(applianceID.substring(applianceID.length() - 1));
                else
                    index = Integer.parseInt(applianceID.substring(applianceID.length() - 2));
                if (applianceLists.get(index).getType() == 1) {
                    if ((index == 3 || index == 4) && !onceOnly[(Integer.parseInt(applianceID.substring(0, 1))) - 1][(Integer.parseInt(applianceID.substring(1, 2))) - 1]) {
                        appliance.setPrize(3498);
                        totalSum += 3498;
                        onceOnly[(Integer.parseInt(applianceID.substring(0, 1))) - 1][(Integer.parseInt(applianceID.substring(1, 2))) - 1] = true;
                    } else {
                        appliance.setPrize(1999);
                        totalSum += 1999;
                    }
                } else {
                    List<Integer> applianceIndex = new ArrayList<>();
                    String savedString = sharedPreferencesAppliances.getString(applianceID.substring(0, 3), String.valueOf(""));
                    StringTokenizer st = new StringTokenizer(savedString, ",");
                    while (st.hasMoreTokens()) {
                        applianceIndex.add(Integer.parseInt(st.nextToken()));
                    }
                    int noOfType0Appliances = applianceIndex.size();
                    for (int j = 0; j < applianceIndex.size(); j++) {
                        if (applianceLists.get(applianceIndex.get(j)).getType() == 1) {
                            noOfType0Appliances--;
                        }
                    }
                    int applianceCounter = Integer.parseInt(appliance.getApplianceID().substring(3, 4))-(applianceIndex.size()-noOfType0Appliances);
                    switch (noOfType0Appliances % 4) {
                        case 0:
                            appliance.setPrize(4999 / 4);
                            totalSum += (double) (4999) / (double) 4;
                            if (isDimmableLight(appliance)) {
                                appliance.setPrize(appliance.getPrize() + 500);
                                totalSum += 500;
                            }
                            break;
                        case 1:
                            if (applianceCounter == noOfType0Appliances) {
                                appliance.setPrize(1999);
                                totalSum += 1999;
                            } else {
                                appliance.setPrize(4999 / 4);
                                totalSum += (double) (4999) / (double) 4;
                            }
                            if (isDimmableLight(appliance)) {
                                appliance.setPrize(appliance.getPrize() + 500);
                                totalSum += 500;
                            }
                            break;
                        case 2:
                            if (applianceCounter == noOfType0Appliances || applianceCounter == noOfType0Appliances - 1) {
                                appliance.setPrize(3499 / 2);
                                totalSum += (double) (3499) / (double) 2;
                            } else {
                                appliance.setPrize(4999 / 4);
                                totalSum += (double) (4999) / (double) 4;
                            }
                            if (isDimmableLight(appliance)) {
                                appliance.setPrize(appliance.getPrize() + 500);
                                totalSum += 500;
                            }
                            break;
                        case 3:
                            if (applianceCounter == noOfType0Appliances || applianceCounter == noOfType0Appliances - 1 || applianceCounter == noOfType0Appliances - 2) {
                                appliance.setPrize(4999 / 3);
                                totalSum += (double) (4999) / (double) 3;
                            } else {
                                appliance.setPrize(4999 / 4);
                                totalSum += (double) (4999) / (double) 4;
                            }
                            if (isDimmableLight(appliance)) {
                                appliance.setPrize(appliance.getPrize() + 500);
                                totalSum += 500;
                            }
                            break;
                        default:
                    }

                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            totalPrize.setText("NET PRIZE : " + String.valueOf(totalSum));
            finalApplianceAdapter.notifyDataSetChanged();
        }
    }

    private boolean isDimmableLight(FinalAppliance appliance) {
        String applianceID = appliance.getApplianceID();
        int index;
        if (applianceID.length() == 5)
            index = Integer.parseInt(applianceID.substring(applianceID.length() - 1));
        else
            index = Integer.parseInt(applianceID.substring(applianceID.length() - 2));
        return (index == 1);
    }
}
