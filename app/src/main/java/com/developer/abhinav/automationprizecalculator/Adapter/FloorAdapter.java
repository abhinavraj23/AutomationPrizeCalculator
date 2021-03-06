package com.developer.abhinav.automationprizecalculator.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.abhinav.automationprizecalculator.Model.Floor;
import com.developer.abhinav.automationprizecalculator.R;

import java.util.List;

public class FloorAdapter extends RecyclerView.Adapter<FloorAdapter.Holder> {

    private Context mContext;
    private List<Floor> floorList;
    private final OnItemClickListener listener;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * View holder for application list item
     */
    public class Holder extends RecyclerView.ViewHolder {

        TextView floorName;

        public Holder(View itemView) {
            super(itemView);
            this.floorName = itemView.findViewById(R.id.floor_name);
        }

        public void setup(final Floor floor, final OnItemClickListener listener) {
            floorName.setText(floor.getFloorName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(floor);
                }
            });
        }
    }

    public FloorAdapter(Context mContext, List<Floor> floorList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.floorList = floorList;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.floor_list, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setup(floorList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return floorList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Floor item);
    }

}