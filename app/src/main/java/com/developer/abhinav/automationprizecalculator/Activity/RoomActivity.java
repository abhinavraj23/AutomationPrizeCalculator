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

import com.developer.abhinav.automationprizecalculator.Adapter.RoomAdapter;
import com.developer.abhinav.automationprizecalculator.Model.Floor;
import com.developer.abhinav.automationprizecalculator.Model.Room;
import com.developer.abhinav.automationprizecalculator.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    private int roomCounter = 0;

    List<Room> rooms;
    RecyclerView listView;
    private RoomAdapter adapter;
    private static Floor floor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        rooms = new ArrayList<>();
        floor = (Floor) getIntent().getSerializableExtra("Floor");

        sharedPreferences = getSharedPreferences("ROOM", MODE_PRIVATE);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        roomCounter = sharedPreferences.getInt(floor.getFloorName(), 0);
        loadPreviousData(roomCounter);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                roomCounter++;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(floor.getFloorName(),roomCounter);
                editor.commit();
                new addRoom().execute();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked
                roomCounter--;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(floor.getFloorName(),roomCounter);
                editor.commit();
                new deleteRoom().execute();
            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
            }
        });

        adapter = new RoomAdapter(this, rooms, new RoomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Room item) {
                Intent i = new Intent(RoomActivity.this, SwitchActivity.class);
                i.putExtra("Room", item);
                startActivity(i);
            }
        });

        int rows = this.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT ? 1 : 2;
        initiateViews(rows);

    }

    private void initiateViews(int rows) {
        RecyclerView listView = findViewById(R.id.listviewRoom);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, rows);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.setAdapter(adapter);
    }

    private void loadPreviousData(int counter) {
        for (int i = 0; i < counter; i++) {
            new addRoom().execute();
        }
    }

    private class addRoom extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            rooms.add(new Room("Room " + String.valueOf(rooms.size() + 1)));
            floor.setRooms(rooms);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class deleteRoom extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (roomCounter > 0) {
                rooms.remove(rooms.size() - 1);
                floor.setRooms(rooms);
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
