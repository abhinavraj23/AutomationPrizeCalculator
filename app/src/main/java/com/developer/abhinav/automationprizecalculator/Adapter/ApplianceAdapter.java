package com.developer.abhinav.automationprizecalculator.Adapter;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.developer.abhinav.automationprizecalculator.Model.Appliance;
import com.developer.abhinav.automationprizecalculator.R;

import java.util.List;

public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.Holder> {

    private Context mContext;
    private List<Appliance> ApplianceList;
    private final OnItemClickListener listener;


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * View holder for application list item
     */
    public class Holder extends RecyclerView.ViewHolder {

        TextView ApplianceName;

        public Holder(View itemView) {
            super(itemView);
            this.ApplianceName = itemView.findViewById(R.id.appliance_name);
        }

        public void setup(final Appliance Appliance, final OnItemClickListener listener) {
            ApplianceName.setText(Appliance.getApplianceName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(Appliance);
                }
            });
        }
    }

    public ApplianceAdapter(Context mContext, List<Appliance> ApplianceList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.ApplianceList = ApplianceList;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appliance_list, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setup(ApplianceList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return ApplianceList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Appliance item);
    }

}