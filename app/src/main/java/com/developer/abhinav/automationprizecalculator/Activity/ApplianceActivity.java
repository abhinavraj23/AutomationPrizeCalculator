package com.developer.abhinav.automationprizecalculator.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.developer.abhinav.automationprizecalculator.Adapter.CustomAdapter;
import com.developer.abhinav.automationprizecalculator.Adapter.FloorAdapter;
import com.developer.abhinav.automationprizecalculator.Adapter.ApplianceAdapter;
import com.developer.abhinav.automationprizecalculator.Model.ApplianceList;
import com.developer.abhinav.automationprizecalculator.Model.Floor;
import com.developer.abhinav.automationprizecalculator.Model.Switch;
import com.developer.abhinav.automationprizecalculator.Model.Appliance;
import com.developer.abhinav.automationprizecalculator.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class ApplianceActivity extends AppCompatActivity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    private int applianceCounter = 0;

    List<Appliance> appliances;
    List<Integer> allApplianceIndex;
    List<Integer> selectedIndex;
    List<TextView> views;
    private ApplianceAdapter adapter;
    private static Switch aSwitch;
    SharedPreferences sharedPreferences;
    List<ApplianceList> applianceLists;
    private static CustomAdapter customAdapter;
    ListView applicationListView;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);
        applianceLists = new ArrayList<>();
        allApplianceIndex = new ArrayList<>();
        selectedIndex = new ArrayList<>();
        views = new ArrayList<>();

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


        sharedPreferences = getSharedPreferences("APPLIANCE", MODE_PRIVATE);

        appliances = new ArrayList<>();
        aSwitch = (Switch) getIntent().getSerializableExtra("Switch");
        String savedString = sharedPreferences.getString(aSwitch.getID(), String.valueOf(""));
        StringTokenizer st = new StringTokenizer(savedString, ",");
        while (st.hasMoreTokens()) {
            allApplianceIndex.add(Integer.parseInt(st.nextToken()));
        }

        applianceCounter = allApplianceIndex.size();
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        loadPreviousData(allApplianceIndex);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                applianceCounter++;
                final Dialog dialog = new Dialog(ApplianceActivity.this);
                dialog.setContentView(R.layout.custom_dialog_box);
                applicationListView = (ListView) dialog.findViewById(R.id.listView);
                customAdapter = new CustomAdapter(dialog.getContext(), applianceLists);
                applicationListView.setAdapter(customAdapter);
                applicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ApplianceList applianceList = applianceLists.get(i);
                        TextView radioText = view.findViewById(R.id.radioText);
                        applianceList.setSelected(!applianceList.getSelected());
                        if (applianceList.getSelected()) {
                            allApplianceIndex.add(i);
                            selectedIndex.add(i);
                            views.add(radioText);
                            radioText.setBackgroundColor(Color.parseColor("#B4D5FE"));
                        } else {
                            radioText.setBackgroundColor(Color.TRANSPARENT);
                            views.remove(radioText);
                            allApplianceIndex.remove(Integer.valueOf(i));
                            selectedIndex.remove(Integer.valueOf(i));
                        }
                    }
                });
                dialog.show();

                Button okay = (Button) dialog.findViewById(R.id.positive_btn);
                okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < views.size(); i++)
                            views.get(i).setBackgroundColor(Color.TRANSPARENT);
                        addSelectedAppliances(selectedIndex);
                        StringBuilder str = new StringBuilder();
                        for (int i = 0; i < allApplianceIndex.size(); i++) {
                            str.append(allApplianceIndex.get(i)).append(",");
                        }
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(aSwitch.getID(), str.toString());
                        editor.commit();
                        views.clear();
                        selectedIndex.clear();
                        dialog.dismiss();
                    }
                });

                Button decline = (Button) dialog.findViewById(R.id.negative_btn);
                decline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                applianceCounter--;
                if (allApplianceIndex.size() > 0) {
                    allApplianceIndex.remove(allApplianceIndex.size() - 1);
                    StringBuilder str = new StringBuilder();
                    for (int i = 0; i < allApplianceIndex.size(); i++) {
                        str.append(allApplianceIndex.get(i)).append(",");
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(aSwitch.getID(), str.toString());
                    editor.commit();
                    new deleteAppliance().execute();
                }
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                startActivity(new Intent(getApplicationContext(),ListActivity.class));
            }
        });

        adapter = new ApplianceAdapter(this, appliances, new ApplianceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Appliance item) {

            }
        });

        int rows = this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;
        initiateViews(rows);

    }

    private void initiateViews(int rows) {
        RecyclerView listView = findViewById(R.id.listviewAppliance);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, rows);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);
    }

    private void loadPreviousData(List<Integer> allIndexes) {
        for (int i = 0; i < allIndexes.size(); i++) {
            new addAppliance().execute(applianceLists.get(allIndexes.get(i)).getApplianceName());
        }
    }

    private void addSelectedAppliances(List<Integer> index) {
        for (int i = 0; i < index.size(); i++) {
            applianceLists.get(index.get(i)).setSelected(false);
            new addAppliance().execute(applianceLists.get(index.get(i)).getApplianceName());
        }
    }

    private class addAppliance extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            appliances.add(new Appliance(params[0],String.valueOf(aSwitch.getID())+String.valueOf(appliances.size()+1)));
            aSwitch.setAppliances(appliances);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            adapter.notifyDataSetChanged();
        }
    }

    private class deleteAppliance extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            appliances.remove(appliances.size() - 1);
            aSwitch.setAppliances(appliances);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }
}
