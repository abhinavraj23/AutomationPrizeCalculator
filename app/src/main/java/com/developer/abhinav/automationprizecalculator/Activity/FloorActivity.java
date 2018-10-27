package com.developer.abhinav.automationprizecalculator.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.developer.abhinav.automationprizecalculator.Adapter.FloorAdapter;
import com.developer.abhinav.automationprizecalculator.Model.Floor;
import com.developer.abhinav.automationprizecalculator.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class FloorActivity extends AppCompatActivity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    private int floorCounter = 0;

    List<Floor> floors;
    private FloorAdapter adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);

        floors = new ArrayList<>();
        sharedPreferences = getSharedPreferences("FLOOR", MODE_PRIVATE);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floorCounter = sharedPreferences.getInt("COUNTER", 0);
        loadPreviousData(floorCounter);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                if (floors.size() < 5) {
                    floorCounter++;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("COUNTER", floorCounter);
                    editor.commit();
                    new addFloor().execute();
                }
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                if(floors.size() != 0) {
                    floorCounter--;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("COUNTER", floorCounter);
                    editor.commit();
                    new deleteFloor().execute();
                }
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                startActivity(new Intent(getApplicationContext(), ListActivity.class));
            }
        });

        adapter = new FloorAdapter(this, floors, new FloorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Floor item) {
                Intent i = new Intent(FloorActivity.this, RoomActivity.class);
                i.putExtra("Floor", item);
                startActivity(i);
            }
        });

        int rows = this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;
        initiateViews(rows);

    }

    private void initiateViews(int rows) {
        RecyclerView listView = findViewById(R.id.listviewFloor);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, rows);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);
    }

    private void loadPreviousData(int counter) {
        for (int i = 0; i < counter; i++) {
            new addFloor().execute();
        }
    }

    private class addFloor extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            floors.add(new Floor("Floor " + String.valueOf(floors.size() + 1), String.valueOf(floors.size() + 1)));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class deleteFloor extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (floorCounter > 0) {
                floors.remove(floors.size() - 1);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }
}
