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

import com.developer.abhinav.automationprizecalculator.Adapter.SwitchAdapter;
import com.developer.abhinav.automationprizecalculator.Model.Appliance;
import com.developer.abhinav.automationprizecalculator.Model.Room;
import com.developer.abhinav.automationprizecalculator.Model.Switch;
import com.developer.abhinav.automationprizecalculator.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class SwitchActivity extends AppCompatActivity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    private int switchCounter = 0;

    List<Switch> switches;
    RecyclerView listView;
    private SwitchAdapter adapter;
    private static Room room;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        switches = new ArrayList<>();
        room = (Room) getIntent().getSerializableExtra("Room");
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                new addSwitch().execute();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                new deleteSwitch().execute();
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
            }
        });

        adapter = new SwitchAdapter(this, switches, new SwitchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Switch item) {
                Intent i = new Intent(SwitchActivity.this, ApplianceActivity.class);
                i.putExtra("Switch",item);
                startActivity(i);
            }
        });

        int rows = this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;
        initiateViews(rows);

    }

    private void initiateViews(int rows) {
        RecyclerView listView = findViewById(R.id.listviewSwitch);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, rows);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);
    }

    private class addSwitch extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            switchCounter++;
            switches.add(new Switch("Switch Board "+String.valueOf(switchCounter)));
            room.setSwitches(switches);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class deleteSwitch extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            switches.remove(switches.size()-1);
            room.setSwitches(switches);
            switchCounter--;
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }
}
