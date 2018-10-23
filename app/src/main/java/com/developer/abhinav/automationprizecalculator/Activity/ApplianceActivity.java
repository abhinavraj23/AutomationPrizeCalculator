package com.developer.abhinav.automationprizecalculator.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.developer.abhinav.automationprizecalculator.Adapter.FloorAdapter;
import com.developer.abhinav.automationprizecalculator.Adapter.ApplianceAdapter;
import com.developer.abhinav.automationprizecalculator.Model.Floor;
import com.developer.abhinav.automationprizecalculator.Model.Switch;
import com.developer.abhinav.automationprizecalculator.Model.Appliance;
import com.developer.abhinav.automationprizecalculator.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class ApplianceActivity extends AppCompatActivity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    private int applianceCounter = 0;

    List<Appliance> appliances;
    RecyclerView listView;
    private ApplianceAdapter adapter;
    private static Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);

        appliances = new ArrayList<>();
        aSwitch = (Switch) getIntent().getSerializableExtra("Switch");
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                new addAppliance().execute();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                new deleteAppliance().execute();
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
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

    private class addAppliance extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            applianceCounter++;
            appliances.add(new Appliance("Appliance "+String.valueOf(applianceCounter)));
            aSwitch.setAppliances(appliances);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class deleteAppliance extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            appliances.remove(appliances.size()-1);
            aSwitch.setAppliances(appliances);
            applianceCounter--;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }
}
